package com.mr.patterns.slidingwindow.easy

/**
 * 219. Contains Duplicate II
 * Easy
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 * Example 1:
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * Example 2:
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -109 <= nums[i] <= 10^9
 * 0 <= k <= 10^5
 */
class ContainsDuplicateII {
    fun containsNearbyDuplicates(nums: IntArray, k: Int): Boolean {
        val set = HashSet<Int>()
        for (i in nums.indices) {
            if (set.contains(nums[i])) {
                return true
            }
            set.add(nums[i])
            if (set.size > k) {
                set.remove(nums[i-k])
            }
        }
        return false
    }

    fun containsDuplicates(nums: IntArray):Boolean {
        val countSet = mutableSetOf<Int>()

        for ( num in nums) {
            if (countSet.contains(num)) return true
            countSet.add(num)
        }
        return false
    }
}

fun main() {
    val testCases = listOf(
        Triple(intArrayOf(1, 2, 3, 1), 3, true),      // duplicate within k
        Triple(intArrayOf(1, 0, 1, 1), 1, true),      // duplicate within 1
        Triple(intArrayOf(1, 2, 3, 1, 2, 3), 2, false), // duplicates exist but too far
        Triple(intArrayOf(1, 2, 3, 4, 5), 3, false),  // no duplicates
        Triple(intArrayOf(1, 1), 1, true),            // immediate duplicate
        Triple(intArrayOf(1, 2, 1), 1, false),        // duplicate but distance=2 > 1
        Triple(intArrayOf(1, 2, 1), 2, true),         // duplicate distance=2 <= 2
        Triple(intArrayOf(99), 5, false),             // single element
        Triple(intArrayOf(1, 2, 3, 4, 1), 3, false),  // duplicate too far
        Triple(intArrayOf(1, 2, 3, 4, 1), 4, true)    // duplicate within k=4
    )

    var passed = 0
    var failed = 0

    for ((index, test) in testCases.withIndex()) {
        val (nums, k, expected) = test
        val result = ContainsDuplicateII().containsNearbyDuplicates(nums, k)

        val isPass = result == expected
        if (isPass) passed++ else failed++

        println(
            "Test #${index + 1}: nums=${nums.contentToString()}, k=$k | " +
                    "Expected=$expected, Got=$result | ${if (isPass) "PASS" else "FAIL"}"
        )
    }

    println("\n========== TEST SUMMARY ==========")
    println("Total Tests : ${testCases.size}")
    println("Passed      : $passed")
    println("Failed      : $failed")
    println("==================================")
}