package com.mr.patterns.twopointers.medium

import com.mr.patterns.ListNode

/**
 * 19. Remove Nth Node From End of List
 * Medium
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * Example 2:
 * Input: head = [1], n = 1
 * Output: []
 *
 * Example 3:
 * Input: head = [1,2], n = 1
 * Output: [1]
 *
 * Constraints:
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */

/**
 * Two Pointer (fast and slow) approach
 * Algo:
 * 1. Create a sentinel node (as have to keep tarck of node previous to the node  to be deleted)
 * 2. Move fastPointer by n nodes
 * 3. Start moving the slowPointer and fastPointer until fastPointer reaches the end of the list
 * 4. Slow pointer will be at the node before the node to be deleted
 *
 * Time complexity: O(n), Space complexity: O(1)
 */
fun removeNthNodeFromEnd(head: ListNode?, n: Int): ListNode? {

    var sentinel = ListNode(0).apply { next = head }
    var fastPointer: ListNode? = head
    var slowPointer: ListNode? = sentinel

    // Move fastPointer by n nodes
    repeat(n) { fastPointer = fastPointer?.next }

    while(fastPointer != null) {
        // start moving the slowPointer and fastPointer
        fastPointer = fastPointer.next
        slowPointer = slowPointer?.next
    }

    // Remove the nth node from end
    val next = slowPointer?.next
    slowPointer?.next = next?.next


    /**
     * Why return sentinel.next?
     * If head is not removed: sentinel.next = original head ✅
     * If head is removed: sentinel.next = new head (second node) ✅
     * If list becomes empty: sentinel.next = null ✅
     */
    return sentinel.next // Beware!! Do not return head from here.

}


//===============================================================================================

fun listToArray(head: ListNode?): List<Int> {
    val result = mutableListOf<Int>()
    var current = head
    while (current != null) {
        result.add(current.value)
        current = current.next
    }
    return result
}

fun arrayToList(arr: List<Int>): ListNode? {
    if (arr.isEmpty()) return null
    val head = ListNode(arr[0])
    var current = head
    for (i in 1 until arr.size) {
        current.next = ListNode(arr[i])
        current = current.next!!
    }
    return head
}

fun main() {
    data class TestCase(val name: String, val input: List<Int>, val n: Int, val expected: List<Int>)

    val testCases = listOf(
        // General cases
        TestCase(
            "Example 1 - Remove 2nd from end",
            listOf(1, 2, 3, 4, 5), 2,
            listOf(1, 2, 3, 5)
        ),
        TestCase(
            "Remove middle node",
            listOf(1, 2, 3, 4, 5), 3,
            listOf(1, 2, 4, 5)
        ),
        TestCase(
            "Remove last node (n=1)",
            listOf(1, 2, 3), 1,
            listOf(1, 2)
        ),

        // Edge cases
        TestCase(
            "Example 2 - Single node, remove it",
            listOf(1), 1,
            emptyList()
        ),
        TestCase(
            "Example 3 - Two nodes, remove last",
            listOf(1, 2), 1,
            listOf(1)
        ),
        TestCase(
            "Two nodes, remove first (head)",
            listOf(1, 2), 2,
            listOf(2)
        ),
        TestCase(
            "Remove head from longer list",
            listOf(1, 2, 3, 4, 5), 5,
            listOf(2, 3, 4, 5)
        ),

        // More cases
        TestCase(
            "Three nodes, remove middle",
            listOf(1, 2, 3), 2,
            listOf(1, 3)
        ),
        TestCase(
            "Five nodes, remove first",
            listOf(10, 20, 30, 40, 50), 5,
            listOf(20, 30, 40, 50)
        )
    )

    println("=== Remove Nth Node From End Test Cases ===\n")

    var passed = 0
    for ((index, test) in testCases.withIndex()) {
        val head = arrayToList(test.input)
        val result = removeNthNodeFromEnd(head, test.n)
        val resultArray = listToArray(result)
        val success = resultArray == test.expected

        val status = if (success) "✅ PASS" else "❌ FAIL"
        println("Test ${index + 1}: ${test.name}")
        println("  Input:    ${test.input}, n=${test.n}")
        println("  Expected: ${test.expected}")
        println("  Actual:   $resultArray")
        println("  Status:   $status\n")

        if (success) passed++
    }

    println("=== Results: $passed/${testCases.size} tests passed ===")
}