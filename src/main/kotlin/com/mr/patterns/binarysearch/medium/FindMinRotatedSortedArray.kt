package com.mr.patterns.binarysearch.medium

/**
 * 153. Find Minimum in Rotated Sorted Array
 * Medium
 * Topics: Arrays, Binary Search
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 * You must write an algorithm that runs in O(log n) time.
 * Example 1:
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 * Example 3:
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 * Constraints:
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * All the integers of nums are unique.
 * nums is sorted and rotated between 1 and n times.
 */

/**
 * Time Complexity : Same as Binary Search O(logN)
 * Space Complexity : O(1)
 */
class FindMinRotatedSortedArray {

    fun findMin(nums: IntArray): Int {
        var left = 0
        var right = nums.size - 1

        while (left < right) {
            val mid = left + (right - left) / 2

            if (nums[mid] > nums[right]) {
                // min in rotated side on the right. Bring in left
                left = mid + 1
            } else {
                //min in rotated side on the left. Bring in right
                right = mid
            }
        }

        // nums at index left is the min - why? Because at the end of the loop, left and right converge to the minimum element.
        return nums[left]
    }
}

fun main() {
    val solver = FindMinRotatedSortedArray()
    println(solver.findMin(intArrayOf(3,4,5,1,2)))                  // 1
    println(solver.findMin(intArrayOf(4,5,6,7,0,1,2)))              // 0
    println(solver.findMin(intArrayOf(11,13,15,17)))                // 11
    println(solver.findMin(intArrayOf(11,13,15,17,19,20,21)))       // 11
    println(solver.findMin(intArrayOf(11,13,15,17,19,20,21,22)))    // 11
}
/**
 * Algorithm:
 * 1. Find the mid point of the array.
 * 2. If the mid point is greater than the right side, then the minimum element is in the rotated side on the right.
 * 3. If the mid point is less than the right side, then the minimum element is in the rotated side on the left.
 * 4. Repeat step 2 and 3 until the left and right pointers converge.
 */