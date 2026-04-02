package com.mr.patterns.twopointers.medium

/**
 * 15. 3Sum Medium
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 *
 * Example 2:
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 *
 * Example 3:
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 *
 * Constraints:
 *
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */


fun threeSum(nums: IntArray): List<List<Int>> {
    //return threeSumHandleDuplicates(nums)
    return threeSumWithHashSet(nums)
}

/**
 * Notes:
 * 1. Using 2 pointers.
 * 2. Check for n < 3 and return empty list
 * 3. Sort the array first
 * 4. Use a HashSet to store the triplet list results.
 *      - automatically takes care of duplicates.
 *
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 */
fun threeSumWithHashSet(nums: IntArray): List<List<Int>> {
    val result = mutableSetOf<List<Int>>()
    val n = nums.size
    if (n < 3)  return result.toList()

    nums.sort()
    for (i in nums.indices) {
        var left = i+1
        var right = n-1

        while (left < right) {
            val sum = nums[i] + nums[left] + nums[right]

            if (sum == 0) {
                result.add(listOf(nums[i], nums[left], nums[right]))
                left++
                right--
            } else if (sum < 0) {
                left++
            } else { // sum > 0
                right--
            }
        }
    }

    return result.toList()
}

/**
 * Notes:
 * 1. Using 2 pointers.
 * 2. Check for n < 3 and return empty list
 * 3. Have to sort the array first
 * 4. If the sum is found, add the triplet to the result list.
 * 5. If the sum is not found, move the left pointer to the right.
 * 6. If the sum is found, move the right pointer to the left.
 *
 * 8. Time complexity: O(n^2)
 * 9. Space complexity: O(1)
 *
 */
fun threeSumHandleDuplicates(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val n = nums.size
    if (n < 3)  return result

    nums.sort()

    for (i in nums.indices) {
        // To remove duplicates in the 1st position
        if (i > 0 && nums[i] == nums[i-1]) {
            continue
        }

        var left = i + 1
        var right = nums.size - 1

        while (left < right) {

            val sum = nums[i] + nums[left] + nums[right]
            if (sum == 0) {
                result.add(listOf(nums[i], nums[left], nums[right]))
                left++
                right--

                // To remove duplicates
                while (left < right && nums[left] == nums[left - 1]) left++
                while (left < right && nums[right] == nums[right + 1]) right--
            } else if (sum < 0) {
                left++
            } else {
                right--
            }
        }
    }

    return result
}


fun main() {
    data class TestCase(val name: String, val nums: IntArray, val expected: List<List<Int>>)

    fun assertTripletsEqual(expected: List<List<Int>>, actual: List<List<Int>>): Boolean {
        val expectedSorted = expected.map { it.sorted() }.sortedBy { it.toString() }
        val actualSorted = actual.map { it.sorted() }.sortedBy { it.toString() }
        return expectedSorted == actualSorted
    }

    val testCases = listOf(
        // General cases
        TestCase(
            "Standard case with duplicates",
            intArrayOf(-1, 0, 1, 2, -1, -4),
            listOf(listOf(-1, -1, 2), listOf(-1, 0, 1))
        ),
        TestCase(
            "Multiple triplets",
            intArrayOf(-2, -1, 0, 1, 2, 3),
            listOf(listOf(-2, -1, 3), listOf(-2, 0, 2), listOf(-1, 0, 1))
        ),
        TestCase(
            "Three zeros",
            intArrayOf(0, 0, 0),
            listOf(listOf(0, 0, 0))
        ),

        // Edge cases
        TestCase(
            "Exactly three elements - valid",
            intArrayOf(-1, 0, 1),
            listOf(listOf(-1, 0, 1))
        ),
        TestCase(
            "Exactly three elements - invalid",
            intArrayOf(1, 2, 3),
            emptyList()
        ),
        TestCase(
            "Many duplicates",
            intArrayOf(-2, -2, 0, 0, 2, 2),
            listOf(listOf(-2, 0, 2))
        ),

        // No solution cases
        TestCase(
            "All positive - no solution",
            intArrayOf(1, 2, 3, 4, 5),
            emptyList()
        ),
        TestCase(
            "All negative - no solution",
            intArrayOf(-5, -4, -3, -2, -1),
            emptyList()
        ),
        TestCase(
            "No valid triplet",
            intArrayOf(0, 1, 1),
            emptyList()
        )
    )

    println("=== 3Sum Test Cases ===\n")

    var passed = 0
    for ((index, test) in testCases.withIndex()) {
        val result = threeSum(test.nums)
        val success = assertTripletsEqual(test.expected, result)

        val status = if (success) "✅ PASS" else "❌ FAIL"
        println("Test ${index + 1}: ${test.name}")
        println("  Input:    ${test.nums.toList()}")
        println("  Expected: ${test.expected}")
        println("  Actual:   $result")
        println("  Status:   $status\n")

        if (success) passed++
    }

    println("=== Results: $passed/${testCases.size} tests passed ===")
}

