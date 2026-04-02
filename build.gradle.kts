import com.mr.build.GenerateProblemsIndex

plugins {
    kotlin("jvm") version "2.2.21"
}

group = "com.mr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(23)
}

tasks.test {
    useJUnitPlatform()
}

/**
 * Regenerates PROBLEMS.generated.md. Implementation: [GenerateProblemsIndex] in buildSrc
 * (avoids Kotlin DSL script compiler IR crashes from large inline task bodies).
 *
 * Run: `./gradlew generateProblemsIndex`
 */
tasks.register("generateProblemsIndex") {
    group = "documentation"
    description = "Regenerate PROBLEMS.generated.md from Kotlin sources"

    doLast {
        GenerateProblemsIndex.run(layout.projectDirectory.asFile)
    }
}
