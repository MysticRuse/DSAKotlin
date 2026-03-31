package com.mr.problemsets.set_1

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ValidNearPalindromeTest {

    @Test
    fun `already a palindrome`() {
        assertTrue(isValidNearPalindrome("aba"))
        assertTrue(isValidNearPalindrome("abba"))
    }

    @Test
    fun `single character`() {
        assertTrue(isValidNearPalindrome("a"))
    }

    @Test
    fun `two characters same`() {
        assertTrue(isValidNearPalindrome("aa"))
    }

    @Test
    fun `two characters different`() {
        assertTrue(isValidNearPalindrome("ab"))
    }

    @Test
    fun `remove one to become palindrome`() {
        assertTrue(isValidNearPalindrome("abca"))  // remove 'c' or 'b'
        assertTrue(isValidNearPalindrome("abcba")) // already palindrome
        assertTrue(isValidNearPalindrome("abcbxa")) // remove 'x'
    }

    @Test
    fun `needs more than one removal`() {
        assertFalse(isValidNearPalindrome("abcd"))
        assertFalse(isValidNearPalindrome("abcdef"))
    }

    @Test
    fun `edge case - remove from middle`() {
        assertTrue(isValidNearPalindrome("racecar"))  // already palindrome
        assertTrue(isValidNearPalindrome("raceecar")) // remove one 'e'
    }

    @Test
    fun `empty string`() {
        assertTrue(isValidNearPalindrome(""))
    }

    @Test
    fun `all same characters`() {
        assertTrue(isValidNearPalindrome("aaaa"))
        assertTrue(isValidNearPalindrome("aaabaaa"))
    }

    @Test
    fun `classic examples`() {
        assertTrue(isValidNearPalindrome("deeee"))   // remove 'd'
        assertFalse(isValidNearPalindrome("abc"))    // needs 2+ removals
    }
}