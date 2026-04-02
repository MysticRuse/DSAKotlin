package com.mr.problemsets.set_2

/**
 * 139. Word Break
 * Medium
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 * Constraints:
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 */

fun wordBreak(S: String, wordDict: List<String>): Boolean {
    val wordSet = wordDict.toHashSet()

    val n = S.length

    val dp = BooleanArray(n+1)
    dp[0] = true // Empty string can always be segmented
    for (i in 1..n) {
        for (j in 0 until i) {
            // If s[0..j-1] can be segmented AND s[j..i-1] is in dictionary
            if (dp[j] && wordSet.contains(S.substring(j, i))) {
                dp[i] = true
            }
        }
    }
    return dp[n]
}

fun main() {
    // Read input
    val s = readln().trim()
    val wordDict = readln().trim().split(" ")

    // Solve and print result
    println(wordBreak(s, wordDict))
}
