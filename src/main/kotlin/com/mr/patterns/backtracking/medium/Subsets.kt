package com.mr.patterns.backtracking.medium

class Subsets {

    fun subsets(nums: IntArray): List<List<Int>> {

        val result = mutableListOf<List<Int>>()
        val n = nums.size
        val path = mutableListOf<Int>()

        fun backtrack(start: Int) {
            result.add(path.toList())

            for (i in start until n) {
                path.add(nums[i])
                backtrack(i+1)
                path.removeLast()
            }
        }

        backtrack(0)
        return result
    }
}

fun main() {

    fun normalize(output: List<List<Int>>): Set<List<Int>> {
        // Convert each subset to List<Int> and store in a set (order doesn't matter)
        return output.map { it.toList() }.toSet()
    }

    fun runTest(testName: String, nums: IntArray, expected: Set<List<Int>>) {
        val solver = Subsets()
        val actual = solver.subsets(nums)

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
        val solver = Subsets()
        val actual = solver.subsets(nums)

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

    // Test 1: Standard example
    runTest(
        testName = "Subsets of [1,2,3]",
        nums = intArrayOf(1, 2, 3),
        expected = setOf(
            emptyList(),
            listOf(1),
            listOf(2),
            listOf(3),
            listOf(1, 2),
            listOf(1, 3),
            listOf(2, 3),
            listOf(1, 2, 3)
        )
    )

    // Test 2: Two elements
    runTest(
        testName = "Subsets of [0,1]",
        nums = intArrayOf(0, 1),
        expected = setOf(
            emptyList(),
            listOf(0),
            listOf(1),
            listOf(0, 1)
        )
    )

    // Test 3: Single element
    runTest(
        testName = "Subsets of [5]",
        nums = intArrayOf(5),
        expected = setOf(
            emptyList(),
            listOf(5)
        )
    )

    // Test 4: Empty array
    runTest(
        testName = "Subsets of []",
        nums = intArrayOf(),
        expected = setOf(
            emptyList()
        )
    )

    // Test 5: Count test (size should be 2^n)
    val nums4 = intArrayOf(1, 2, 3, 4)
    runCountTest(
        testName = "Subsets count of [1,2,3,4]",
        nums = nums4,
        expectedCount = 1 shl nums4.size // 2^n
    )

    // Test 6: Negative numbers
    runTest(
        testName = "Subsets of [-1,2]",
        nums = intArrayOf(-1, 2),
        expected = setOf(
            emptyList(),
            listOf(-1),
            listOf(2),
            listOf(-1, 2)
        )
    )

    println("\n====================================")
    println("SUMMARY")
    println("====================================")
    println("Approach: Backtracking with start index")
    println("At each step, we either include nums[i] or skip it by moving forward.")
    println("Every intermediate path is a valid subset.")
    println()
    println("Time Complexity: O(n * 2^n)")
    println("  - There are 2^n subsets")
    println("  - Copying each subset takes O(n) in worst case")
    println()
    println("Space Complexity: O(n)")
    println("  - recursion depth + path storage")
}