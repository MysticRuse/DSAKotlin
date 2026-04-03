package com.mr.patterns.stacks.medium

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MinStackTest {

    @Test
    fun testLeetcodeExample() {
        val ms = MinStack()
        ms.push(-2)
        ms.push(0)
        ms.push(-3)

        assertEquals(-3, ms.getMin())

        ms.pop()
        assertEquals(0, ms.top())
        assertEquals(-2, ms.getMin())
    }

    @Test
    fun testIncreasingPush() {
        val ms = MinStack()
        ms.push(1)
        ms.push(2)
        ms.push(3)

        assertEquals(3, ms.top())
        assertEquals(1, ms.getMin())
    }

    @Test
    fun testDecreasingPush() {
        val ms = MinStack()
        ms.push(5)
        ms.push(4)
        ms.push(3)

        assertEquals(3, ms.top())
        assertEquals(3, ms.getMin())
    }

    @Test
    fun testDuplicateMinimum() {
        val ms = MinStack()
        ms.push(2)
        ms.push(0)
        ms.push(0)
        ms.push(3)

        assertEquals(0, ms.getMin())

        ms.pop() // remove 3
        assertEquals(0, ms.getMin())

        ms.pop() // remove 0
        assertEquals(0, ms.getMin())

        ms.pop() // remove 0
        assertEquals(2, ms.getMin())
    }

    @Test
    fun testPopUntilEmptyThenExceptions() {
        val ms = MinStack()
        ms.push(10)
        ms.pop()

        assertThrows(NoSuchElementException::class.java) { ms.top() }
        assertThrows(NoSuchElementException::class.java) { ms.getMin() }
        assertThrows(NoSuchElementException::class.java) { ms.pop() }
    }

    @Test
    fun testSingleElement() {
        val ms = MinStack()
        ms.push(7)

        assertEquals(7, ms.top())
        assertEquals(7, ms.getMin())

        ms.pop()
        assertThrows(NoSuchElementException::class.java) { ms.getMin() }
    }
}