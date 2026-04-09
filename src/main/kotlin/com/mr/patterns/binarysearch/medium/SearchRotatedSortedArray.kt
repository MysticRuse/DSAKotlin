package com.mr.patterns.binarysearch.medium

/**
 * 33. Search in Rotated Sorted Array
 * Medium
 * Topics
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * Example 3:
 *
 * Input: nums = [1], target = 0
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -104 <= target <= 104
 */

/**
 * Complexity Analysis
 * Let n be the length of nums.
 * Time complexity: O(logn)
 *  - This algorithm only requires one binary search over nums.
 * Space complexity: O(1)
 *  -  We only need to update several parameters left, right and mid, which takes O(1) space.
 */
class SearchRotatedSortedArray {

    fun search(nums: IntArray, target: Int): Int {

        var left = 0
        var right = nums.size - 1

        while (left <= right) {
            val mid = left + (right - left) / 2

            // Case 1: find target
            if (nums[mid] == target) return mid

            // Case 2: subarray on mid's left is sorted
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    // Target lies between left and mid, binary search there. Update right to mid - 1
                    right = mid - 1
                } else {
                    // search from right of mid to end. update left to mid + 1
                    left = mid + 1
                }
            } else {
                // Case 3: Subarray on mid's right is sorted
                if (target > nums[mid] && target <= nums[right]) {
                    // Target lies between mid and right - binary search there. Adjust left to mid + 1
                    left = mid + 1
                } else {
                    // search from left of mid to left. update right to mid - 1
                    right = mid - 1
                }
            }
        }

        return -1
    }
}

fun main() {
    val nums = intArrayOf(4,5,6,7,0,1,2)
    val target = 0
    val solver = SearchRotatedSortedArray()
    println(solver.search(nums, target))            // 4

    val nums2 = intArrayOf(4,5,6,7,0,1,2)
    val target2 = 3
    println(solver.search(nums2, target2))          // -1

    val nums3 = intArrayOf(1)
    val target3 = 0
    println(solver.search(nums3, target3))          // -1

    val nums4 = intArrayOf(1,2,3,4,5,6,7,8,9,10)
    val target4 = 1
    println(solver.search(nums4, target4))          // 0

    val nums5 = intArrayOf(1,2,3,4,5,6,7,8,9,10)
    val target5 = 10
    println(solver.search(nums5, target5))          // 9
}

/**
 * Algorithmic reasoning
 * 1. If the left subarray is sorted, then the target must lie between left and mid.
 * 2. If the right subarray is sorted, then the target must lie between mid and right.
 * 3. If the left subarray is sorted and the right subarray is sorted, then the target must lie between mid and right.
 *
 * Algorithm
 * Initialize pointers:
 * Set left to 0.
 * Set right to n - 1 where n is the length of the array nums.
 *
 * Perform binary search:
 *
 * While left is less than or equal to right:
 *      Calculate the middle index mid as left + (right - left) / 2.
 *
 *      Case 1: Check if the middle element nums[mid] is equal to target.
 *      If true, return mid as the index of target.
 *
 *      Case 2: Check if the subarray from left to mid is sorted (nums[mid] >= nums[left]).
 *      If target is within the range [nums[left], nums[mid]):
 *          Adjust the search range by setting right to mid - 1.
 *      Otherwise:
 *          Adjust the search range by setting left to mid + 1.
 *
 *      Case 3: If the subarray from mid to right is sorted (nums[mid] > nums[right]):
 *          If target is within the range (nums[mid], nums[right]):
 *              Adjust the search range by setting left to mid + 1.
 *          Otherwise:
 *              Adjust the search range by setting right to mid - 1.
 *      If the target is not found, return -1.
 */