package com.mr.problemsets.set_1

/**
 * ✅ Problem 3 (Medium) — Count Subarrays with Sum K
 * Given an array of integers and an integer k, count the number of continuous subarrays whose sum equals k.
 * Input
 * First line: n
 * Second line: n integers
 * Third line: integer k
 * Output
 * Print the count.
 * Constraints
 * 1 ≤ n ≤ 200000
 * -10^4 ≤ arr[i] ≤ 10^4
 * -10^9 ≤ k ≤ 10^9
 * Example
 * Input
 * 5
 * 1 2 3 -2 2
 * 3
 * Output
 * 3
 * Explanation: subarrays = [1,2], [3], [3,-2,2]
 */


/**
 * This is a classic prefix sum + HashMap problem:
 * Approach                       Time         Space
 * Brute force (all subarrays)    O(n²)        O(1)
 * Prefix sum + HashMap           O(n)         O(n)
 * The key insight: if prefixSum[j] - prefixSum[i] = k, then the subarray [i+1...j] sums to k.
 */
fun countSubArraysWithSumK(arr: List<Int>, k: Int): Int {
    var count = 0
    var sum = 0
    val map = mutableMapOf(0 to 1)
    for (num in arr) {
        sum += num
        count += map.getOrDefault(sum - k, 0)
        map[sum] = map.getOrDefault(sum, 0) + 1
    }
    return count
}

fun main() {
    val n = readln().toInt()
    val arr = readln().split(" ").map { it.toInt() }
    val k = readln().toInt()
    println(countSubArraysWithSumK(arr, k))
}