package com.mr.patterns.dp.medium

/**
 * 198. House Robber
 * Medium
 * Topics: Array, DP, 1D DP
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 *
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
class HouseRobber {

    fun rob(nums: IntArray): Int {
        return rob_simple(nums)
        //return rob_dp(nums)
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private fun rob_dp(nums: IntArray): Int {
        val n = nums.size
        if (n == 0) return 0
        if (n == 1) return nums[0]

        val dp = IntArray(n) {0}
        dp[0] = nums[0]
        dp[1] = maxOf(nums[0], nums[1])
        for (i in 2 until n) {
            // Explanation: Cannot rob consecutive.
            // At i, either rob (nums[i] + max until [i-2]) or only max until [i-1]
            dp[i] = maxOf(dp[i-1], dp[i-2] + nums[i])
        }
        return dp[n-1]
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    private fun rob_simple(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]

        var t2 = 0 // max robbed until i-2
        var t1 = 0 // max robbed until i-1

        // Explanation: Cannot rob consecutive.
        for (money in nums) {

            val current = maxOf(t1, t2 + money)
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

        val solver = HouseRobber()
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

    // 1) Empty input
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

    // 3) Two houses
    runTest(
        testName = "Two Houses",
        nums = intArrayOf(2, 7),
        expected = 7
    )

    // 4) LeetCode example 1
    runTest(
        testName = "LeetCode Example 1",
        nums = intArrayOf(1, 2, 3, 1),
        expected = 4
    )

    // 5) LeetCode example 2
    runTest(
        testName = "LeetCode Example 2",
        nums = intArrayOf(2, 7, 9, 3, 1),
        expected = 12
    )

    // 6) All same values
    runTest(
        testName = "All Same Values",
        nums = intArrayOf(5, 5, 5, 5),
        expected = 10
    )

    // 7) Increasing values
    runTest(
        testName = "Increasing Values",
        nums = intArrayOf(1, 2, 3, 4, 5),
        expected = 9 // 1 + 3 + 5
    )

    // 8) Decreasing values
    runTest(
        testName = "Decreasing Values",
        nums = intArrayOf(9, 8, 7, 6, 5),
        expected = 21 // 9 + 7 + 5
    )

    // 9) Alternate large-small
    runTest(
        testName = "Alternating Large-Small",
        nums = intArrayOf(10, 1, 10, 1, 10),
        expected = 30
    )

    // 10) Random mixed values
    runTest(
        testName = "Random Mixed Values",
        nums = intArrayOf(6, 7, 1, 30, 8, 2, 4),
        expected = 41 // 7 + 30 + 4
    )

    // 11) Houses with zeros
    runTest(
        testName = "Zeros Included",
        nums = intArrayOf(0, 0, 0, 10, 0),
        expected = 10
    )

    // 12) Large single peak in middle
    runTest(
        testName = "Large Peak in Middle",
        nums = intArrayOf(1, 1, 50, 1, 1),
        expected = 52
    )

    // ---------------- SUMMARY ----------------
    println("\n==================== TEST SUMMARY ====================")
    println("Total Tests  : $totalTests")
    println("Passed Tests : $passedTests")
    println("Failed Tests : $failedTests")
    println("======================================================")
}