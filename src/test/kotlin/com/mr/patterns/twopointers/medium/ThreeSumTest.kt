package com.mr.patterns.twopointers.medium

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ThreeSumTest {

    // Helper to compare results regardless of order
    private fun assertTripletsEqual(expected: List<List<Int>>, actual: List<List<Int>>) {
        val expectedSorted = expected.map { it.sorted() }.sortedBy { it.toString() }
        val actualSorted = actual.map { it.sorted() }.sortedBy { it.toString() }
        Assertions.assertEquals(expectedSorted, actualSorted)
    }

    // ===== LeetCode Examples =====

    @Test
    fun `example 1 - standard case with duplicates`() {
        val nums = intArrayOf(-1, 0, 1, 2, -1, -4)
        val expected = listOf(listOf(-1, -1, 2), listOf(-1, 0, 1))
        assertTripletsEqual(expected, threeSum(nums))
    }

    @Test
    fun `example 2 - no valid triplet`() {
        val nums = intArrayOf(0, 1, 1)
        Assertions.assertTrue(threeSum(nums).isEmpty())
    }

    @Test
    fun `example 3 - three zeros`() {
        val nums = intArrayOf(0, 0, 0)
        val expected = listOf(listOf(0, 0, 0))
        assertTripletsEqual(expected, threeSum(nums))
    }

    // ===== Edge Cases =====

    @Test
    fun `exactly three elements - valid triplet`() {
        val nums = intArrayOf(-1, 0, 1)
        val expected = listOf(listOf(-1, 0, 1))
        assertTripletsEqual(expected, threeSum(nums))
    }

    @Test
    fun `exactly three elements - invalid triplet`() {
        val nums = intArrayOf(1, 2, 3)
        Assertions.assertTrue(threeSum(nums).isEmpty())
    }

    @Test
    fun `all positive numbers - no solution`() {
        val nums = intArrayOf(1, 2, 3, 4, 5)
        Assertions.assertTrue(threeSum(nums).isEmpty())
    }

    @Test
    fun `all negative numbers - no solution`() {
        val nums = intArrayOf(-5, -4, -3, -2, -1)
        Assertions.assertTrue(threeSum(nums).isEmpty())
    }

    // ===== Duplicate Handling =====

    @Test
    fun `many duplicates - single unique triplet`() {
        val nums = intArrayOf(0, 0, 0, 0, 0)
        val expected = listOf(listOf(0, 0, 0))
        assertTripletsEqual(expected, threeSum(nums))
    }

    @Test
    fun `duplicates should not create duplicate triplets`() {
        val nums = intArrayOf(-2, -2, 0, 0, 2, 2)
        val expected = listOf(listOf(-2, 0, 2))
        assertTripletsEqual(expected, threeSum(nums))
    }

    @Test
    fun `multiple valid triplets with duplicates`() {
        val nums = intArrayOf(-1, -1, -1, 0, 1, 1, 1, 2)
        val expected = listOf(
            listOf(-1, -1, 2),
            listOf(-1, 0, 1)
        )
        assertTripletsEqual(expected, threeSum(nums))
    }

    // ===== Multiple Triplets =====

    @Test
    fun `multiple distinct triplets`() {
        val nums = intArrayOf(-4, -2, -1, 0, 1, 2, 3, 4)
        val expected = listOf(
            listOf(-4, 0, 4),
            listOf(-4, 1, 3),
            listOf(-2, -1, 3),
            listOf(-2, 0, 2),
            listOf(-1, 0, 1)
        )
        assertTripletsEqual(expected, threeSum(nums))
    }

    @Test
    fun `triplets with zeros`() {
        val nums = intArrayOf(-2, 0, 0, 2, 2)
        val expected = listOf(listOf(-2, 0, 2))
        assertTripletsEqual(expected, threeSum(nums))
    }

    // ===== Special Values =====

    @Test
    fun `large values`() {
        val nums = intArrayOf(-100000, 50000, 50000)
        val expected = listOf(listOf(-100000, 50000, 50000))
        assertTripletsEqual(expected, threeSum(nums))
    }

    @Test
    fun `mix of large positive and negative`() {
        val nums = intArrayOf(-100000, -50000, 0, 50000, 100000)
        val expected = listOf(
            listOf(-100000, 0, 100000),
            listOf(-50000, 0, 50000)
        )
        assertTripletsEqual(expected, threeSum(nums))
    }

    // ===== Sorted Input Variations =====

    @Test
    fun `already sorted ascending`() {
        val nums = intArrayOf(-3, -2, -1, 0, 1, 2, 3)
        val expected = listOf(
            listOf(-3, 0, 3),
            listOf(-3, 1, 2),
            listOf(-2, -1, 3),
            listOf(-2, 0, 2),
            listOf(-1, 0, 1)
        )
        assertTripletsEqual(expected, threeSum(nums))
    }

    @Test
    fun `sorted descending`() {
        val nums = intArrayOf(3, 2, 1, 0, -1, -2, -3)
        val expected = listOf(
            listOf(-3, 0, 3),
            listOf(-3, 1, 2),
            listOf(-2, -1, 3),
            listOf(-2, 0, 2),
            listOf(-1, 0, 1)
        )
        assertTripletsEqual(expected, threeSum(nums))
    }

    // ===== Complex Cases =====

    @Test
    fun `leetcode hard case - extensive duplicates`() {
        val nums = intArrayOf(-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4)
        val result = threeSum(nums)

        // Verify no duplicate triplets
        val sortedTriplets = result.map { it.sorted() }
        Assertions.assertEquals(sortedTriplets.size, sortedTriplets.distinct().size)

        // Verify all triplets sum to 0
        result.forEach { triplet ->
            Assertions.assertEquals(0, triplet.sum(), "Triplet $triplet does not sum to 0")
        }
    }

    @Test
    fun `all elements same non-zero - no solution`() {
        val nums = intArrayOf(1, 1, 1, 1, 1)
        Assertions.assertTrue(threeSum(nums).isEmpty())
    }

    @Test
    fun `two zeros with matching pair`() {
        val nums = intArrayOf(0, 0, -1, 1)
        val expected = listOf(
            listOf(-1, 0, 1),
            listOf(0, 0, 0) // This won't be included - only 2 zeros
        ).filter { triplet ->
            // Filter to only valid triplets based on available elements
            triplet != listOf(0, 0, 0) // Can't form [0,0,0] with only 2 zeros
        }
        val result = threeSum(nums)
        assertTripletsEqual(listOf(listOf(-1, 0, 1)), result)
    }
}