package com.mr.patterns.twopointers.easy

import com.mr.patterns.ListNode

/**
 * 206. Reverse Linked List
 * Easy
 * Topics: Linked List, 2 Pointers
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * Example 2:
 * Input: head = [1,2]
 * Output: [2,1]
 * Example 3:
 * Input: head = []
 * Output: []
 * Constraints:
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 *
 *
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
class ReverseLinkedList {

    fun reverseList(head: ListNode?): ListNode? {
        return reverseList_Iterative(head)
        //return reverseList_recursive(head)
    }

    /**
     * Assume that the rest of the list had already been reversed, now how do we reverse the front part?
     * Let's assume the list is: n1 → … → nk-1 → nk → nk+1 → … → nm → Ø
     * Assume from node nk+1 to nm had been reversed and we are at node nk.
     * n1 → … → nk-1 → nk → nk+1 ← … ← nm
     * We want nk+1’s next node to point to nk.
     * So, nk.next.next = nk;
     * Be very careful that n1's next must point to Ø. If forget about this, the linked list will have a cycle in it.
     * This bug could be caught if code tested with a linked list of size 2.
     *
     * Complexity Analysis
     * Time complexity : O(n).
     * Assume that n is the list's length, the time complexity is O(n).
     * Space complexity : O(n).
     * The extra space comes from implicit stack space due to recursion. The recursion could go up to n levels deep.
     */
    private fun reverseList_recursive(head: ListNode?): ListNode? {
        if (head == null || head.next == null) return head
        val newHead = reverseList_recursive(head.next)
        head.next!!.next = head
        head.next = null
        return newHead
    }

    /**
     * Complexity analysis
     * Time complexity : O(n).
     * Assume that n is the list's length, the time complexity is O(n).
     * Space complexity : O(1).
     */
    private fun reverseList_Iterative(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var curr: ListNode? = head

        while (curr != null) {
            val next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        }

        return prev
    }
}

// Utility: build linked list from IntArray
fun buildList(arr: IntArray): ListNode? {
    if (arr.isEmpty()) return null
    val dummy = ListNode(0)
    var tail = dummy
    for (x in arr) {
        tail.next = ListNode(x)
        tail = tail.next!!
    }
    return dummy.next
}

// Utility: convert linked list to Kotlin List<Int>
fun toList(head: ListNode?): List<Int> {
    val result = mutableListOf<Int>()
    var curr = head
    while (curr != null) {
        result.add(curr.value)
        curr = curr.next
    }
    return result
}

fun main() {
    val solver = ReverseLinkedList()

    data class TestCase(
        val input: IntArray,
        val expected: List<Int>,
        val description: String
    )

    val tests = listOf(
        TestCase(
            input = intArrayOf(1, 2, 3, 4, 5),
            expected = listOf(5, 4, 3, 2, 1),
            description = "Normal case: reverse a 5-node list"
        ),
        TestCase(
            input = intArrayOf(1, 2),
            expected = listOf(2, 1),
            description = "Two node list"
        ),
        TestCase(
            input = intArrayOf(1),
            expected = listOf(1),
            description = "Single node list"
        ),
        TestCase(
            input = intArrayOf(),
            expected = emptyList(),
            description = "Empty list"
        ),
        TestCase(
            input = intArrayOf(-1, -2, -3),
            expected = listOf(-3, -2, -1),
            description = "List with negative numbers"
        )
    )

    var passed = 0

    for ((idx, test) in tests.withIndex()) {
        val head = buildList(test.input)
        val reversedHead = solver.reverseList(head)
        val actual = toList(reversedHead)

        if (actual == test.expected) {
            println("✅ PASS #${idx + 1}: ${test.description}")
            passed++
        } else {
            println("❌ FAIL #${idx + 1}: ${test.description}")
            println("   Input    : ${test.input.toList()}")
            println("   Expected : ${test.expected}")
            println("   Got      : $actual")
        }
    }

    println("\n==============================")
    println("Reverse Linked List Test Summary")
    println("==============================")
    println("Total Tests : ${tests.size}")
    println("Passed      : $passed")
    println("Failed      : ${tests.size - passed}")

    println("\nTest Coverage Summary:")
    println("1) Standard multi-node list reversal")
    println("2) Two-node list reversal")
    println("3) Single node list (no change)")
    println("4) Empty list")
    println("5) Negative numbers handling")
}