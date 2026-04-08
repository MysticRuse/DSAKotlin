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
        //println("---i: $i-----")
        for (j in 0 until i) {
            // If s[0..j-1] can be segmented AND s[j..i-1] is in dictionary

            //println("dp: ${dp.toList()}")
            //println("substring: ${s.substring(j, i)}")
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

/**
 * Input: s="neetcode" wordDict=["neet","code"]
 * stdout:
 * ---i: 1-----
 * dp: [true, false, false, false, false, false, false, false, false]
 * substring: n
 * ---i: 2-----
 * dp: [true, false, false, false, false, false, false, false, false]
 * substring: ne
 * dp: [true, false, false, false, false, false, false, false, false]
 * substring: e
 * ---i: 3-----
 * dp: [true, false, false, false, false, false, false, false, false]
 * substring: nee
 * dp: [true, false, false, false, false, false, false, false, false]
 * substring: ee
 * dp: [true, false, false, false, false, false, false, false, false]
 * substring: e
 * ---i: 4-----
 * dp: [true, false, false, false, false, false, false, false, false]
 * substring: neet
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: eet
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: et
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: t
 * ---i: 5-----
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: neetc
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: eetc
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: etc
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: tc
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: c
 * ---i: 6-----
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: neetco
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: eetco
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: etco
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: tco
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: co
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: o
 * ---i: 7-----
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: neetcod
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: eetcod
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: etcod
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: tcod
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: cod
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: od
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: d
 * ---i: 8-----
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: neetcode
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: eetcode
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: etcode
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: tcode
 * dp: [true, false, false, false, true, false, false, false, false]
 * substring: code
 * dp: [true, false, false, false, true, false, false, false, true]
 * substring: ode
 * dp: [true, false, false, false, true, false, false, false, true]
 * substring: de
 * dp: [true, false, false, false, true, false, false, false, true]
 * substring: e
 *
 * Your Output: true
 * Expected output: true
 */
