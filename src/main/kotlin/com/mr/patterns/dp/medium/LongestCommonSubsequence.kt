package com.mr.patterns.dp.medium

/**
 * 1143. Longest Common Subsequence
 * Medium
 * Topics: DP, 2D DP
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 * Example 1:
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 * Constraints:
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 */
class LongestCommonSubsequence {


    fun longestCommonSubsequence(text1: String, text2: String): Int {
        //return longestCommonSubsequence_DP_BottomUp(text1, text2)

        return longestCommonSubsequence_DP_SpaceOptimized(text1, text2)
    }

    fun longestCommonSubsequence_DP_SpaceOptimized(text1: String, text2: String): Int {

        // Always use the shorter string as columns to minimize space usage.
        // This ensures DP arrays are O(min(m, n)).
        var s1 = text1
        var s2 = text2

        if (s2.length > s1.length) {
            val temp = s1
            s1 = s2
            s2 = temp
        }

        val m = s1.length
        val n = s2.length

        // prev[j] represents dp[i-1][j] (previous row)
        // curr[j] represents dp[i][j]   (current row being built)
        var prev = IntArray(n + 1)
        var curr = IntArray(n + 1)

        // Build DP row by row (bottom-up)
        for (i in 1..m) {
            // Important: reset curr[0] for each row (base case dp[i][0] = 0)
            curr[0] = 0

            for (j in 1..n) {
                if (s1[i - 1] == s2[j - 1]) {
                    // If chars match:
                    // dp[i][j] = dp[i-1][j-1] + 1
                    // prev[j-1] is dp[i-1][j-1]
                    curr[j] = prev[j - 1] + 1
                } else {
                    // If chars do not match:
                    // dp[i][j] = max(dp[i-1][j], dp[i][j-1])
                    // prev[j] is dp[i-1][j] (top)
                    // curr[j-1] is dp[i][j-1] (left)
                    curr[j] = maxOf(prev[j], curr[j - 1])
                }
            }

            // After finishing the row:
            // curr becomes the new prev row for next iteration.
            val temp = prev
            prev = curr
            curr = temp
        }

        // After processing all rows, prev[n] contains dp[m][n]
        return prev[n]
    }

    private fun longestCommonSubsequence_DP_BottomUp(text1: String, text2: String): Int {
        // Bottom-Up(Tabular) from top left corner of 2D DP array.
        var m = text1.length
        var n = text2.length

        val dp = Array(m+1) { IntArray(n+1) }

        for (i in 1..m) {
            for (j in 1..n) {
                dp[i][j] = if (text1[i-1] == text2[j-1]) {
                    dp[i-1][j-1] + 1
                } else {
                    maxOf(dp[i-1][j], dp[i][j-1])
                }
            }
        }
        return dp[m][n]
    }
}

fun main() {
    val solver = LongestCommonSubsequence()

    data class TestCase(
        val text1: String,
        val text2: String,
        val expected: Int,
        val description: String
    )

    val tests = listOf(
        TestCase(
            text1 = "abcde",
            text2 = "ace",
            expected = 3,
            description = "Basic case: common subsequence exists (ace)"
        ),
        TestCase(
            text1 = "abc",
            text2 = "abc",
            expected = 3,
            description = "Identical strings: LCS is the full string"
        ),
        TestCase(
            text1 = "abc",
            text2 = "def",
            expected = 0,
            description = "No common characters: LCS length is 0"
        ),
        TestCase(
            text1 = "",
            text2 = "abc",
            expected = 0,
            description = "One empty string: LCS length is 0"
        ),
        TestCase(
            text1 = "",
            text2 = "",
            expected = 0,
            description = "Both strings empty: LCS length is 0"
        ),
        TestCase(
            text1 = "aaaa",
            text2 = "aa",
            expected = 2,
            description = "Repeated characters: LCS uses correct counts"
        ),
        TestCase(
            text1 = "AGGTAB",
            text2 = "GXTXAYB",
            expected = 4,
            description = "Classic LCS example: expected GTAB"
        ),
        TestCase(
            text1 = "abc",
            text2 = "ac",
            expected = 2,
            description = "Subsequence skipping middle character"
        ),
        TestCase(
            text1 = "abc",
            text2 = "cba",
            expected = 1,
            description = "Same letters but reversed order: only 1 char matches in order"
        ),
        TestCase(
            text1 = "XMJYAUZ",
            text2 = "MZJAWXU",
            expected = 4,
            description = "Another classic case: expected MJAU"
        )
    )

    var passed = 0

    for ((index, test) in tests.withIndex()) {
        val actual = solver.longestCommonSubsequence(test.text1, test.text2)

        if (actual == test.expected) {
            println("✅ PASS #${index + 1}: ${test.description}")
            passed++
        } else {
            println("❌ FAIL #${index + 1}: ${test.description}")
            println("   text1='${test.text1}', text2='${test.text2}'")
            println("   Expected=${test.expected}, Got=$actual")
        }
    }

    println("\n==============================")
    println("LCS Test Summary")
    println("==============================")
    println("Total Tests : ${tests.size}")
    println("Passed      : $passed")
    println("Failed      : ${tests.size - passed}")

    if (passed == tests.size) {
        println("🎉 All test cases passed!")
    }
}