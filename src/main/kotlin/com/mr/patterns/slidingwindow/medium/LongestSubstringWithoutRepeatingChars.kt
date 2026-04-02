package com.mr.patterns.slidingwindow.medium

/**
 * 3. Longest Substring Without Repeating Characters
 * Medium
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Constraints:
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 */
class LongestSubstringWithoutRepeatingChars {

    /**
     * 2 pointer solution with HashSet.
     * Keep a left pointer and right pointer.
     * for (right in s.indices):
     *     while right pointer val in HashSet,
     *         remove char at [left], increment left;
     *     add right pointer val in HashSet.
     *     Update result size to max for right - left + 1;
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun lengthOfLongestSubstring(s: String): Int {

        val seen = mutableSetOf<Char>()
        var left = 0
        var longest = 0

        for (right in 0 until s.length) {
            while (s[right] in seen) {
                seen.remove(s[left])
                left++
            }
            seen.add(s[right])
            longest = maxOf(longest, right - left + 1)
            //println("seen: $seen, left: $left, right: $right, maxLength: $maxLength")
        }

        return longest
    }
}

fun main() {
    data class TestCase(val name: String, val input: String, val expected: Int)

    val testCases = listOf(
        // LeetCode Examples
        TestCase("Example 1 - abcabcbb", "abcabcbb", 3),
        TestCase("Example 2 - all same chars", "bbbbb", 1),
        TestCase("Example 3 - pwwkew", "pwwkew", 3),

        // Edge cases - Empty and Single
        TestCase("Empty string", "", 0),
        TestCase("Single character", "a", 1),
        TestCase("Two same characters", "aa", 1),
        TestCase("Two different characters", "ab", 2),

        // All unique
        TestCase("All unique characters", "abcdef", 6),
        TestCase("Unique with numbers", "abc123", 6),

        // Repeating patterns
        TestCase("Alternating chars", "ababab", 2),
        TestCase("Repeat at end", "abcda", 4),
        TestCase("Repeat at start", "aabcd", 4),

        // Special characters
        TestCase("With spaces", "a b c", 3),
        TestCase("With symbols", "a!@#b", 5),
        TestCase("Only spaces", "   ", 1),

        // Complex cases
        TestCase("Long unique at middle", "xxabcdefxx", 7),
        TestCase("Palindrome-like", "abccba", 3),
        TestCase("Numbers only", "12321", 3)
    )

    println("=== Longest Substring Without Repeating Characters ===\n")

    var passed = 0
    val solution = LongestSubstringWithoutRepeatingChars()
    for ((index, test) in testCases.withIndex()) {
        val result = solution.lengthOfLongestSubstring(test.input)
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