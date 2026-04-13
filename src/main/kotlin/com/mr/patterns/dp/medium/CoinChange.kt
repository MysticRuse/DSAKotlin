package com.mr.patterns.dp.medium

/**
 * 322. Coin Change
 * Medium
 * Topics: Array, DP, BFS
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 * Constraints:
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 10^4
 */

class CoinChange {

    /**
     * Dynamic Programming Bottom-Up Approach
     * TC: O(N*M) where N is the number of coins and M is the amount
     * SC: O(M)
     *
     * Algorithm:
     * 1. Initialize dp array with max value except dp[0] which is 0
     * 2. Iterate over the amount from 1 to amount
     * 3. For each amount, find the minimum number of coins needed to make up the amount
     *      1. If the amount is greater than the current coin value, skip
     *      2. Otherwise, update the minimum number of coins needed to make up the amount
     * 4. Return the minimum number of coins needed to make up the amount
     *
     */
    fun leastCoins(coins: IntArray, amount: Int): Int {

        val max = amount + 1
        // Maintain a dp array to store the minimum number of coins needed to make up the amount
        // Initialize dp array with max value except dp[0] which is 0
        val dp = IntArray(max) { max }
        dp[0] = 0

        for (currAmt in 1 until max) {
            for (coin in coins) {
                // If the amount is greater than the current coin value, skip
                //println("dp[${i}] = ${dp[i]}" )
                if (currAmt < coin) continue
                dp[currAmt] = minOf(dp[currAmt], dp[currAmt - coin] + 1)
            }
        }
        return if (dp[amount] > amount) -1 else dp[amount]
    }
}

// ---------------- MAIN FUNCTION WITH TESTS ----------------

fun main() {

    var totalTests = 0
    var passedTests = 0
    var failedTests = 0

    fun runTest(testName: String, coins: IntArray, amount: Int, expected: Int) {
        totalTests++

        val solver = CoinChange()
        val result = solver.leastCoins(coins, amount)

        if (result == expected) {
            passedTests++
            println("✅ PASS: $testName | Coins=${coins.contentToString()}, Amount=$amount, Expected=$expected, Got=$result")
        } else {
            failedTests++
            println("❌ FAIL: $testName | Coins=${coins.contentToString()}, Amount=$amount, Expected=$expected, Got=$result")
        }
    }

    // ---------------- TEST CASES ----------------

    // 1) Standard case
    runTest(
        testName = "Standard Example",
        coins = intArrayOf(1, 2, 5),
        amount = 11,
        expected = 3 // 5+5+1
    )

    // 2) Amount = 0
    runTest(
        testName = "Zero Amount",
        coins = intArrayOf(1, 2, 5),
        amount = 0,
        expected = 0
    )

    // 3) Impossible case
    runTest(
        testName = "Impossible Amount",
        coins = intArrayOf(2),
        amount = 3,
        expected = -1
    )

    // 4) Single coin exactly matches
    runTest(
        testName = "Single Coin Exact Match",
        coins = intArrayOf(7),
        amount = 7,
        expected = 1
    )

    // 5) Single coin cannot match
    runTest(
        testName = "Single Coin Cannot Match",
        coins = intArrayOf(7),
        amount = 14,
        expected = 2
    )

    // 6) Single coin cannot make amount
    runTest(
        testName = "Single Coin Cannot Form Amount",
        coins = intArrayOf(7),
        amount = 15,
        expected = -1
    )

    // 7) Coins not including 1, but still possible
    runTest(
        testName = "No Coin of 1 but Possible",
        coins = intArrayOf(3, 4),
        amount = 6,
        expected = 2 // 3+3
    )

    // 8) Large amount
    runTest(
        testName = "Large Amount",
        coins = intArrayOf(1, 2, 5),
        amount = 100,
        expected = 20 // 20*5
    )

    // 9) Coin set where greedy fails but DP succeeds
    runTest(
        testName = "Greedy Fails Case",
        coins = intArrayOf(1, 3, 4),
        amount = 6,
        expected = 2 // 3+3 (greedy might do 4+1+1 = 3)
    )

    // 10) Coins unsorted
    runTest(
        testName = "Unsorted Coins Input",
        coins = intArrayOf(5, 1, 2),
        amount = 11,
        expected = 3
    )

    // 11) Amount smaller than all coins
    runTest(
        testName = "Amount Smaller Than All Coins",
        coins = intArrayOf(5, 10, 25),
        amount = 3,
        expected = -1
    )

    // 12) Multiple optimal solutions possible
    runTest(
        testName = "Multiple Optimal Solutions",
        coins = intArrayOf(2, 3, 5),
        amount = 10,
        expected = 2 // 5+5
    )

    // ---------------- SUMMARY ----------------
    println("\n==================== TEST SUMMARY ====================")
    println("Total Tests  : $totalTests")
    println("Passed Tests : $passedTests")
    println("Failed Tests : $failedTests")
    println("======================================================")
}