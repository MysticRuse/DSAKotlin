package com.mr.patterns.stacks.medium

/**
 * 739. Daily Temperatures
 * Medium
 * Topics: Arrays, Stack, Monotonic Stack, DP
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that
 * answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 * Example 1:
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Example 2:
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * Example 3:
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 *
 * Constraints:
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
class DailyTemperature {

    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val result = IntArray(temperatures.size) { 0 } // Init with zeros

        // Monotonically decreasing stack
        val monoDecreasingStack = ArrayDeque<Int>() // Contains the indices of temperatures in decreasing order

        for ( i in temperatures.indices) {
            while (monoDecreasingStack.isNotEmpty()
                && temperatures[i] > temperatures[monoDecreasingStack.last()]) {

                val topStackIndex = monoDecreasingStack.removeLast()
                result[topStackIndex] = i - topStackIndex
            }
            // Over here, temperatures[i] is less than or equal to the top of the stack.
            // So, push it's index to the stack.
            monoDecreasingStack.addLast(i)
        }
        return result

    }
}

fun main() {
    val solver = DailyTemperature()

    val testCases = listOf(
        intArrayOf(73, 74, 75, 71, 69, 72, 76, 73) to intArrayOf(1, 1, 4, 2, 1, 1, 0, 0),
        intArrayOf(30, 40, 50, 60) to intArrayOf(1, 1, 1, 0),
        intArrayOf(60, 50, 40, 30) to intArrayOf(0, 0, 0, 0),
        intArrayOf(70) to intArrayOf(0),
        intArrayOf(70, 70, 70) to intArrayOf(0, 0, 0),
        intArrayOf(70, 71, 70, 71) to intArrayOf(1, 0, 1, 0),
        intArrayOf(90, 80, 70, 60, 50, 100) to intArrayOf(5, 4, 3, 2, 1, 0),
        intArrayOf(50, 60, 55, 65, 60, 70) to intArrayOf(1, 2, 1, 2, 1, 0)
    )

    var passed = 0
    var failed = 0

    for ((index, testCase) in testCases.withIndex()) {
        val (input, expected) = testCase
        val result = solver.dailyTemperatures(input)

        val isPass = result.contentEquals(expected)

        if (isPass) passed++ else failed++

        println(
            "Test #${index + 1}: " +
                    "Input: ${input.contentToString()} | " +
                    "Expected: ${expected.contentToString()} | " +
                    "Got: ${result.contentToString()} | " +
                    (if (isPass) "PASS" else "FAIL")
        )
    }

    println("\n========== TEST SUMMARY ==========")
    println("Total Tests : ${testCases.size}")
    println("Passed      : $passed")
    println("Failed      : $failed")
    println("==================================")
}

