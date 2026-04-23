package com.mr.patterns.backtracking.medium

/**
 * 46. Permutations
 * Medium
 * Topics: Backtracking, Recursion
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 * Input: nums = [1]
 * Output: [[1]]
 * Constraints:
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 */
class Permutations {

    val result = mutableListOf<List<Int>>()

    // Use a path to store the current permutation being built.
    // At each level:
    // Try adding unused numbers
    // recurse
    // Backtrack (remove last element)
    fun permute(nums: IntArray): List<List<Int>> {
        val visited = BooleanArray(nums.size)
        val path = mutableListOf<Int>()

        backtrack(nums, visited, path)
        return result

    }
    private fun backtrack(nums: IntArray, visited: BooleanArray, path: MutableList<Int>) {

        // visited array keeps track of numbers already placed in the permutation.
        // When path reaches length = n:
        //     save a copy to result.
        // Backtrack:
        //     for each index i in nums,indices:
        //         mark index as visited
        //         add nums[index] to path
        //         recurse
        //         remove last element
        //         mark number unvisited again
        // Time → O(n! * n) → all permutations explored
        // Space → O(n) recursion stack + O(n) used array
        if (path.size == nums.size) {
            result.add(path.toList())
            return
        }

        for (i in nums.indices) {
            if (visited[i]) continue

            visited[i] = true
            path.add(nums[i])
            backtrack(nums, visited, path)
            path.removeAt(path.lastIndex)
            visited[i] = false
        }
    }
}

fun main() {

    fun normalize(output: List<List<Int>>): Set<List<Int>> {
        return output.toSet()
    }

    fun factorial(n: Int): Int {
        var result = 1
        for (i in 2..n) result *= i
        return result
    }

    fun runTest(testName: String, nums: IntArray, expected: Set<List<Int>>) {
        val solver = Permutations()
        val actual = solver.permute(nums)

        val actualSet = normalize(actual)

        if (actualSet == expected) {
            println("✅ PASS: $testName")
        } else {
            println("❌ FAIL: $testName")
            println("   Input: ${nums.toList()}")
            println("   Expected: $expected")
            println("   Got: $actualSet")
        }
    }

    fun runCountTest(testName: String, nums: IntArray, expectedCount: Int) {
        val solver = Permutations()
        val actual = solver.permute(nums)

        if (actual.size == expectedCount) {
            println("✅ PASS: $testName (Count=$expectedCount)")
        } else {
            println("❌ FAIL: $testName")
            println("   Input: ${nums.toList()}")
            println("   Expected Count: $expectedCount")
            println("   Got Count: ${actual.size}")
            println("   Output: $actual")
        }
    }

    // ---------------- TEST CASES ----------------

    // Test 1: Standard case
    runTest(
        testName = "Permutations of [1,2,3]",
        nums = intArrayOf(1, 2, 3),
        expected = setOf(
            listOf(1, 2, 3),
            listOf(1, 3, 2),
            listOf(2, 1, 3),
            listOf(2, 3, 1),
            listOf(3, 1, 2),
            listOf(3, 2, 1)
        )
    )

    // Test 2: Two elements
    runTest(
        testName = "Permutations of [0,1]",
        nums = intArrayOf(0, 1),
        expected = setOf(
            listOf(0, 1),
            listOf(1, 0)
        )
    )

    // Test 3: Single element
    runTest(
        testName = "Permutations of [5]",
        nums = intArrayOf(5),
        expected = setOf(
            listOf(5)
        )
    )

    // Test 4: Empty array
    runTest(
        testName = "Permutations of []",
        nums = intArrayOf(),
        expected = setOf(
            emptyList()
        )
    )

    // Test 5: Count test for 4 elements (should be 4! = 24)
    val nums4 = intArrayOf(1, 2, 3, 4)
    runCountTest(
        testName = "Permutations count of [1,2,3,4]",
        nums = nums4,
        expectedCount = factorial(nums4.size)
    )

    // Test 6: Negative numbers
    runTest(
        testName = "Permutations of [-1,-2,3]",
        nums = intArrayOf(-1, -2, 3),
        expected = setOf(
            listOf(-1, -2, 3),
            listOf(-1, 3, -2),
            listOf(-2, -1, 3),
            listOf(-2, 3, -1),
            listOf(3, -1, -2),
            listOf(3, -2, -1)
        )
    )

    println("\n====================================")
    println("SUMMARY")
    println("====================================")
    println("Approach: Backtracking + visited[]")
    println("At each step, pick an unused number and recurse.")
    println("When path.size == nums.size, store the permutation.")
    println()
    println("Time Complexity: O(n * n!)")
    println("  - There are n! permutations")
    println("  - Each permutation takes O(n) to build/store")
    println()
    println("Space Complexity: O(n)")
    println("  - visited[] + recursion depth + path list")
}