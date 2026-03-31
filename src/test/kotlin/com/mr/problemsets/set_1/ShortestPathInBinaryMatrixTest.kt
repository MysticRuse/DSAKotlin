package com.mr.problemsets.set_1

import kotlin.test.Test
import kotlin.test.assertEquals

class ShortestPathInBinaryMatrixTest {

    @Test
    fun `example 1 - simple path exists`() {
        val grid = listOf(
            listOf(0, 0, 0),
            listOf(1, 1, 0),
            listOf(1, 1, 0)
        )
        assertEquals(4, shortestPathBinaryMatrix(grid))
    }

    @Test
    fun `example 2 - no path exists`() {
        val grid = listOf(
            listOf(0, 1, 0),
            listOf(0, 1, 0),
            listOf(0, 1, 0)
        )
        assertEquals(-1, shortestPathBinaryMatrix(grid))
    }

    @Test
    fun `diagonal path - 2x2 grid`() {
        val grid = listOf(
            listOf(0, 0),
            listOf(0, 0)
        )
        assertEquals(2, shortestPathBinaryMatrix(grid))
    }

    @Test
    fun `single cell - open`() {
        val grid = listOf(listOf(0))
        assertEquals(1, shortestPathBinaryMatrix(grid))
    }

    @Test
    fun `single cell - blocked`() {
        val grid = listOf(listOf(1))
        assertEquals(-1, shortestPathBinaryMatrix(grid))
    }

    @Test
    fun `start blocked`() {
        val grid = listOf(
            listOf(1, 0, 0),
            listOf(0, 0, 0),
            listOf(0, 0, 0)
        )
        assertEquals(-1, shortestPathBinaryMatrix(grid))
    }

    @Test
    fun `end blocked`() {
        val grid = listOf(
            listOf(0, 0, 0),
            listOf(0, 0, 0),
            listOf(0, 0, 1)
        )
        assertEquals(-1, shortestPathBinaryMatrix(grid))
    }

    @Test
    fun `all open - diagonal is shortest`() {
        val grid = listOf(
            listOf(0, 0, 0),
            listOf(0, 0, 0),
            listOf(0, 0, 0)
        )
        assertEquals(3, shortestPathBinaryMatrix(grid))
    }

    @Test
    fun `must go around obstacle`() {
        val grid = listOf(
            listOf(0, 0, 0),
            listOf(0, 1, 0),
            listOf(0, 0, 0)
        )
        assertEquals(4, shortestPathBinaryMatrix(grid))
    }

    @Test
    fun `zigzag path required`() {
        val grid = listOf(
            listOf(0, 1, 0, 0),
            listOf(0, 1, 0, 1),
            listOf(0, 0, 0, 1),
            listOf(1, 1, 0, 0)
        )
        assertEquals(5, shortestPathBinaryMatrix(grid))
    }

    @Test
    fun `large open grid - diagonal`() {
        val n = 5
        val grid = List(n) { List(n) { 0 } }
        assertEquals(5, shortestPathBinaryMatrix(grid))
    }

    @Test
    fun `only diagonal path available`() {
        val grid = listOf(
            listOf(0, 1, 1),
            listOf(1, 0, 1),
            listOf(1, 1, 0)
        )
        assertEquals(3, shortestPathBinaryMatrix(grid))
    }

    @Test
    fun `multiple paths - BFS finds shortest`() {
        val grid = listOf(
            listOf(0, 0, 0, 0),
            listOf(1, 1, 1, 0),
            listOf(0, 0, 0, 0),
            listOf(0, 1, 1, 0)
        )
        assertEquals(6, shortestPathBinaryMatrix(grid))
    }
}