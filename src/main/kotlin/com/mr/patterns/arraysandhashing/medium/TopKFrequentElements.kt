package com.mr.patterns.arraysandhashing.medium

/**
 * 347. Top K Frequent Elements
 * Medium
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 * Example 3:
 * Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
 * Output: [1,2]
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
class TopKFrequentElements {

    fun topKFrequent(nums: IntArray, k: Int): List<Int> {

        // 1. Count freq of each number
        val freqMap = HashMap<Int, Int>()
        for (num in nums) {
            freqMap[num] = freqMap.getOrDefault(num, 0) + 1
        }

        // 2. Create buckets where index = frequency, each bucket holds list of numbers with that frequency
        val freqBucket = Array<MutableList<Int>>(nums.size + 1) { mutableListOf() }
        for ((num, freq) in freqMap) {
            freqBucket[freq].add(num)
        }

        // Traverse buckets of frequencies in reverse order and get top K elements
        val result = mutableListOf<Int>()
        for ( i in freqBucket.lastIndex downTo 0) {
            result.addAll(freqBucket[i])
            if (result.size > k) break
        }

        return result.take(k).toList()
    }
}

fun main() {
    val solver = TopKFrequentElements()

    // Helper: compare ignoring order
    fun sameElements(a: List<Int>, b: List<Int>): Boolean {
        return a.sorted() == b.sorted()
    }

    val testCases = listOf(
        Triple(intArrayOf(1, 1, 1, 2, 2, 3), 2, listOf(1, 2)),          // standard
        Triple(intArrayOf(1), 1, listOf(1)),                            // single element
        Triple(intArrayOf(4, 4, 4, 6, 6, 7), 1, listOf(4)),             // k=1
        Triple(intArrayOf(5, 5, 6, 6, 7, 7), 2, listOf(5, 6)),          // equal frequencies (any 2 valid)
        Triple(intArrayOf(-1, -1, -2, -2, -2, -3), 2, listOf(-2, -1)),  // negatives
        Triple(intArrayOf(9, 9, 9, 9, 8, 8, 7), 3, listOf(9, 8, 7)),    // all unique in top3
        Triple(intArrayOf(1, 2, 3, 4, 5), 5, listOf(1, 2, 3, 4, 5)),    // k = n
        Triple(intArrayOf(2, 2, 3, 3, 3, 4), 2, listOf(3, 2)),          // different frequencies
        Triple(intArrayOf(10, 10, 20, 30, 30, 30), 1, listOf(30)),      // most frequent is 30
        Triple(intArrayOf(100, 100, 200, 200, 300), 2, listOf(100, 200))// tie between 100 and 200
    )

    var passed = 0
    var failed = 0

    for ((index, test) in testCases.withIndex()) {
        val (nums, k, expected) = test

        val result = solver.topKFrequent(nums, k)

        // For cases with equal frequency ties, allow any valid output of size k
        val isPass = result.size == k && sameElements(result, expected)

        if (isPass) passed++ else failed++

        println(
            "Test #${index + 1}: nums=${nums.contentToString()}, k=$k | " +
                    "Expected=$expected | Got=$result | ${if (isPass) "PASS" else "FAIL"}"
        )
    }

    println("\n========== TEST SUMMARY ==========")
    println("Total Tests : ${testCases.size}")
    println("Passed      : $passed")
    println("Failed      : $failed")
    println("==================================")
}