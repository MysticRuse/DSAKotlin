package com.mr.patterns.twopointers.medium

import com.mr.patterns.ListNode

/**
 * 142. Linked List Cycle II
 * Medium
 * Topics: HashTable, Linked Lists, Two Pointers
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 * Do not modify the linked list.
 * Example 1:
 * Input: head = [3,2,0,-4, 2], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 * Example 2:
 * Input: head = [1,2,1], pos = 0
 * Output: tail connects to node index 0
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: no cycle
 * Explanation: There is no cycle in the linked list.
 * Constraints:
 * The number of the nodes in the list is in the range [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * pos is -1 or a valid index in the linked-list.
 * Follow up: Can you solve it using O(1) (i.e. constant) memory?
 */
class LinkedListCycleII {

    fun detectCycle(head: ListNode?): ListNode? {
        // Empty list of 1-node list.
        if (head == null || head.next == null) return null

        // 1. Find cycle using fast and slow pointers

        var slow: ListNode? = head
        var fast: ListNode? = head

        while (fast != null && fast.next!= null) {
            slow = slow?.next
            fast = fast.next?.next

            if (slow === fast) break
        }

        // 2. No cycle found.
        if (fast == null || fast.next == null) return null

        // 2. Found a cycle. Move fast to head & move both slow and fast
        // by one step until they are equal - that's the cycle begin node
        fast = head
        while(slow != fast) {
            slow = slow?.next
            fast = fast?.next
        }
        return slow
    }
}

/**
 * Utility function to build a linked list from an array and optionally create a cycle.
 *
 * pos = index where tail connects (0-based). If pos = -1, no cycle.
 *
 * Returns head node.
 */
fun buildLinkedListWithCycle(values: IntArray, pos: Int): ListNode? {
    if (values.isEmpty()) return null

    val nodes = Array(values.size) { ListNode(values[it]) }

    for (i in 0 until nodes.size - 1) {
        nodes[i].next = nodes[i + 1]
    }

    // Create cycle if pos is valid
    if (pos >= 0) {
        nodes[nodes.size - 1].next = nodes[pos]
    }

    return nodes[0]
}

/**
 * Utility function: returns the node at a given index (0-based) in a normal traversal.
 * Works fine for our test construction since we use arrays.
 */
fun getNodeAtIndex(head: ListNode?, index: Int): ListNode? {
    var curr = head
    var i = 0
    while (curr != null && i < index) {
        curr = curr.next
        i++
    }
    return curr
}

fun main() {
    val solver = LinkedListCycleII()

    data class TestCase(
        val values: IntArray,
        val pos: Int,
        val expectedCycleIndex: Int,
        val description: String
    )

    val tests = listOf(
        TestCase(
            values = intArrayOf(3, 2, 0, -4),
            pos = 1,
            expectedCycleIndex = 1,
            description = "Cycle exists: tail connects to node index 1 (value 2)"
        ),
        TestCase(
            values = intArrayOf(1, 2),
            pos = 0,
            expectedCycleIndex = 0,
            description = "Cycle exists: tail connects to head"
        ),
        TestCase(
            values = intArrayOf(1),
            pos = -1,
            expectedCycleIndex = -1,
            description = "Single node, no cycle"
        ),
        TestCase(
            values = intArrayOf(1),
            pos = 0,
            expectedCycleIndex = 0,
            description = "Single node cycle (self-loop)"
        ),
        TestCase(
            values = intArrayOf(1, 2, 3, 4, 5),
            pos = -1,
            expectedCycleIndex = -1,
            description = "Multiple nodes, no cycle"
        ),
        TestCase(
            values = intArrayOf(10, 20, 30, 40, 50, 60),
            pos = 3,
            expectedCycleIndex = 3,
            description = "Cycle exists: tail connects to middle node index 3 (value 40)"
        )
    )

    var passed = 0

    for ((idx, test) in tests.withIndex()) {
        val head = buildLinkedListWithCycle(test.values, test.pos)

        val expectedNode =
            if (test.expectedCycleIndex == -1) null
            else getNodeAtIndex(head, test.expectedCycleIndex)

        val actualNode = solver.detectCycle(head)

        // Must compare node reference identity, not just value.
        val ok = actualNode === expectedNode

        if (ok) {
            println("✅ PASS #${idx + 1}: ${test.description}")
            passed++
        } else {
            println("❌ FAIL #${idx + 1}: ${test.description}")
            println("   Expected cycle node index=${test.expectedCycleIndex}, node=${expectedNode?.value}")
            println("   Got node=${actualNode?.value}")
        }
    }

    println("\n==============================")
    println("LinkedList Cycle II Test Summary")
    println("==============================")
    println("Total Tests : ${tests.size}")
    println("Passed      : $passed")
    println("Failed      : ${tests.size - passed}")

    println("\nTest Coverage Summary:")
    println("1) Standard cycle in middle of list")
    println("2) Cycle where tail connects to head")
    println("3) Single node without cycle")
    println("4) Single node with self-loop cycle")
    println("5) Multi-node list without cycle")
    println("6) Cycle connecting to a middle node")
}

