package com.mr.patterns.slidingwindow.easy

/**
 * 121. Best Time to Buy and Sell Stock
 * Easy
 * Topics: Array, DP
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 * Example 2:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 * Constraints:
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^4
 */
class BestTimeToBuySellStocks {

    fun maxProfit(prices: IntArray): Int {
        var maxProf = 0
        var buyPrice = prices[0]

        for (sellDay in 1 until prices.size) {
            val sellPrice = prices[sellDay]
            maxProf = maxOf(maxProf, sellPrice - buyPrice)
            buyPrice = minOf(buyPrice, sellPrice)
        }

        return maxProf
    }
}

fun main() {
    val solver = BestTimeToBuySellStocks()

    val testCases = listOf(
        Pair(intArrayOf(7, 1, 5, 3, 6, 4), 5),      // classic example
        Pair(intArrayOf(7, 6, 4, 3, 1), 0),         // strictly decreasing
        Pair(intArrayOf(1, 2, 3, 4, 5), 4),         // strictly increasing
        Pair(intArrayOf(5), 0),                     // single element
        Pair(intArrayOf(2, 4, 1), 2),               // buy at 2 sell at 4
        Pair(intArrayOf(3, 3, 3, 3), 0),            // all equal
        Pair(intArrayOf(2, 1, 2, 1, 0, 1, 2), 2),   // multiple valleys
        Pair(intArrayOf(10, 9, 8, 7, 20), 13),      // profit at end
        Pair(intArrayOf(1, 10, 1, 10), 9),          // multiple peaks
        Pair(intArrayOf(6, 1, 3, 2, 4, 7), 6)       // best is 1 -> 7
    )

    var passed = 0
    var failed = 0

    for ((index, test) in testCases.withIndex()) {
        val input = test.first
        val expected = test.second

        val result = solver.maxProfit(input)
        val isPass = result == expected

        if (isPass) passed++ else failed++

        println(
            "Test #${index + 1}: prices=${input.contentToString()} | " +
                    "Expected=$expected, Got=$result | ${if (isPass) "PASS" else "FAIL"}"
        )
    }

    println("\n========== TEST SUMMARY ==========")
    println("Total Tests : ${testCases.size}")
    println("Passed      : $passed")
    println("Failed      : $failed")
    println("==================================")
}