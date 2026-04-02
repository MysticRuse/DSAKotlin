package com.mr.patterns.slidingwindow.medium


/**
 * 53. Maximum Subarray
 * Medium
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 *
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 * Constraints:
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
class MaximumSubarray {

    fun maxSubArray(nums: IntArray): Int {
        return maxSubArray_Kadanes(nums)
        //return maxSubArray_Kadanes_Simple(nums)
    }

    fun maxSubArray_Kadanes(nums: IntArray): Int {
        // !!!IMP to add this empty check - else an edge case with an empty array will fail
        if (nums.isEmpty()) return 0

        var subArraySum = nums[0]
        var maxSoFar = nums[0]

        // If initialized with nums[0] start checking with index 1
        for (i in 1 until nums.size) {
            // Pick the max of current number or current number + last subarray sum
            subArraySum = maxOf(subArraySum + nums[i], nums[i])
            maxSoFar = maxOf(maxSoFar, subArraySum)
        }

        return maxSoFar
    }

    /**
     * Kadane's Algorithm.
     *  - Keep track of the max sum seen so far and the current sum.
     *  - If the current sum is negative, reset it to 0.
     *  - Otherwise, add the current number to the sum.
     * Return the max sum seen so far.
     * Time Complexity: O(n) Space Complexity: O(1)
     */
    fun maxSubArray_Kadanes_Simple(nums: IntArray): Int {
        // !!!IMP to add this check - else an edge case with an empty array will fail
        if (nums.isEmpty()) return 0

        var maxSoFar = Int.MIN_VALUE // Or can also init with nums[0]
        var currentSum = 0

        for (num in nums) {
            // First, check the currentSum for negative value. Then add the current num to it.
            if (currentSum < 0) currentSum = 0
            currentSum += num
            maxSoFar = maxOf(maxSoFar, currentSum)
        }

        return maxSoFar
    }

}

fun main() {
    data class TestCase(val name: String, val nums: IntArray, val expected: Int)

    val testCases = listOf(
        // LeetCode examples
        TestCase("Example 1 - classic mixed", intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4), 6),
        TestCase("Example 2 - single element", intArrayOf(1), 1),
        TestCase("Example 3 - whole array is best", intArrayOf(5, 4, -1, 7, 8), 23),
        // Edge cases
        TestCase("Empty array", intArrayOf(), 0),
        TestCase("All negative", intArrayOf(-3, -2, -1), -1),
        TestCase("Single negative", intArrayOf(-5), -5),
        TestCase("Mixed - reset and climb", intArrayOf(3, -2, 5), 6),
        TestCase("Two positives with dip", intArrayOf(-2, 1), 1),
    )

    println("=== Maximum Subarray ===\n")

    var passed = 0
    val solution = MaximumSubarray()
    for ((index, test) in testCases.withIndex()) {
        val result = solution.maxSubArray(test.nums)
        val success = result == test.expected

        val status = if (success) "✅ PASS" else "❌ FAIL"
        println("Test ${index + 1}: ${test.name}")
        println("  Input:    ${test.nums.contentToString()}")
        println("  Expected: ${test.expected}")
        println("  Actual:   $result")
        println("  Status:   $status\n")

        if (success) passed++
    }

    println("=== Results: $passed/${testCases.size} tests passed ===")
}