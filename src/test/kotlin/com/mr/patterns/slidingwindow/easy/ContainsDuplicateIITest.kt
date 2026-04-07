package com.mr.patterns.slidingwindow.easy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ContainsDuplicateIITest {

    val solution = ContainsDuplicateII()
    @Test
    fun testExample1() {
        val nums = intArrayOf(1, 2, 3, 1)
        assertTrue(solution.containsNearbyDuplicates(nums, 3))
    }

    @Test
    fun testExample2() {
        val nums = intArrayOf(1, 0, 1, 1)
        assertTrue(solution.containsNearbyDuplicates(nums, 1))
    }

    @Test
    fun testExample3() {
        val nums = intArrayOf(1, 2, 3, 1, 2, 3)
        assertFalse(solution.containsNearbyDuplicates(nums, 2))
    }

    @Test
    fun testNoDuplicates() {
        val nums = intArrayOf(1, 2, 3, 4, 5)
        assertFalse(solution.containsNearbyDuplicates(nums, 3))
    }

    @Test
    fun testImmediateDuplicate() {
        val nums = intArrayOf(1, 1)
        assertTrue(solution.containsNearbyDuplicates(nums, 1))
    }

    @Test
    fun testDuplicateTooFar() {
        val nums = intArrayOf(1, 2, 1)
        assertFalse(solution.containsNearbyDuplicates(nums, 1))
    }

    @Test
    fun testDuplicateWithinRange() {
        val nums = intArrayOf(1, 2, 1)
        assertTrue(solution.containsNearbyDuplicates(nums, 2))
    }

    @Test
    fun testSingleElement() {
        val nums = intArrayOf(99)
        assertFalse(solution.containsNearbyDuplicates(nums, 10))
    }

    @Test
    fun testDuplicateAtEndTooFar() {
        val nums = intArrayOf(1, 2, 3, 4, 1)
        assertFalse(solution.containsNearbyDuplicates(nums, 3))
    }

    @Test
    fun testDuplicateAtEndWithinRange() {
        val nums = intArrayOf(1, 2, 3, 4, 1)
        assertTrue(solution.containsNearbyDuplicates(nums, 4))
    }
}