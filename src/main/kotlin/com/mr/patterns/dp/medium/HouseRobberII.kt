package com.mr.patterns.dp.medium

/**
 * 213. House Robber II
 * Medium
 * Topics
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two
 * adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you
 * can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * Example 2:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 3:
 * Input: nums = [1,2,3]
 * Output: 3
 *
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
class HouseRobberII {

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]

        // Since circular:
        // Case 1: Exclude first - rob from house 1 to n-1
        // case 2: Exclude last - rob from house 0 to n-2
        // Pick the max of the 2
        val case1 = robLinear(nums, 0, nums.size - 2)
        val case2 = robLinear(nums, 1, nums.size - 1)

        return maxOf(case1, case2)
    }

    private fun robLinear(nums: IntArray, start: Int, end: Int): Int {
        var t2 = 0 // dp[i-2]
        var t1 = 0 // dp[i-1]

        for ( i in start..end) {
            val current = maxOf(t1, t2 + nums[i])
            t2 = t1
            t1 = current
        }

        return t1
    }
}

// ---------------- MAIN FUNCTION WITH TESTS + SUMMARY ----------------

fun main() {

    var totalTests = 0
    var passedTests = 0
    var failedTests = 0

    fun runTest(testName: String, nums: IntArray, expected: Int) {
        totalTests++

        val solver = HouseRobberII()
        val result = solver.rob(nums)

        if (result == expected) {
            passedTests++
            println("✅ PASS: $testName | Houses=${nums.contentToString()}, Expected=$expected, Got=$result")
        } else {
            failedTests++
            println("❌ FAIL: $testName | Houses=${nums.contentToString()}, Expected=$expected, Got=$result")
        }
    }

    // ---------------- TEST CASES ----------------

    // 1) Empty houses
    runTest(
        testName = "Empty Houses",
        nums = intArrayOf(),
        expected = 0
    )

    // 2) Single house
    runTest(
        testName = "Single House",
        nums = intArrayOf(5),
        expected = 5
    )

    // 3) Two houses (pick max)
    runTest(
        testName = "Two Houses",
        nums = intArrayOf(2, 3),
        expected = 3
    )

    // 4) LeetCode example 1
    runTest(
        testName = "LeetCode Example 1",
        nums = intArrayOf(2, 3, 2),
        expected = 3
    )

    // 5) LeetCode example 2
    runTest(
        testName = "LeetCode Example 2",
        nums = intArrayOf(1, 2, 3, 1),
        expected = 4
    )

    // 6) Circular effect matters
    runTest(
        testName = "Circular Constraint Matters",
        nums = intArrayOf(5, 1, 1, 5),
        expected = 6
        // Cannot take both 5s because they are adjacent in circular sense
        // Best = 5 + 1 = 6
    )

    // 7) All same values
    runTest(
        testName = "All Same Values",
        nums = intArrayOf(4, 4, 4, 4),
        expected = 8
    )

    // 8) Increasing values
    runTest(
        testName = "Increasing Values",
        nums = intArrayOf(1, 2, 3, 4, 5),
        expected = 8
        // Best = 3 + 5 = 8 (cannot take 1 and 5 together)
    )

    // 9) Decreasing values
    runTest(
        testName = "Decreasing Values",
        nums = intArrayOf(9, 8, 7, 6, 5),
        expected = 16
        // Best = 9 + 7 = 16 (can't take 9 and 5 together)
    )

    // 10) Large peak in middle
    runTest(
        testName = "Large Peak in Middle",
        nums = intArrayOf(1, 1, 50, 1, 1),
        expected = 51
        // Best is 50 + 1 = 51 (can't take both ends with circular constraint)
    )

    // 11) Random mixed values
    runTest(
        testName = "Random Mixed Values",
        nums = intArrayOf(2, 7, 9, 3, 1),
        expected = 11
        // Case1 (0..3) => [2,7,9,3] best = 11
        // Case2 (1..4) => [7,9,3,1] best = 10
    )

    // 12) All zeros
    runTest(
        testName = "All Zeros",
        nums = intArrayOf(0, 0, 0, 0),
        expected = 0
    )

    // ---------------- SUMMARY ----------------
    println("\n==================== TEST SUMMARY ====================")
    println("Total Tests  : $totalTests")
    println("Passed Tests : $passedTests")
    println("Failed Tests : $failedTests")
    println("======================================================")
}