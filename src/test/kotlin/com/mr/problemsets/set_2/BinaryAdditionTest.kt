package com.mr.problemsets.set_2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BinaryAdditionTest {

    @Test
    fun `example 1 - 11 + 1 = 100`() {
        assertEquals("100", addBinary("11", "1"))
    }

    @Test
    fun `example 2 - 1010 + 1011 = 10101`() {
        assertEquals("10101", addBinary("1010", "1011"))
    }

    @Test
    fun `both zeros`() {
        assertEquals("0", addBinary("0", "0"))
    }

    @Test
    fun `one zero operand`() {
        assertEquals("101", addBinary("101", "0"))
        assertEquals("101", addBinary("0", "101"))
    }

    @Test
    fun `same length no carry`() {
        assertEquals("10", addBinary("01", "01"))
    }

    @Test
    fun `all ones - cascade carry`() {
        // 111 (7) + 1 (1) = 1000 (8)
        assertEquals("1000", addBinary("111", "1"))
    }

    @Test
    fun `different lengths`() {
        // 1 (1) + 111 (7) = 1000 (8)
        assertEquals("1000", addBinary("1", "111"))
    }

    @Test
    fun `large equal numbers`() {
        // 1111 (15) + 1111 (15) = 11110 (30)
        assertEquals("11110", addBinary("1111", "1111"))
    }

    @Test
    fun `single bits`() {
        assertEquals("0", addBinary("0", "0"))
        assertEquals("1", addBinary("0", "1"))
        assertEquals("1", addBinary("1", "0"))
        assertEquals("10", addBinary("1", "1"))
    }

    @Test
    fun `longer strings`() {
        // 10101010 (170) + 1010101 (85) = 11111111 (255)
        assertEquals("11111111", addBinary("10101010", "1010101"))
    }
}