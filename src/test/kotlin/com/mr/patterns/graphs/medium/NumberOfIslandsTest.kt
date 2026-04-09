package com.mr.patterns.graphs.medium

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NumberOfIslandsTest {

    private fun deepCopyGrid(grid: Array<CharArray>): Array<CharArray> {
        return Array(grid.size) { r -> grid[r].clone() }
    }

    @Test
    fun testEmptyGrid() {
        val solver = NumberOfIslands()
        val grid = emptyArray<CharArray>()
        assertEquals(0, solver.numIslands(grid))
    }

    @Test
    fun testSingleCellWater() {
        val solver = NumberOfIslands()
        val grid = arrayOf(charArrayOf('0'))
        assertEquals(0, solver.numIslands(deepCopyGrid(grid)))
    }

    @Test
    fun testSingleCellLand() {
        val solver = NumberOfIslands()
        val grid = arrayOf(charArrayOf('1'))
        assertEquals(1, solver.numIslands(deepCopyGrid(grid)))
    }

    @Test
    fun testAllLandOneIsland() {
        val solver = NumberOfIslands()
        val grid = arrayOf(
            charArrayOf('1', '1', '1'),
            charArrayOf('1', '1', '1'),
            charArrayOf('1', '1', '1')
        )
        assertEquals(1, solver.numIslands(deepCopyGrid(grid)))
    }

    @Test
    fun testClassicMultipleIslands() {
        val solver = NumberOfIslands()
        val grid = arrayOf(
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '1', '0', '0'),
            charArrayOf('0', '0', '0', '1', '1')
        )
        assertEquals(3, solver.numIslands(deepCopyGrid(grid)))
    }

    @Test
    fun testDiagonalNotConnected() {
        val solver = NumberOfIslands()
        val grid = arrayOf(
            charArrayOf('1', '0', '0'),
            charArrayOf('0', '1', '0'),
            charArrayOf('0', '0', '1')
        )
        assertEquals(3, solver.numIslands(deepCopyGrid(grid)))
    }

    @Test
    fun testOneRowGrid() {
        val solver = NumberOfIslands()
        val grid = arrayOf(
            charArrayOf('1', '0', '1', '1', '0', '1')
        )
        assertEquals(3, solver.numIslands(deepCopyGrid(grid)))
    }

    @Test
    fun testOneColumnGrid() {
        val solver = NumberOfIslands()
        val grid = arrayOf(
            charArrayOf('1'),
            charArrayOf('0'),
            charArrayOf('1'),
            charArrayOf('1'),
            charArrayOf('0'),
            charArrayOf('1')
        )
        assertEquals(3, solver.numIslands(deepCopyGrid(grid)))
    }

    @Test
    fun testAllWater() {
        val solver = NumberOfIslands()
        val grid = arrayOf(
            charArrayOf('0', '0', '0'),
            charArrayOf('0', '0', '0'),
            charArrayOf('0', '0', '0')
        )
        assertEquals(0, solver.numIslands(deepCopyGrid(grid)))
    }

    @Test
    fun testCheckerboardPattern() {
        val solver = NumberOfIslands()
        val grid = arrayOf(
            charArrayOf('1', '0', '1'),
            charArrayOf('0', '1', '0'),
            charArrayOf('1', '0', '1')
        )
        assertEquals(5, solver.numIslands(deepCopyGrid(grid)))
    }

    @Test
    fun testComplexShapeSingleIsland() {
        val solver = NumberOfIslands()
        val grid = arrayOf(
            charArrayOf('1', '1', '0', '0'),
            charArrayOf('0', '1', '1', '0'),
            charArrayOf('0', '0', '1', '0'),
            charArrayOf('0', '0', '1', '1')
        )
        assertEquals(1, solver.numIslands(deepCopyGrid(grid)))
    }
}