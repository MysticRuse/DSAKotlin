package com.mr.problemsets.set_2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MoveZeroesToEndTest {

    // ===== Problem Examples =====

    @Test
    fun `example 1 - mixed zeros and non-zeros`() {
        val nums = intArrayOf(0, 1, 0, 3, 12)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(1, 3, 12, 0, 0), nums)
    }

    @Test
    fun `example 2 - single zero`() {
        val nums = intArrayOf(0)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(0), nums)
    }

    // ===== Edge Cases =====

    @Test
    fun `single non-zero element`() {
        val nums = intArrayOf(5)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(5), nums)
    }

    @Test
    fun `no zeros in array`() {
        val nums = intArrayOf(1, 2, 3, 4, 5)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(1, 2, 3, 4, 5), nums)
    }

    @Test
    fun `all zeros`() {
        val nums = intArrayOf(0, 0, 0, 0)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(0, 0, 0, 0), nums)
    }

    // ===== Bug Regression Tests (from code review) =====

    @Test
    fun `non-zero at start - regression test for overwrite bug`() {
        // This was the bug case: [1, 0, 3, 12] → incorrectly became [3, 12, 0, 0]
        val nums = intArrayOf(1, 0, 3, 12)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(1, 3, 12, 0), nums)
    }

    @Test
    fun `first element non-zero followed by zero`() {
        val nums = intArrayOf(2, 0)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(2, 0), nums)
    }

    // ===== Position Variants =====

    @Test
    fun `zeros already at end`() {
        val nums = intArrayOf(1, 2, 3, 0, 0)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(1, 2, 3, 0, 0), nums)
    }

    @Test
    fun `zeros at beginning`() {
        val nums = intArrayOf(0, 0, 1, 2, 3)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(1, 2, 3, 0, 0), nums)
    }

    @Test
    fun `alternating zeros and non-zeros`() {
        val nums = intArrayOf(0, 1, 0, 2, 0, 3, 0)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(1, 2, 3, 0, 0, 0, 0), nums)
    }

    @Test
    fun `zero in middle`() {
        val nums = intArrayOf(1, 2, 0, 3, 4)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(1, 2, 3, 4, 0), nums)
    }

    // ===== Two Element Cases =====

    @Test
    fun `two elements - zero first`() {
        val nums = intArrayOf(0, 1)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(1, 0), nums)
    }

    @Test
    fun `two elements - zero last`() {
        val nums = intArrayOf(1, 0)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(1, 0), nums)
    }

    @Test
    fun `two elements - both non-zero`() {
        val nums = intArrayOf(1, 2)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(1, 2), nums)
    }

    @Test
    fun `two elements - both zero`() {
        val nums = intArrayOf(0, 0)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(0, 0), nums)
    }

    // ===== Relative Order Preservation =====

    @Test
    fun `relative order of non-zeros is maintained`() {
        val nums = intArrayOf(4, 0, 2, 0, 1, 0, 3)
        moveZeroesToEnd(nums)
        // Original non-zero order: 4, 2, 1, 3 - must be preserved
        assertArrayEquals(intArrayOf(4, 2, 1, 3, 0, 0, 0), nums)
    }

    @Test
    fun `descending non-zeros with zeros`() {
        val nums = intArrayOf(5, 0, 4, 0, 3, 0, 2, 0, 1)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(5, 4, 3, 2, 1, 0, 0, 0, 0), nums)
    }

    // ===== Negative Numbers =====

    @Test
    fun `negative numbers with zeros`() {
        val nums = intArrayOf(-1, 0, -2, 0, -3)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(-1, -2, -3, 0, 0), nums)
    }

    @Test
    fun `mixed positive and negative with zeros`() {
        val nums = intArrayOf(0, -1, 0, 3, 0, -5, 12)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(-1, 3, -5, 12, 0, 0, 0), nums)
    }

    // ===== Boundary Values =====

    @Test
    fun `large values at int boundaries`() {
        val nums = intArrayOf(Int.MAX_VALUE, 0, Int.MIN_VALUE, 0)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(Int.MAX_VALUE, Int.MIN_VALUE, 0, 0), nums)
    }

    @Test
    fun `consecutive non-zeros then consecutive zeros`() {
        val nums = intArrayOf(1, 2, 3, 0, 0, 0)
        moveZeroesToEnd(nums)
        assertArrayEquals(intArrayOf(1, 2, 3, 0, 0, 0), nums)
    }
}