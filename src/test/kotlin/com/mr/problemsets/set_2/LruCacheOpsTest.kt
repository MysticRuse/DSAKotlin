package com.mr.problemsets.set_2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LRUCacheTest {

    // ===== Problem Examples =====

    @Test
    fun `example 1 - capacity 2 with eviction`() {
        val cache = LRUCache(2)

        assertEquals(-1, cache.get(2))      // Cache empty
        cache.put(1, 100)                    // [(1,100)]
        cache.put(2, 125)                    // [(1,100), (2,125)]
        cache.put(3, 150)                    // Evict key 1 → [(2,125), (3,150)]
        assertEquals(-1, cache.get(1))      // Key 1 was evicted
        assertEquals(150, cache.get(3))     // Key 3 exists
    }

    @Test
    fun `example 2 - capacity 3 with update`() {
        val cache = LRUCache(3)

        cache.put(11, 25)                    // [(11,25)]
        cache.put(22, 50)                    // [(11,25), (22,50)]
        cache.put(11, 75)                    // Update key 11 → [(22,50), (11,75)]
        assertEquals(75, cache.get(11))     // Key 11 updated value
        assertEquals(50, cache.get(22))     // Key 22 exists
    }

    // ===== Basic Operations =====

    @Test
    fun `get from empty cache returns -1`() {
        val cache = LRUCache(2)
        assertEquals(-1, cache.get(1))
        assertEquals(-1, cache.get(100))
    }

    @Test
    fun `put and get single item`() {
        val cache = LRUCache(1)
        cache.put(1, 10)
        assertEquals(10, cache.get(1))
    }

    @Test
    fun `put updates existing key`() {
        val cache = LRUCache(2)
        cache.put(1, 10)
        cache.put(1, 20)
        assertEquals(20, cache.get(1))
    }

    @Test
    fun `get non-existent key returns -1`() {
        val cache = LRUCache(2)
        cache.put(1, 10)
        assertEquals(-1, cache.get(2))
    }

    // ===== Eviction Tests =====

    @Test
    fun `evicts least recently used on capacity overflow`() {
        val cache = LRUCache(2)
        cache.put(1, 10)
        cache.put(2, 20)
        cache.put(3, 30)  // Evicts key 1

        assertEquals(-1, cache.get(1))
        assertEquals(20, cache.get(2))
        assertEquals(30, cache.get(3))
    }

    @Test
    fun `get operation updates recency`() {
        val cache = LRUCache(2)
        cache.put(1, 10)
        cache.put(2, 20)
        cache.get(1)      // Key 1 is now most recent
        cache.put(3, 30)  // Evicts key 2 (not 1!)

        assertEquals(10, cache.get(1))
        assertEquals(-1, cache.get(2))
        assertEquals(30, cache.get(3))
    }

    @Test
    fun `put operation on existing key updates recency`() {
        val cache = LRUCache(2)
        cache.put(1, 10)
        cache.put(2, 20)
        cache.put(1, 15)  // Update key 1, now most recent
        cache.put(3, 30)  // Evicts key 2

        assertEquals(15, cache.get(1))
        assertEquals(-1, cache.get(2))
        assertEquals(30, cache.get(3))
    }

    @Test
    fun `capacity 1 - constant eviction`() {
        val cache = LRUCache(1)
        cache.put(1, 10)
        cache.put(2, 20)  // Evicts 1

        assertEquals(-1, cache.get(1))
        assertEquals(20, cache.get(2))

        cache.put(3, 30)  // Evicts 2
        assertEquals(-1, cache.get(2))
        assertEquals(30, cache.get(3))
    }

    // ===== Complex Scenarios =====

    @Test
    fun `multiple gets maintain correct order`() {
        val cache = LRUCache(3)
        cache.put(1, 10)
        cache.put(2, 20)
        cache.put(3, 30)

        cache.get(1)      // Order: 2, 3, 1
        cache.get(2)      // Order: 3, 1, 2
        cache.put(4, 40)  // Evicts 3, Order: 1, 2, 4

        assertEquals(-1, cache.get(3))
        assertEquals(10, cache.get(1))
        assertEquals(20, cache.get(2))
        assertEquals(40, cache.get(4))
    }

    @Test
    fun `interleaved put and get operations`() {
        val cache = LRUCache(2)

        cache.put(1, 1)
        cache.put(2, 2)
        assertEquals(1, cache.get(1))
        cache.put(3, 3)   // Evicts 2
        assertEquals(-1, cache.get(2))
        cache.put(4, 4)   // Evicts 1
        assertEquals(-1, cache.get(1))
        assertEquals(3, cache.get(3))
        assertEquals(4, cache.get(4))
    }

    @Test
    fun `update same key multiple times`() {
        val cache = LRUCache(2)
        cache.put(1, 10)
        cache.put(1, 20)
        cache.put(1, 30)
        cache.put(1, 40)

        assertEquals(40, cache.get(1))
        // Capacity should still be correct
        cache.put(2, 200)
        assertEquals(40, cache.get(1))
        assertEquals(200, cache.get(2))
    }

    @Test
    fun `failed get does not affect cache state`() {
        val cache = LRUCache(2)
        cache.put(1, 10)
        cache.put(2, 20)

        cache.get(99)     // Miss, should not affect order
        cache.put(3, 30)  // Should still evict key 1

        assertEquals(-1, cache.get(1))
        assertEquals(20, cache.get(2))
        assertEquals(30, cache.get(3))
    }

    // ===== Edge Cases =====

    @Test
    fun `negative values`() {
        val cache = LRUCache(2)
        cache.put(1, -100)
        cache.put(-2, -200)

        assertEquals(-100, cache.get(1))
        assertEquals(-200, cache.get(-2))
    }

    @Test
    fun `zero as key and value`() {
        val cache = LRUCache(2)
        cache.put(0, 0)
        assertEquals(0, cache.get(0))

        cache.put(0, 100)
        assertEquals(100, cache.get(0))
    }

    @Test
    fun `large capacity no eviction`() {
        val cache = LRUCache(100)
        for (i in 1..50) {
            cache.put(i, i * 10)
        }
        for (i in 1..50) {
            assertEquals(i * 10, cache.get(i))
        }
    }

    @Test
    fun `exact capacity - no eviction until overflow`() {
        val cache = LRUCache(3)
        cache.put(1, 10)
        cache.put(2, 20)
        cache.put(3, 30)

        // All should still exist
        assertEquals(10, cache.get(1))
        assertEquals(20, cache.get(2))
        assertEquals(30, cache.get(3))

        // Now overflow
        cache.put(4, 40)
        assertEquals(-1, cache.get(1))  // 1 was LRU after the gets above
    }

    // ===== LeetCode Style Test =====

    @Test
    fun `leetcode example sequence`() {
        val cache = LRUCache(2)

        cache.put(1, 1)
        cache.put(2, 2)
        assertEquals(1, cache.get(1))     // returns 1
        cache.put(3, 3)                    // evicts key 2
        assertEquals(-1, cache.get(2))    // returns -1 (not found)
        cache.put(4, 4)                    // evicts key 1
        assertEquals(-1, cache.get(1))    // returns -1 (not found)
        assertEquals(3, cache.get(3))     // returns 3
        assertEquals(4, cache.get(4))     // returns 4
    }
}