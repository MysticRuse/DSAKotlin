package com.mr.patterns.heapspriorityqueues.hard

import com.mr.patterns.ListNode
import java.util.PriorityQueue

/**
 * 23. Merge k Sorted Lists
 * Hard
 * Topics
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 * Example 1:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted linked list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 * Input: lists = []
 * Output: []
 * Example 3:
 * Input: lists = [[]]
 * Output: []
 *
 *
 * Constraints:
 *
 * k == lists.length
 * 0 <= k <= 104
 * 0 <= lists[i].length <= 500
 * -104 <= lists[i][j] <= 104
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length will not exceed 104.
 */
class MergeKSortedLists {

    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        return mergeKLists_Heap(lists)
        //return mergeKLists_divideNConquer(lists)
    }

    // MergeSort : Divide and Conquer
    // ✅ Why Divide and Conquer?
    // Instead of pushing all elements into a heap (as in min-heap approach),
    // we recursively merge pairs of lists.
    // At each level, the number of lists is halved — leading to log k levels.
    // Each list is traversed once per merge → O(N) per level.
    // ⏱ Time Complexity: O(N log k)
    //   -- Where N is the total number of nodes and k is the number of input lists.
    private fun mergeKLists_divideNConquer(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty()) return null

        if (lists.size == 1) return lists[0]
        return mergeHelper(lists, 0, lists.size - 1)
    }

    private fun mergeHelper(lists: Array<ListNode?>, left: Int, right: Int): ListNode? {
        if (left == right) return lists[left]
        val mid = left + (right - left) / 2
        val leftLists = mergeHelper(lists, left, mid)
        val rightLists = mergeHelper(lists, mid + 1, right)
        return mergeTwoLists(leftLists, rightLists)
    }

    private fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        var curr1 = l1
        var curr2 = l2
        val dummy = ListNode(-1)
        var curr = dummy
        while (curr1 != null && curr2 != null) {
            if (curr1.value < curr2.value) {
                curr.next = curr1
                curr1 = curr1.next
            } else {
                curr.next = curr2
                curr2 = curr2.next
            }
            curr = curr.next!!
        }
        // If one of them not yet null, add the remaining nodes.
        curr.next = curr1 ?: curr2

        return dummy.next
    }

    // TC: O(N log k), k = number of lists
    // - The comparison cost will be reduced to O(logk) for every pop and insertion
    //   to priority queue. But finding the node with the smallest value just costs O(1) time.
    // - N nodes in the final linked list
    // SC:
    // - O(n): Creating a new Linked List
    // - O(k): in-place method costs O(1).
    //         - Priority Q implemented as heap costs O(k) space.
    private fun mergeKLists_Heap(lists: Array<ListNode?>): ListNode? {
        val heap = PriorityQueue<ListNode>(compareBy { it.value })

        // Push the head of each list into the heap
        for (head in lists) {
            if (head != null) heap.add(head)
        }

        val dummy = ListNode(0)
        var curr = dummy
        while(heap.isNotEmpty()) {
            curr.next = heap.poll()
            println("adding to merge list: ${curr.next?.value}")
            curr = curr.next!!

            // If the popped node has a next node, add it to the heap
            if (curr.next != null) {
                heap.add(curr.next)
                println("adding to heap: ${curr.next!!.value}")
            }
        }
        return dummy.next
    }
}



//======================= For testing ===============================================
/** Utility: build linked list from array */
fun buildList(arr: IntArray): ListNode? {
    val dummy = ListNode(0)
    var tail = dummy
    for (x in arr) {
        tail.next = ListNode(x)
        tail = tail.next!!
    }
    return dummy.next
}

/** Utility: convert linked list to Kotlin list */
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
    val solver = MergeKSortedLists()

    data class TestCase(
        val input: Array<IntArray>,
        val expected: List<Int>,
        val description: String
    )

    fun buildLists(input: Array<IntArray>): Array<ListNode?> {
        return Array(input.size) { idx ->
            buildList(input[idx])
        }
    }

    val tests = listOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 4, 5),
                intArrayOf(1, 3, 4),
                intArrayOf(2, 6)
            ),
            expected = listOf(1, 1, 2, 3, 4, 4, 5, 6),
            description = "Classic case: 3 sorted lists"
        ),
        TestCase(
            input = arrayOf(),
            expected = emptyList(),
            description = "Edge case: no lists"
        ),
        TestCase(
            input = arrayOf(intArrayOf()),
            expected = emptyList(),
            description = "Edge case: single empty list"
        ),
        TestCase(
            input = arrayOf(intArrayOf(1)),
            expected = listOf(1),
            description = "Single list with one element"
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(-10, -5, 0),
                intArrayOf(-6, -2),
                intArrayOf(1, 2, 3)
            ),
            expected = listOf(-10, -6, -5, -2, 0, 1, 2, 3),
            description = "Handles negatives and positives"
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(),
                intArrayOf(),
                intArrayOf()
            ),
            expected = emptyList(),
            description = "All empty lists"
        )
    )

    var passed = 0

    for ((idx, test) in tests.withIndex()) {
        val lists = buildLists(test.input) // fresh lists every test run

        val mergedHead = solver.mergeKLists(lists)
        val actual = toList(mergedHead)

        if (actual == test.expected) {
            println("✅ PASS #${idx + 1}: ${test.description}")
            passed++
        } else {
            println("❌ FAIL #${idx + 1}: ${test.description}")
            println("   Expected: ${test.expected}")
            println("   Got     : $actual")
        }
    }

    println("\n==============================")
    println("Merge K Sorted Lists Summary")
    println("==============================")
    println("Total Tests : ${tests.size}")
    println("Passed      : $passed")
    println("Failed      : ${tests.size - passed}")

    if (passed == tests.size) {
        println("🎉 All test cases passed!")
    }
}