// Floyd's cycle detection algorithm
/**
 * Floyd's Tortoise and Hare Algorithm is a clever technique used to detect cycles in sequences or linked lists.
 * You can imagine it as a race between a fast "hare" and a slow "tortoise."
 * Imagine you're in a park, where there is a circular path inside the park and a straight path leading to the
 * circular path. If you start walking on the straight path into the circular path,
 * you'll eventually start walking in a cycle around the circular path.
 * Now imagine two people: a fast runner (the "hare") and a slow walker (the "tortoise").
 * They both start at the beginning of the path (the start of the linked list). The hare starts running twice as fast as
 * the tortoise. If the path does not contain a cycle (no circular path), the hare will reach the end of the straight
 * path first. Let's focus on the case where the cycle exists.
 * At some point, if there is a cycle (a circular path) in the park, the hare will enter this cycle earlier due to its
 * speed. Eventually, the tortoise will also enter the cycle. Since the hare is moving faster, it will lap the tortoise
 * at some point inside the cycle.
 *
 * Let's define a as the length of the path from the start of the list to the entrance of the cycle.
 * Let's define b as the length of the path from the cycle's entrance to the meeting point of the hare and the tortoise
 * inside the cycle.
 * Let's define c as the total length of the cycle.
 * The hare could lap the cycle multiple times before it meets the tortoise, especially if the cycle's size is
 * relatively small compared to the distance from the start to the cycle's entrance, or if the cycle's size is big,
 * and the hare enters it significantly before the tortoise does.
 *
 * When the tortoise and the hare meet inside the cycle, the tortoise has walked a+b distance.
 * On the other hand, the hare, which moves twice as fast, has covered this distance and maybe a few more laps around
 * the cycle. So, the total distance the hare ran is a+b plus k⋅c, where k is the number of times it lapped the cycle.
 * Because the hare moves twice as fast, this total distance is also equal to 2(a+b).
 * If we set these two equals: a+b+k⋅c=2(a+b), we obtain k⋅c=a+b.
 * This tells us that the number of times the hare laps the cycle times the length of the cycle equals the distance from
 * the head of the list to the meeting point.
 * The question now is where is the entrance to the cycle?
 * Here is where the second part of the algorithm comes in: after finding a meeting point inside the cycle,
 * you'll leave the tortoise there and move the hare back to the starting point of the park (or the head of the linked list). Then, have both the hare and the tortoise move at the same pace (one step at a time). When they meet again, that meeting point is the entrance to the cycle.
 *
 * You may ask, "Why is this the entrance to the cycle?" Well, let's consider the distances each has traveled.
 *
 * The first time that the hare and the tortoise meet within the cycle, we have established that:
 * The tortoise has traveled a+b distance.
 * The hare has traveled a+b+k⋅c distance, where k represents how many times the hare has lapped the cycle.
 * Because the hare moves at twice the speed, a+b+k⋅c=2(a+b), rearrange for k⋅c=a+b.
 * If we move the hare back to the start of the straight path and make it move at the same speed as the tortoise,
 * here's what happens:
 *
 * The hare has a distance to travel to reach the entrance of the cycle. We can rearrange the above equation to say that
 * the hare will reach the entrance of the cycle in a=k⋅c−b steps.
 * Currently, the tortoise is b away from the entrance of the cycle. In k⋅c−b steps, where will the tortoise be?
 * Relative to the entrance of the cycle, the tortoise will be at (k⋅c−b)+b=k⋅c. Because k is an integer, c is defined
 * as the length of the cycle, and this distance is relative to the entrance of the cycle, the tortoise will be at the
 * entrance! Because the tortoise and hare are now moving at the same speed, after k⋅c−b steps, they will meet again at
 * the entrance of the cycle. This must be the first time they meet again because the hare has just entered the cycle
 * again for the first time. Therefore, to find the entrance of the cycle, we don't actually need the values of a,b,c,k.
 * We can just return the node at which they meet again.
 */