package com.mr.problemsets.set_1

import kotlin.test.Test
import kotlin.test.assertEquals

class CountSubArraysWithSumKTest {

    @Test
    fun `basic case with multiple subarrays`() {
        assertEquals(2, countSubArraysWithSumK(listOf(1, 1, 1), 2))
        // [1,1] at index 0-1 and [1,1] at index 1-2
    }

    @Test
    fun `single element equals k`() {
        assertEquals(1, countSubArraysWithSumK(listOf(5), 5))
    }

    @Test
    fun `single element not equals k`() {
        assertEquals(0, countSubArraysWithSumK(listOf(5), 3))
    }

    @Test
    fun `entire array sums to k`() {
        assertEquals(1, countSubArraysWithSumK(listOf(1, 2, 3), 6))
    }

    @Test
    fun `no subarray sums to k`() {
        assertEquals(0, countSubArraysWithSumK(listOf(1, 2, 3), 10))
    }

    @Test
    fun `k is zero with zeros in array`() {
        assertEquals(6, countSubArraysWithSumK(listOf(0, 0, 0), 0))
        // [0] at 0, [0] at 1, [0] at 2, [0,0] at 0-1, [0,0] at 1-2, [0,0,0] at 0-2
        // Actually 6 subarrays sum to 0
    }

    @Test
    fun `k is zero - all zeros`() {
        assertEquals(6, countSubArraysWithSumK(listOf(0, 0, 0), 0))
    }

    @Test
    fun `negative numbers`() {
        assertEquals(4, countSubArraysWithSumK(listOf(1, -1, 1, -1), 0))
        // [1,-1] at 0-1 and [1,-1] at 2-3... actually more: [-1,1] at 1-2, [1,-1,1,-1] at 0-3
    }

    @Test
    fun `negative numbers - corrected`() {
        assertEquals(4, countSubArraysWithSumK(listOf(1, -1, 1, -1), 0))
    }

    @Test
    fun `mixed positive and negative`() {
        assertEquals(4, countSubArraysWithSumK(listOf(3, 4, 7, 2, -3, 1, 4, 2), 7))
        // [3,4], [7], [1,4,2]... need to verify
    }

    @Test
    fun `overlapping subarrays`() {
        assertEquals(4, countSubArraysWithSumK(listOf(1, 2, 1, 2, 1), 3))
        // [1,2] at 0-1, [2,1] at 1-2, [1,2] at 2-3, [2,1] at 3-4
    }

    @Test
    fun `large k with no match`() {
        assertEquals(0, countSubArraysWithSumK(listOf(1, 2, 3, 4, 5), 100))
    }

    @Test
    fun `empty array`() {
        assertEquals(0, countSubArraysWithSumK(emptyList(), 0))
    }

    @Test
    fun `prefix sum equals k multiple times`() {
        assertEquals(3, countSubArraysWithSumK(listOf(1, 1, 1, 1), 2))
        // [1,1] at 0-1, [1,1] at 1-2, [1,1] at 2-3
    }
}

