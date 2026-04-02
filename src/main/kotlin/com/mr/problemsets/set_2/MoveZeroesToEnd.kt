package com.mr.problemsets.set_2

/**
 * 283. Move Zeroes
 * Easy
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * Note that you must do this in-place without making a copy of the array.
 *
 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 *
 * Constraints:
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 * Follow up: Could you minimize the total number of operations done?
 */

fun moveZeroesToEnd(nums: IntArray): Unit {

    // "Everything to the left of insertPos is a non-zero in correct relative order"
    var insertPosition = 0

    for (i in nums.indices) {
        if (nums[i] != 0) {
            // Swap optimization:
            // Avoids unnecessary writes when an element is already in place (if (i != insertPosition))
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