package com.mr.patterns.slidingwindow.medium

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LongestSubstringWithoutRepeatingCharsTest {

    // ===== LeetCode Examples =====

    val solution = LongestSubstringWithoutRepeatingChars()
    @Test
    fun `example 1 - abcabcbb returns 3`() {
        Assertions.assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"))
    }

    @Test
    fun `example 2 - all same characters returns 1`() {
        Assertions.assertEquals(1, solution.lengthOfLongestSubstring("bbbbb"))
    }

    @Test
    fun `example 3 - pwwkew returns 3`() {
        Assertions.assertEquals(3, solution.lengthOfLongestSubstring("pwwkew"))
    }

    // ===== Edge Cases - Empty and Single =====

    @Test
    fun `empty string returns 0`() {
        Assertions.assertEquals(0, solution.lengthOfLongestSubstring(""))
    }

    @Test
    fun `single character returns 1`() {
        Assertions.assertEquals(1, solution.lengthOfLongestSubstring("a"))
        Assertions.assertEquals(1, solution.lengthOfLongestSubstring("Z"))
        Assertions.assertEquals(1, solution.lengthOfLongestSubstring("1"))
    }

    @Test
    fun `two same characters returns 1`() {
        Assertions.assertEquals(1, solution.lengthOfLongestSubstring("aa"))
        Assertions.assertEquals(1, solution.lengthOfLongestSubstring("11"))
    }

    @Test
    fun `two different characters returns 2`() {
        Assertions.assertEquals(2, solution.lengthOfLongestSubstring("ab"))
        Assertions.assertEquals(2, solution.lengthOfLongestSubstring("12"))
    }

    // ===== All Unique Characters =====

    @Test
    fun `all unique returns full length`() {
        Assertions.assertEquals(6, solution.lengthOfLongestSubstring("abcdef"))
        Assertions.assertEquals(10, solution.lengthOfLongestSubstring("abcdefghij"))
    }

    @Test
    fun `unique with mixed types`() {
        Assertions.assertEquals(6, solution.lengthOfLongestSubstring("abc123"))
        Assertions.assertEquals(5, solution.lengthOfLongestSubstring("a1b2c"))
    }

    // ===== Repeating Patterns =====

    @Test
    fun `alternating characters`() {
        Assertions.assertEquals(2, solution.lengthOfLongestSubstring("ababab"))
        Assertions.assertEquals(2, solution.lengthOfLongestSubstring("abab"))
    }

    @Test
    fun `repeat at different positions`() {
        Assertions.assertEquals(4, solution.lengthOfLongestSubstring("abcda"))  // "abcd" or "bcda"
        Assertions.assertEquals(4, solution.lengthOfLongestSubstring("aabcd"))  // "abcd"
        Assertions.assertEquals(4, solution.lengthOfLongestSubstring("abcdd"))  // "abcd"
    }

    // ===== Special Characters =====

    @Test
    fun `string with spaces`() {
        Assertions.assertEquals(3, solution.lengthOfLongestSubstring("a b c"))  // "a b" or " b " or "b c"
        Assertions.assertEquals(1, solution.lengthOfLongestSubstring("   "))     // only spaces
    }

    @Test
    fun `string with symbols`() {
        Assertions.assertEquals(5, solution.lengthOfLongestSubstring("a!@#b"))
        Assertions.assertEquals(2, solution.lengthOfLongestSubstring("!@!"))
    }

    // ===== Complex Cases =====

    @Test
    fun `longest unique substring in middle`() {
        Assertions.assertEquals(7, solution.lengthOfLongestSubstring("xxabcdefxx"))
        Assertions.assertEquals(5, solution.lengthOfLongestSubstring("aabcdeaa"))
    }

    @Test
    fun `palindrome-like strings`() {
        Assertions.assertEquals(3, solution.lengthOfLongestSubstring("abccba"))
        Assertions.assertEquals(3, solution.lengthOfLongestSubstring("abcba"))
    }

    @Test
    fun `numbers only`() {
        Assertions.assertEquals(3, solution.lengthOfLongestSubstring("12321"))
        Assertions.assertEquals(10, solution.lengthOfLongestSubstring("1234567890"))
    }

    @Test
    fun `window shrinks correctly`() {
        // "dvdf" -> when we see second 'd', we should jump past first 'd'
        Assertions.assertEquals(3, solution.lengthOfLongestSubstring("dvdf"))  // "vdf"
    }

    @Test
    fun `tmmzuxt case`() {
        Assertions.assertEquals(5, solution.lengthOfLongestSubstring("tmmzuxt"))  // "mzuxt"
    }
}