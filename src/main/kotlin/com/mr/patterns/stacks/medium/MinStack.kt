package com.mr.patterns.stacks.medium

/**
 * 155. Min Stack
 *
 * Medium
 * Topics: Design, Stack
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 * Example 1:
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * Output
 * [null,null,null,null,-3,null,0,-2]
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 *
 * Constraints:
 * -231 <= val <= 231 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 104 calls will be made to push, pop, top, and getMin.
 */
class MinStack() {

    val stack = ArrayDeque<Pair<Int, Int>> ()

    fun push(value: Int) {
        val currentMin = if (stack.isEmpty()) value else minOf(getMin(), value)
        stack.addLast(value to currentMin)
    }

    fun pop() {
        stack.removeLast()
    }

    fun top(): Int {
        return stack.lastOrNull()?.first ?: throw NoSuchElementException("Stack is empty")
    }

    fun getMin(): Int {
        return stack.lastOrNull()?.second ?: throw NoSuchElementException("Stack is empty")
    }
}

fun main() {
    var passed = 0
    var failed = 0

    fun assertEquals(testName: String, expected: Any, actual: Any) {
        if (expected == actual) {
            println("$testName -> PASS")
            passed++
        } else {
            println("$testName -> FAIL | Expected=$expected, Got=$actual")
            failed++
        }
    }

    fun assertThrows(testName: String, block: () -> Unit) {
        try {
            block()
            println("$testName -> FAIL | Expected exception but none thrown")
            failed++
        } catch (e: Exception) {
            println("$testName -> PASS | Threw ${e::class.simpleName}")
            passed++
        }
    }

    // ---------------- TESTS ----------------

    // Test 1: LeetCode Example
    run {
        val ms = MinStack()
        ms.push(-2)
        ms.push(0)
        ms.push(-3)
        assertEquals("Test1-getMin", -3, ms.getMin())

        ms.pop()
        assertEquals("Test1-top", 0, ms.top())
        assertEquals("Test1-getMin-after-pop", -2, ms.getMin())
    }

    // Test 2: Increasing push
    run {
        val ms = MinStack()
        ms.push(1)
        ms.push(2)
        ms.push(3)
        assertEquals("Test2-getMin", 1, ms.getMin())
        assertEquals("Test2-top", 3, ms.top())
    }

    // Test 3: Decreasing push
    run {
        val ms = MinStack()
        ms.push(5)
        ms.push(4)
        ms.push(3)
        assertEquals("Test3-getMin", 3, ms.getMin())
        assertEquals("Test3-top", 3, ms.top())
    }

    // Test 4: Duplicate minimum values
    run {
        val ms = MinStack()
        ms.push(2)
        ms.push(0)
        ms.push(0)
        ms.push(3)

        assertEquals("Test4-getMin-initial", 0, ms.getMin())

        ms.pop() // pop 3
        assertEquals("Test4-getMin-after-pop3", 0, ms.getMin())

        ms.pop() // pop 0
        assertEquals("Test4-getMin-after-pop0", 0, ms.getMin())

        ms.pop() // pop 0
        assertEquals("Test4-getMin-after-pop0-again", 2, ms.getMin())
    }

    // Test 5: pop until empty and check exceptions
    run {
        val ms = MinStack()
        ms.push(10)
        ms.pop()

        assertThrows("Test5-top-empty") { ms.top() }
        assertThrows("Test5-getMin-empty") { ms.getMin() }
        assertThrows("Test5-pop-empty") { ms.pop() }
    }

    // ---------------- SUMMARY ----------------
    println("\n========== TEST SUMMARY ==========")
    println("Total Tests : ${passed + failed}")
    println("Passed      : $passed")
    println("Failed      : $failed")
    println("==================================")
}