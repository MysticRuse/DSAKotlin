package com.mr.patterns.twopointers.easy

/**
 * 125. Valid Palindrome (Easy)
 *
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase
 * and removing all non-alphanumeric characters, it reads the same forward and backward.
 *
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true ("amanaplanacanalpanama" is a palindrome)
 *
 * Example 2:
 * Input: s = "race a car"
 * Output: false ("raceacar" is not a palindrome)
 *
 * Example 3:
 * Input: s = " "
 * Output: true (Empty string after removing non-alphanumeric is "" which is a palindrome)
 *
 * Constraints:
 * 1 <= s.length <= 2 * 10^5
 * s consists only of printable ASCII characters.
 */


/**
 * Notes:
 * 1. Use two pointers, one at the start and one at the end of the string
 * 2. Skip alphanumeric characters from both ends
 * 3. Compare the characters at the two pointers (use .lowercase() for case-insensitive comparison)
 */
fun isValidPalindrome(s: String): Boolean {

    var left = 0
    var right = s.length - 1

    while (left < right) {
        // Skip alphanumeric from left
        while (left < right && !s[left].isLetterOrDigit()) left++

        // skip alphanumeric from right
        while (left < right && !s[right].isLetterOrDigit()) right--

        if (s[left].lowercase() != s[right].lowercase()) return false

        left++
        right--
    }

    return true

}

fun main() {
    data class TestCase(val name: String, val input: String, val expected: Boolean)

    val testCases = listOf(
        // General cases
        TestCase("Classic palindrome with spaces/punctuation", "A man, a plan, a canal: Panama", true),
        TestCase("Simple palindrome", "racecar", true),
        TestCase("Not a palindrome", "race a car", false),
        TestCase("Palindrome with numbers", "Was it a car or a cat I saw?", true),

        // Edge cases
        TestCase("Single character", "a", true),
        TestCase("Two same characters", "aa", true),
        TestCase("Two different characters", "ab", false),
        TestCase("Only spaces", " ", true),
        TestCase("Only punctuation", ".,!?", true),
        TestCase("Mixed case", "Aa", true),
        TestCase("Numbers only palindrome", "12321", true),
        TestCase("Numbers not palindrome", "12345", false),
        TestCase("Alphanumeric mix", "0P", false),

        // Empty-like cases
        TestCase("Empty string", "", true),
        TestCase("Single space", " ", true),
        TestCase("Multiple spaces", "   ", true)
    )

    println("=== Valid Palindrome Test Cases ===\n")

    var passed = 0
    for ((index, test) in testCases.withIndex()) {
        val result = isValidPalindrome(test.input)
        val success = result == test.expected

        val status = if (success) "✅ PASS" else "❌ FAIL"
        println("Test ${index + 1}: ${test.name}")
        println("  Input:    \"${test.input}\"")
        println("  Expected: ${test.expected}")
        println("  Actual:   $result")
        println("  Status:   $status\n")

        if (success) passed++
    }

    println("=== Results: $passed/${testCases.size} tests passed ===")
}