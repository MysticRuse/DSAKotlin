package com.mr.problemsets.set_1

/**
 * ✅ Problem 5 (Hard) — Shortest Path in Binary Matrix
 * You are given a grid of size n x m containing 0 and 1.
 * 0 = free cell
 * 1 = blocked cell
 * You start at (0,0) and want to reach (n-1, m-1).
 * You can move in 4 directions: up/down/left/right.
 * Return the length of the shortest path (number of cells in path including start and end).
 * If no path exists, print -1.
 * Input
 * First line: n m
 * Next n lines: each contains m integers (0/1)
 * Output
 * Shortest path length or -1
 * Constraints
 * 1 ≤ n, m ≤ 1000
 * Grid size up to 1,000,000 cells
 * Example
 * Input
 * 3 3
 * 0 0 0
 * 1 1 0
 * 0 0 0
 * Output
 * 5
 */

/**
 * Finds the shortest path in a binary matrix from top-left to bottom-right.
 *
 * @param grid n x n matrix where 0 = open, 1 = blocked
 * @return Length of shortest path (cells visited), or -1 if no path exists
 */
fun shortestPathBinaryMatrix(grid: List<List<Int>>): Int {
    val n = grid.size
    val m = grid[0].size
    if (n == 1 && m == 1) {
        return if (grid[0][0] == 0) 1 else -1
    }
    if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1) return -1

    // 8 directions: up, down, left, right, up-left, up-right, down-left, down-right
    val directions = listOf(
        Pair(0, -1), Pair(0, 1), Pair(-1, 0), Pair(1, 0), Pair(-1, -1), Pair(-1, 1), Pair(1, -1), Pair(1, 1)
    )

    val visited = Array(n) { BooleanArray(m)}

    val q = ArrayDeque<Triple<Int, Int, Int>>()
    q.add(Triple(0, 0, 1))
    visited[0][0] = true

    while (q.isNotEmpty()) {
        val (row, col, dist ) = q.removeFirst()

        for ((dr, dc) in directions) {
            val newRow = row + dr
            val newCol = col + dc

            if (newRow !in 0 until n || newCol !in 0 until m) continue
            // Check if blocked or visited
            if (grid[newRow][newCol] == 1 || visited[newRow][newCol]) continue

            // Found destination!
            if (newRow == n - 1 && newCol == m - 1) return dist + 1

            // Add to queue
            q.add(Triple(newRow, newCol, dist + 1))
            visited[newRow][newCol] = true
        }
    }

    return -1
}

fun main() {
    val n = readln().toInt()
    val grid = List(n) {
        readln().split(" ").map { it.toInt() }
    }
    println(shortestPathBinaryMatrix(grid))
}

