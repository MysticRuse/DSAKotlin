package com.mr.problemsets.set_2

/**
 * String segmentation
 * You are given a string S and a dictionary of strings wordDict. Write a program that returns true if S can be segmented into a space-separated sequence of one or more dictionary words, else return false.
 * Note: The same word in the dictionary may be reused multiple times in the segmentation.
 *
 * For Example:
 * Input: S = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * This should return true because "applepenapple" can be segmented as "apple pen apple". Since you are allowed to reuse a dictionary word.
 *
 * Input
 * The first line of input contains a string S.
 * The second line of input contains an integer N, representing the size of the wordDict.
 * The third line of input contains N space-separated strings, representing the words in the dictionary.
 *
 * Output
 * Print true if S can be segmented into a space-separated sequence, otherwise print false.
 *
 * Constraints
 * 1 <= N <= 25
 *
 * Example #1 Input applepenapple 2 apple pen
 * Output true
 * Explanation: Here "applepenapple" can be segmented as "apple-pen-apple", So return true.
 *
 * Example #2 Input catsandog 5 cats dog sand and catExample
 * Output false
 * Explanation: Here "catsandog" can be segmented as cat-sand-og, cats-and-og, cat-san-dog etc, but none of these combinations are completely present in the dictionary- [cats, dog, sand, and, cat], So return false.
 */

fun canSegmentString(S: String, wordDict: List<String>): Boolean {
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
    println(canSegmentString(s, wordDict))
}
