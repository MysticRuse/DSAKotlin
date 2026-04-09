package com.mr.patterns.binarysearch.easy

/**
 * 704. Binary Search
 * Easy
 * Topics - Array, Binary Search
 * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1
 *
 * You must write an algorithm with O(log n) runtime complexity.
 * Example 1:
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * Example 2:
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 * Constraints:

 * 1 <= nums.length <= 104
 * -104 < nums[i], target < 104
 * All the integers in nums are unique.
 * nums is sorted in ascending order.
 */

/**
 * Time Complexity : O(logN)
 * Space Complexity : O(1)
 */
class BinarySearch {

    fun binarySearch(nums: IntArray, target: Int) : Int {
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (nums[mid] == target) return mid
            if (nums[mid] < target) left = mid + 1
            else right = mid - 1
        }
        return -1
    }
}

fun main() {
    val solver = BinarySearch()
    println(solver.binarySearch(intArrayOf(1,2,3,4,5,6,7,8,9,10), 5))
    println(solver.binarySearch(intArrayOf(1,2,3,4,5,6,7,8,9,10), 11))
    println(solver.binarySearch(intArrayOf(1,2,3,4,5,6,7,8,9,10), 0))
    println(solver.binarySearch(intArrayOf(1,2,3,4,5,6,7,8,9,10), 1))
    println(solver.binarySearch(intArrayOf(1,2,3,4,5,6,7,8,9,10), 10))
}

/**
 * Algorithm:
 * 1. Initialize left and right pointers to the beginning and end of the array respectively.
 * 2. Compare the middle element with the target.
 * 3. If the middle element is equal to the target, return its index.
 * 4. If the middle element is less than the target, move the left pointer to the middle element + 1.
 * 5. If the middle element is greater than the target, move the right pointer to the middle element - 1.
 * 6. Repeat steps 2-5 until the left pointer reaches the right pointer.
 * 7. If the left pointer reaches the right pointer without finding the target, return -1.
 */