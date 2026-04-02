package com.mr.patterns.slidingwindow.medium

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MaximumSubarrayTest {

    private val solution = MaximumSubarray()

    // ===== LeetCode examples =====

    @Test
    fun `example 1 - classic mixed returns 6`() {
        Assertions.assertEquals(
            6,
            solution.maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4))
        )
    }

    @Test
    fun `example 2 - single element returns 1`() {
        Assertions.assertEquals(1, solution.maxSubArray(intArrayOf(1)))
    }

    @Test
    fun `example 3 - whole array is best returns 23`() {
        Assertions.assertEquals(23, solution.maxSubArray(intArrayOf(5, 4, -1, 7, 8)))
    }

    // ===== Edge cases =====

    @Test
    fun `empty array returns 0`() {
        Assertions.assertEquals(0, solution.maxSubArray(intArrayOf()))
    }

    @Test
    fun `all negative returns largest element`() {
        Assertions.assertEquals(-1, solution.maxSubArray(intArrayOf(-3, -2, -1)))
    }

    @Test
    fun `single negative returns that value`() {
        Assertions.assertEquals(-5, solution.maxSubArray(intArrayOf(-5)))
    }

    @Test
    fun `mixed reset and climb returns 6`() {
        Assertions.assertEquals(6, solution.maxSubArray(intArrayOf(3, -2, 5)))
    }

    @Test
    fun `two elements negative then positive returns 1`() {
        Assertions.assertEquals(1, solution.maxSubArray(intArrayOf(-2, 1)))
    }
}
