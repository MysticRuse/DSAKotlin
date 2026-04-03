package com.mr.patterns.stacks.medium

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class DailyTemperatureTest {

    private val solver = DailyTemperature()

    @Test
    fun testLeetcodeExample() {
        val input = intArrayOf(73, 74, 75, 71, 69, 72, 76, 73)
        val expected = intArrayOf(1, 1, 4, 2, 1, 1, 0, 0)

        assertArrayEquals(expected, solver.dailyTemperatures(input))
    }

    @Test
    fun testStrictlyIncreasing() {
        val input = intArrayOf(30, 40, 50, 60)
        val expected = intArrayOf(1, 1, 1, 0)

        assertArrayEquals(expected, solver.dailyTemperatures(input))
    }

    @Test
    fun testStrictlyDecreasing() {
        val input = intArrayOf(60, 50, 40, 30)
        val expected = intArrayOf(0, 0, 0, 0)

        assertArrayEquals(expected, solver.dailyTemperatures(input))
    }

    @Test
    fun testSingleElement() {
        val input = intArrayOf(70)
        val expected = intArrayOf(0)

        assertArrayEquals(expected, solver.dailyTemperatures(input))
    }

    @Test
    fun testAllSameTemperatures() {
        val input = intArrayOf(70, 70, 70)
        val expected = intArrayOf(0, 0, 0)

        assertArrayEquals(expected, solver.dailyTemperatures(input))
    }

    @Test
    fun testAlternatingPattern() {
        val input = intArrayOf(70, 71, 70, 71)
        val expected = intArrayOf(1, 0, 1, 0)

        assertArrayEquals(expected, solver.dailyTemperatures(input))
    }

    @Test
    fun testBigSpikeAtEnd() {
        val input = intArrayOf(90, 80, 70, 60, 50, 100)
        val expected = intArrayOf(5, 4, 3, 2, 1, 0)

        assertArrayEquals(expected, solver.dailyTemperatures(input))
    }

    @Test
    fun testMixedUpDownPattern() {
        val input = intArrayOf(50, 60, 55, 65, 60, 70)
        val expected = intArrayOf(1, 2, 1, 2, 1, 0)

        assertArrayEquals(expected, solver.dailyTemperatures(input))
    }

    @Test
    fun testEmptyArray() {
        val input = intArrayOf()
        val expected = intArrayOf()

        assertArrayEquals(expected, solver.dailyTemperatures(input))
    }
}