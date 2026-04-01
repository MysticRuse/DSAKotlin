package com.mr.problemsets.set_2

/**
 * Prime numbers less than N
 * You are given a non-negative integer N. Your task is to write a program that can print the number of prime numbers less than N.
 * Input
 * The input contains an integer N, representing the non-negative integer.
 * Output
 * Print the number of prime numbers less than N.
 *
 * Constraints
 * 0 <= N <= 5 * 106
 *
 * Example #1 Input 10
 * Output 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 *
 * Example #2 Input 0
 * Output 0
 * Explanation: There are no prime numbers less than 0.
 *
 */

fun countPrimesLessThanN(n: Int): Int {

    //return isPrimeBasic(n)

    // Check the constraints - it has a large number 5^106 - so require optimization.
    return isPrimeOptimizedEratosthenesSieve(n)
}

fun isPrimeOptimizedEratosthenesSieve(n: Int): Int {
    if (n < 2) return 0


    // Create array: isPrimeSieve[i] = true means i might be prime

    val isPrimeSieve = BooleanArray(n) { true }
    isPrimeSieve[0] = false
    isPrimeSieve[1] = false

    var i = 2
    while (i * i < n) {
        if (isPrimeSieve[i]) {
            // Mark all multiples of i as not prime
            // Start from i*i (smaller multiples already crossed out)
            var j = i * i
            while (j < n) {
                isPrimeSieve[j] = false
                j += i
            }
        }
        i++
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


