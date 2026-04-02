package com.mr.problemsets.set_2

import kotlin.math.sqrt

/**
 * 204. Count Primes
 * Medium
 * Given an integer n, return the number of prime numbers that are strictly less than n.
 *
 * Example 1:
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 *
 * Example 2:
 * Input: n = 0
 * Output: 0
 *
 * Example 3:
 * Input: n = 1
 * Output: 0
 *
 * Constraints:
 *
 * 0 <= n <= 5 * 106
 */

fun countPrimesLessThanN(n: Int): Int {

    //return isPrimeBasic(n)

    // Check the constraints - it has a large number 5^106 - so require optimization.
    return isPrimeOptimizedEratosthenesSieve(n)
}

fun isPrimeOptimizedEratosthenesSieve(n: Int): Int {
    if (n <= 2) return 0

    // Create array: isPrimeSieve[i] = true means i might be prime
    val isPrimeSieve = BooleanArray(n) { true }
    isPrimeSieve[0] = false
    isPrimeSieve[1] = false

    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (isPrimeSieve[i]) {
            // Mark all multiples of i as not prime
            // Start from i*i (smaller multiples already crossed out)
            for (j in i*i until n step i) {
                isPrimeSieve[j] = false
            }
        }
    }

    return isPrimeSieve.count { it }
}

fun isPrimeBasic(n: Int): Int {
    var count = 0

    for (i in 2..<n) {
        //if (i % 2 == 0) continue
        if (isPrime(i)) {
            count++
        }
    }

    return count
}

private fun isPrime(n: Int): Boolean {
    for (i in 2..n/2) {
        if ( n % i == 0) return false
    }
    return true
}

fun main() {
    //val n = readln().trim().toInt()
    println("Primes less than 10: ${countPrimesLessThanN(10)}")
    println("Primes less than 0: ${countPrimesLessThanN(0)}")
    println("Primes less than 2000: ${countPrimesLessThanN(2000)}")
    println("Primes less than 200000: ${countPrimesLessThanN(200000)}")

}


