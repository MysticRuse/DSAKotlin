package com.mr.patterns.graphs.medium

/**
 * 200. Number of Islands
 * Medium
 * Topics: Array, Matrix, DFS, BFS, Union-Find
 * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume water all surrounds all four edges of the grid.
 * Example 1:
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 */
class NumberOfIslands {
    var ROWS: Int = 0
    var COLS: Int = 0

    val DIRECTIONS = arrayOf(
        intArrayOf(0,1),    // right
        intArrayOf(0,-1),   // left
        intArrayOf(1,0),    // down
        intArrayOf(-1,0))   // up

    fun numIslands(grid: Array<CharArray>) : Int {
        if (grid.isEmpty()) return 0

        var numIslands = 0
        ROWS = grid.size
        COLS = grid[0].size

        for (r in 0 until ROWS) {
            for (c in 0 until COLS) {
                if (grid[r][c] == '1') {
                    numIslands++
                    dfsHelper(grid, r, c)
                }
            }
        }

        return numIslands
    }

    private fun dfsHelper(grid: Array<CharArray>, r: Int, c: Int) {
        // Check for validity - within the grid range and not visited
        if ((r < 0) || (r >= ROWS) || (c < 0) || (c >= COLS) || (grid[r][c] == '0')) return

        // Mark as visited
        grid[r][c] = '0'

        // Explore neighbors
        for (dir in DIRECTIONS) {
            dfsHelper(grid, r + dir[0], c + dir[1])
        }
    }
}

// ---------------- MAIN FUNCTION WITH TESTS + SUMMARY ----------------

fun main() {

    var totalTests = 0
    var passedTests = 0
    var failedTests = 0

    fun runTest(testName: String, grid: Array<CharArray>, expected: Int) {
        totalTests++

        val solver = NumberOfIslands()

        // Deep copy because algorithm modifies grid in-place
        val gridCopy = Array(grid.size) { r -> grid[r].clone() }

        val result = solver.numIslands(gridCopy)

        if (result == expected) {
            passedTests++
            println("✅ PASS: $testName | Expected=$expected, Got=$result")
        } else {
            failedTests++
            println("❌ FAIL: $testName | Expected=$expected, Got=$result")
        }
    }

    // 1) Empty grid
    runTest(
        testName = "Empty Grid",
        grid = emptyArray(),
        expected = 0
    )

    // 2) Single cell water
    runTest(
        testName = "Single Cell Water",
        grid = arrayOf(charArrayOf('0')),
        expected = 0
    )

    // 3) Single cell land
    runTest(
        testName = "Single Cell Land",
        grid = arrayOf(charArrayOf('1')),
        expected = 1
    )

    // 4) One big island
    runTest(
        testName = "One Big Island (All Land)",
        grid = arrayOf(
            charArrayOf('1', '1', '1'),
            charArrayOf('1', '1', '1'),
            charArrayOf('1', '1', '1')
        ),
        expected = 1
    )

    // 5) Multiple islands (classic LeetCode example)
    runTest(
        testName = "Multiple Islands (Classic Example)",
        grid = arrayOf(
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '1', '0', '0'),
            charArrayOf('0', '0', '0', '1', '1')
        ),
        expected = 3
    )

    // 6) Islands separated diagonally (diagonal doesn't connect)
    runTest(
        testName = "Diagonal Lands Not Connected",
        grid = arrayOf(
            charArrayOf('1', '0', '0'),
            charArrayOf('0', '1', '0'),
            charArrayOf('0', '0', '1')
        ),
        expected = 3
    )

    // 7) One row grid
    runTest(
        testName = "One Row Grid",
        grid = arrayOf(
            charArrayOf('1', '0', '1', '1', '0', '1')
        ),
        expected = 3
    )

    // 8) One column grid
    runTest(
        testName = "One Column Grid",
        grid = arrayOf(
            charArrayOf('1'),
            charArrayOf('0'),
            charArrayOf('1'),
            charArrayOf('1'),
            charArrayOf('0'),
            charArrayOf('1')
        ),
        expected = 3
    )

    // 9) All water
    runTest(
        testName = "All Water",
        grid = arrayOf(
            charArrayOf('0', '0', '0'),
            charArrayOf('0', '0', '0'),
            charArrayOf('0', '0', '0')
        ),
        expected = 0
    )

    // 10) Checkerboard pattern (each land isolated)
    runTest(
        testName = "Checkerboard Pattern",
        grid = arrayOf(
            charArrayOf('1', '0', '1'),
            charArrayOf('0', '1', '0'),
            charArrayOf('1', '0', '1')
        ),
        expected = 5
    )

    // 11) Complex shape with one island
    runTest(
        testName = "Complex Shape Single Island",
        grid = arrayOf(
            charArrayOf('1', '1', '0', '0'),
            charArrayOf('0', '1', '1', '0'),
            charArrayOf('0', '0', '1', '0'),
            charArrayOf('0', '0', '1', '1')
        ),
        expected = 1
    )

    // ---------------- SUMMARY ----------------
    println("\n==================== TEST SUMMARY ====================")
    println("Total Tests  : $totalTests")
    println("Passed Tests : $passedTests")
    println("Failed Tests : $failedTests")
    println("======================================================")
}