package com.mr.problemsets.set_2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PrimeNumbersLessThanNTest {

    @Test
    fun `primes less than 10`() {
        // Primes: 2, 3, 5, 7
        assertEquals(4, countPrimesLessThanN(10))
    }

    @Test
    fun `primes less than 0`() {
        assertEquals(0, countPrimesLessThanN(0))
    }

    @Test
    fun `primes less than 1`() {
        assertEquals(0, countPrimesLessThanN(1))
    }

    @Test
    fun `primes less than 2`() {
        assertEquals(0, countPrimesLessThanN(2))
    }

    @Test
    fun `primes less than 3`() {
        // Only 2 is prime and less than 3
        assertEquals(1, countPrimesLessThanN(3))
    }

    @Test
    fun `primes less than 20`() {
        // Primes: 2, 3, 5, 7, 11, 13, 17, 19
        assertEquals(8, countPrimesLessThanN(20))
    }

    @Test
    fun `primes less than 100`() {
        // There are 25 primes less than 100
        assertEquals(25, countPrimesLessThanN(100))
    }

    @Test
    fun `primes less than 1000`() {
        // There are 168 primes less than 1000
        assertEquals(168, countPrimesLessThanN(1000))
    }

    @Test
    fun `edge case - prime number itself`() {
        // Primes less than 7: 2, 3, 5
        assertEquals(3, countPrimesLessThanN(7))
    }

    @Test
    fun `large input - primes less than 1 million`() {
        // There are 78498 primes less than 1,000,000
        assertEquals(78498, countPrimesLessThanN(1_000_000))
    }
}