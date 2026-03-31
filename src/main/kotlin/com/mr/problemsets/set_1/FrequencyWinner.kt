package com.mr.problemsets.set_1

import kotlin.collections.iterator

/**
 * ✅ Problem 1 (Easy) - Frequency Winner
 * You are given an integer array. Print the number that occurs maximum times.
 * If multiple numbers have the same maximum frequency, print the smallest number.
 * Input
 * First line: integer n
 * Second line: n integers
 * Output
 * Print the number.
 * Constraints
 * 1 ≤ n ≤ 200000
 * -10^9 ≤ arr[i] ≤ 10^9
 * Example
 * Input
 * 7
 * 4 2 2 8 4 2 8
 * Output
 * 2
 */

fun findFrequencyWinner(arr: List<Int>): Int {
    val frequencyMap = mutableMapOf<Int, Int>()
    for (num in arr) {
        frequencyMap[num] = frequencyMap.getOrDefault(num, 0) + 1
    }
    var maxFreq = 0
    var result = Int.MAX_VALUE
    for ((num, frequency) in frequencyMap) {
        if (frequency > maxFreq || (frequency == maxFreq && num < result)) {
            maxFreq = frequency
            result = num
        }
    }

    return result
}


fun main() {
    val n = readln().toInt()
    val arr = readln().split(" ").map { it.toInt() }
    println(findFrequencyWinner(arr))
}