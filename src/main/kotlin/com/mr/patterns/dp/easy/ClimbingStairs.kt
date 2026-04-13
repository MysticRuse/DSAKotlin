package com.mr.patterns.dp.easy

/**
 * 70. Climbing Stairs
 * Easy
 * Topics: DP, Memoization
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Example 1:
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * Constraints:
 * 1 <= n <= 45
 */
class ClimbingStairs {

    fun climbStairs(n: Int): Int {
        //return climbStairs_RecursiveDfs(n)
        // return climbStairs_Dp(n)
        return climbStairs_DpSpaceOptimised(n)
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Key Insight (Recurrence)
     * To reach step n, you could have come from:
     * step n-1 (taking 1 step)
     * step n-2 (taking 2 steps)
     * So the total ways to reach n is: ways(n)=ways(n−1)+ways(n−2)
     * This is exactly the Fibonacci pattern.
     *
     * Why We Only Need O(1) Space
     * We only ever need the last 2 computed values:
     *      - ways(i-1)
     *      - ways(i-2)
     * So we store:
     *      - prev2 = ways(i-2)
     *      - prev1 = ways(i-1)
     *      - curr = prev1 + prev2
     * Then shift forward.
     */
    private fun climbStairs_DpSpaceOptimised(n: Int): Int {
        if ( n <= 2 ) return n
        var prev2 = 1 // ways(1)
        var prev1 = 2 // ways(2)
        for (i in 3..n) {
            val curr = prev1 + prev2
            prev2 = prev1
            prev1 = curr
        }
        return prev1
    }


    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 1. Create an array dp of size n+1 to store the results of subproblems.
     * 2. Base cases: dp[0] = 1, dp[1] = 1
     * 3. Recursive case: dp[i] = dp[i-1] + dp[i-2]
     * 4. Return dp[n]
     */
    private fun climbStairs_Dp(n: Int): Int {
        val dp = IntArray(n+1)
        // Base cases:
        dp[0] = 1
        dp[1] = 1
        for (i in 2..n) {
            // Recursive case: dp[i] = dp[i-1] + dp[i-2]
            dp[i] = dp[i-1] + dp[i-2]
        }
        return dp[n]
    }

    /**
     * Time Complexity: O(2^n)
     * Space Complexity: O(n)
     */
    private fun climbStairs_RecursiveDfs(n: Int): Int {
        if (n <= 3) {
            // n = 1, way = 1
            // n = 2, ways = 1+1, 2
            // n = 3, ways = 1+1+1, 1+2, 2+1
            return n;
        }

        return climbStairs_RecursiveDfs(n-1) + climbStairs_RecursiveDfs(n-2)
    }


}

// ---------------- MAIN FUNCTION WITH TESTS ----------------

fun main() {

    var totalTests = 0
    var passedTests = 0
    var failedTests = 0

    fun runTest(testName: String, n: Int, expected: Int) {
        totalTests++

        val solver = ClimbingStairs()
        val result = solver.climbStairs(n)

        if (result == expected) {
            passedTests++
            println("✅ PASS: $testName | n=$n, Expected=$expected, Got=$result")
        } else {
            failedTests++
            println("❌ FAIL: $testName | n=$n, Expected=$expected, Got=$result")
        }
    }

    // ---------------- TEST CASES ----------------

    // 1) Minimum input
    runTest("n=1 (Base Case)", 1, 1)

    // 2) Second base case
    runTest("n=2 (Base Case)", 2, 2)

    // 3) Small input
    runTest("n=3", 3, 3)

    // 4) Standard example
    runTest("n=4", 4, 5)

    // 5) Slightly larger
    runTest("n=5", 5, 8)

    // 6) Medium
    runTest("n=10", 10, 89)

    // 7) Large input (upper constraints typical in LeetCode)
    runTest("n=20", 20, 10946)

    // 8) Bigger stress test
    runTest("n=30", 30, 1346269)

    // ---------------- SUMMARY ----------------
    println("\n==================== TEST SUMMARY ====================")
    println("Total Tests  : $totalTests")
    println("Passed Tests : $passedTests")
    println("Failed Tests : $failedTests")
    println("======================================================")
}