package com.mr.build

import java.io.File
import java.util.Locale

/**
 * Generates [PROBLEMS.generated.md] from Kotlin sources. Logic lives here so the root
 * [build.gradle.kts] stays small; Gradle’s Kotlin DSL compiler can crash on heavy nested
 * lambdas / local data classes in the script (IR lowering).
 */
object GenerateProblemsIndex {

    fun run(projectRoot: File) {
        val mrRoot = projectRoot.resolve("src/main/kotlin/com/mr")

        val out = StringBuilder()
        out.appendLine("# Generated problem index")
        out.appendLine()
        out.appendLine("> Regenerate: `./gradlew generateProblemsIndex`")
        out.appendLine()
        out.appendLine("---")
        out.appendLine()
        out.appendLine("## Problems by pattern")
        out.appendLine()

        val summaryRows = mutableListOf<Triple<String, DiffCount, String>>()

        val patternsRoot = mrRoot.resolve("patterns")
        if (patternsRoot.isDirectory) {
            val patternDirs = patternsRoot.listFiles()
                ?.filter { it.isDirectory }
                ?.sortedBy { it.name }
                .orEmpty()
            for (patternDir in patternDirs) {
                val sectionTitle = patternSectionTitle(patternDir.name)
                out.appendLine("### $sectionTitle")
                out.appendLine()
                out.appendLine("| # | Problem | Difficulty | File |")
                out.appendLine("|---|---------|------------|------|")

                val counts = DiffCount()
                var idx = 1
                for (diffFolder in listOf("easy", "medium", "hard")) {
                    val d = patternDir.resolve(diffFolder)
                    if (!d.isDirectory) continue
                    val diffLabel = diffFolder.replaceFirstChar { it.titlecase(Locale.US) }
                    val files = d.listFiles()
                        ?.filter { it.isFile && it.extension == "kt" }
                        ?.sortedBy { it.name }
                        .orEmpty()
                    for (f in files) {
                        val title = fileNameToTitle(f.name)
                        val link = relativeLink(f, projectRoot)
                        out.appendLine("| $idx | [$title]($link) | $diffLabel | `${f.name}` |")
                        counts.add(diffLabel)
                        idx++
                    }
                }
                summaryRows.add(Triple(sectionTitle, counts, "pattern:${patternDir.name}"))
                out.appendLine()
            }
        }

        out.appendLine("---")
        out.appendLine()
        out.appendLine("## Problem sets")
        out.appendLine()

        val setsRoot = mrRoot.resolve("problemsets")
        if (setsRoot.isDirectory) {
            val setDirs = setsRoot.listFiles()
                ?.filter { it.isDirectory && it.name.startsWith("set_") }
                ?.sortedBy { it.name }
                .orEmpty()
            for (setDir in setDirs) {
                val setLabel = setDir.name.replace("_", " ").replaceFirstChar { it.titlecase(Locale.US) }
                out.appendLine("### $setLabel")
                out.appendLine()
                out.appendLine("| # | Problem | Difficulty | File |")
                out.appendLine("|---|---------|------------|------|")

                val counts = DiffCount()
                var idx = 1
                val files = setDir.listFiles()
                    ?.filter { it.isFile && it.extension == "kt" }
                    ?.sortedBy { it.name }
                    .orEmpty()
                for (f in files) {
                    val (diff, title) = parseProblemSetMeta(f)
                    val link = relativeLink(f, projectRoot)
                    out.appendLine("| $idx | [$title]($link) | $diff | `${f.name}` |")
                    if (diff != "—") counts.add(diff)
                    idx++
                }
                summaryRows.add(Triple(setLabel, counts, "set:${setDir.name}"))
                out.appendLine()
            }
        }

        out.appendLine("---")
        out.appendLine()
        out.appendLine("## Problem count summary")
        out.appendLine()
        out.appendLine("| Category | Easy | Medium | Hard | Total |")
        out.appendLine("|----------|------|--------|------|-------|")

        var tEasy = 0
        var tMed = 0
        var tHard = 0
        for ((name, c, _) in summaryRows) {
            out.appendLine("| $name | ${c.easy} | ${c.medium} | ${c.hard} | ${c.total} |")
            tEasy += c.easy
            tMed += c.medium
            tHard += c.hard
        }
        out.appendLine("| **Total** | **$tEasy** | **$tMed** | **$tHard** | **${tEasy + tMed + tHard}** |")
        out.appendLine()

        val outFile = projectRoot.resolve("PROBLEMS.generated.md")
        outFile.writeText(out.toString())
        println(
            "Wrote ${outFile.relativeTo(projectRoot)} (${summaryRows.size} categories, ${tEasy + tMed + tHard} problems)"
        )
    }

    private class DiffCount(var easy: Int = 0, var medium: Int = 0, var hard: Int = 0) {
        fun add(diff: String) {
            when (diff.lowercase(Locale.US)) {
                "easy" -> easy++
                "medium" -> medium++
                "hard" -> hard++
            }
        }

        val total: Int get() = easy + medium + hard
    }

    private fun fileNameToTitle(base: String): String =
        base.removeSuffix(".kt").replace(Regex("([a-z])([A-Z0-9])"), "$1 $2")

    private fun patternSectionTitle(folder: String): String = when (folder) {
        "twopointers" -> "Two Pointers"
        "slidingwindow" -> "Sliding Window"
        else -> folder.split("_").joinToString(" ") { word ->
            word.replaceFirstChar { it.titlecase(Locale.US) }
        }
    }

    private fun relativeLink(file: File, projectRoot: File): String =
        file.relativeTo(projectRoot).path.replace(File.separatorChar, '/')

    private fun parseProblemSetMeta(file: File): Pair<String, String> {
        val head = file.readText().lineSequence().take(80).joinToString("\n")
        val problemDash = Regex(
            """Problem\s+\d+\s*\(\s*(Easy|Medium|Hard)\s*\)\s*[-–—]\s*(.+)"""
        ).find(head)
        if (problemDash != null) {
            val title = problemDash.groupValues[2].trim().trimEnd('*', ' ')
            return problemDash.groupValues[1] to title
        }
        val leetTitle = Regex("""^\s*\*\s*\d+\.\s*(.+)\s*$""", RegexOption.MULTILINE).find(head)
        val diffLine = Regex(
            """^\s*\*\s*(Easy|Medium|Hard)\s*$""",
            RegexOption.MULTILINE
        ).find(head)
        if (leetTitle != null && diffLine != null) {
            return diffLine.groupValues[1] to leetTitle.groupValues[1].trim()
        }
        val fallbackTitle = fileNameToTitle(file.name)
        val diff = Regex("""\b(Easy|Medium|Hard)\b""").find(head)?.groupValues?.get(1) ?: "—"
        return diff to fallbackTitle
    }
}
