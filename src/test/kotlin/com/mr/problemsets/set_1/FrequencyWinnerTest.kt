package com.mr.problemsets.set_1

import kotlin.test.Test
import kotlin.test.assertEquals

class FrequencyWinnerTest {

    @Test
    fun `example case`() {
        assertEquals(2, findFrequencyWinner(listOf(4, 2, 2, 8, 4, 2, 8)))
    }

    @Test
    fun `single element`() {
        assertEquals(5, findFrequencyWinner(listOf(5)))
    }

    @Test
    fun `tie returns smallest`() {
        assertEquals(3, findFrequencyWinner(listOf(5, 3, 5, 3)))
    }

    @Test
    fun `all unique returns smallest`() {
        assertEquals(1, findFrequencyWinner(listOf(7, 2, 9, 1)))
    }

    @Test
    fun `negative numbers`() {
        assertEquals(-1, findFrequencyWinner(listOf(-1, -2, -1, -2, -1)))
    }

    @Test
    fun `negative wins tie over positive`() {
        assertEquals(-5, findFrequencyWinner(listOf(-5, 3, -5, 3)))
    }
}