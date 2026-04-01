package com.mr.problemsets.set_2

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class StringSegmentationTest {

    @Test
    fun `example 1 - applepenapple can be segmented`() {
        val s = "applepenapple"
        val wordDict = listOf("apple", "pen")
        assertTrue(canSegmentString(s, wordDict))
    }

    @Test
    fun `example 2 - catsandog cannot be segmented`() {
        val s = "catsandog"
        val wordDict = listOf("cats", "dog", "sand", "and", "cat")
        assertFalse(canSegmentString(s, wordDict))
    }

    @Test
    fun `single word match`() {
        val s = "hello"
        val wordDict = listOf("hello", "world")
        assertTrue(canSegmentString(s, wordDict))
    }

    @Test
    fun `reusing same word multiple times`() {
        val s = "aaaa"
        val wordDict = listOf("a", "aa")
        assertTrue(canSegmentString(s, wordDict))
    }

    @Test
    fun `empty string should return true`() {
        val s = ""
        val wordDict = listOf("any", "word")
        assertTrue(canSegmentString(s, wordDict))
    }

    @Test
    fun `no match possible`() {
        val s = "xyz"
        val wordDict = listOf("abc", "def")
        assertFalse(canSegmentString(s, wordDict))
    }

    @Test
    fun `leetcode classic example`() {
        val s = "leetcode"
        val wordDict = listOf("leet", "code")
        assertTrue(canSegmentString(s, wordDict))
    }

    @Test
    fun `partial match only - should return false`() {
        val s = "catsand"
        val wordDict = listOf("cats", "dog", "sand")
        // "cats" + "and" but "and" not in dict
        assertFalse(canSegmentString(s, wordDict))
    }

    @Test
    fun `complex segmentation`() {
        val s = "pineapplepenapple"
        val wordDict = listOf("apple", "pen", "applepen", "pine", "pineapple")
        assertTrue(canSegmentString(s, wordDict))
    }
}