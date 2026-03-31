package com.mr.problemsets.set_1

/**
 * ✅ Problem 2 (Easy/Medium) — Valid Near Palindrome
 * Given a string s, determine if it can become a palindrome after deleting at most one character.
 * Print "YES" or "NO".
 * Input
 * One line string s
 * Output
 * YES or NO
 * Constraints
 * 1 ≤ |s| ≤ 200000
 * lowercase English letters only
 * Example 1
 * Input
 * abca
 * Output
 * YES
 * Explanation: delete 'b' or 'c'.
 * Example 2
 * Input
 * abc
 * Output
 * NO
 */

fun isValidNearPalindrome(s:String): Boolean {
    var left = 0
    var right = s.length - 1
    while (left < right) {
        if (s[left] != s[right]) {
            return isPalindromeRange(left+1, right, s) || isPalindromeRange(left, right-1, s)
        }
        left++
        right--
    }

    return true // Already palindrome

}

fun isPalindromeRange(left: Int, right: Int, s: String): Boolean  {
    var l = left
    var r = right
    while (l < r) {
        if (s[left] != s[right]) {
            return false
        }
        l++
        r--
    }

    return true
}


fun main() {
    val s = readln()
    println(if (isValidNearPalindrome(s)) "YES" else "NO")
}