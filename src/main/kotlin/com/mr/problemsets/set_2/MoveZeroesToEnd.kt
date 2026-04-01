package com.mr.problemsets.set_2

/**
 * Moving Zeros to End
 * Given an integer array nums[], move all 0's to the end of it while maintaining the relative order of the non- zero elements.
 * Note: You must do this in-place without making a copy of the array.
 *
 * Input
 * The first line of input contains an integer N, representing the size of the array.
 * The second line of input contains N space-separated integers, representing the array elements.
 *
 * Output
 * The updated array after moving 0s to the end of it.
 *
 * Constraints
 * 1 <= N <= 104
 * -231 <= nums[i] <= 231 - 1
 * 5
 * 0 1 0 3 12Example #1 Input
 * 1 3 12 0 0Output
 * Example #2 Input
 * 0
 * 1
 * 0Output
 */

fun moveZeroesToEnd(nums: IntArray): Unit {

    // "Everything to the left of insertPos is a non-zero in correct relative order"
    var insertPosition = 0

    for (i in nums.indices) {
        if (nums[i] != 0) {
            // Swap optimization: Avoids unnecessary writes when element is already in place (if (i != insertPos))
            if (i != insertPosition) {
                // Swap elements
                val temp = nums[i]
                nums[i] = nums[insertPosition]
                nums[insertPosition] = temp
            }
            insertPosition++
        }
    }
}

fun main() {
    val nums = intArrayOf(0, 1, 0, 3, 12)
    moveZeroesToEnd(nums)
    println(nums.joinToString(" "))
}