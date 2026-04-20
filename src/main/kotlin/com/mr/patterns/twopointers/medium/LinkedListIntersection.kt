package com.mr.patterns.twopointers.medium

import com.mr.patterns.ListNode

/**
 * 160. Intersection of Two Linked Lists
 * Easy/Medium
 * Topics: Linked List, Two Pointers, HashTable
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 * Note that the linked lists must retain their original structure after the function returns.
 * Custom Judge:
 * The inputs to the judge are given as follows (your program is not given these inputs):
 * intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
 * listA - The first linked list.
 * listB - The second linked list.
 * skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
 * skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
 * The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.
 *
 * Example 1:
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * Output: Intersected at '8'
 * Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 * - Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2nd node in A and 3rd node in B) are different node references. In other words, they point to two different locations in memory, while the nodes with value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.
 * Example 2:
 * Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * Output: Intersected at '2'
 * Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
 * Example 3:
 * Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * Output: No intersection
 * Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
 * Explanation: The two lists do not intersect, so return null.
 * Constraints:
 * The number of nodes of listA is in the m.
 * The number of nodes of listB is in the n.
 * 1 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * intersectVal is 0 if listA and listB do not intersect.
 * intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.
 *
 * Follow up: Could you write a solution that runs in O(m + n) time and use only O(1) memory?
 */

class LinkedListIntersection {

    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        var p1 = headA
        var p2 = headB

        // DEBUG GUARD: prevents infinite loop if input has a cycle
        var steps = 0
        val maxSteps = 1000

        while (p1 != p2) {
            p1 = if (p1 == null) headB else p1.next
            p2 = if (p2 == null) headA else p2.next

            steps++
            if (steps > maxSteps) {
                throw RuntimeException("Infinite loop detected! Input lists likely contain a cycle.")
            }
        }

        return p1
    }
}

// Utility: build linked list from IntArray
fun buildList(arr: IntArray): ListNode? {
    if (arr.isEmpty()) return null

    val head = ListNode(arr[0])
    var curr = head

    for (i in 1 until arr.size) {
        curr.next = ListNode(arr[i])
        curr = curr.next!!
    }

    return head
}

// Utility: get tail node
fun getTail(head: ListNode?): ListNode? {
    var curr = head
    while (curr?.next != null) {
        curr = curr.next
    }
    return curr
}

/**
 * Build two lists with intersection:
 * A = prefixA + common
 * B = prefixB + common
 */
fun buildIntersectingLists(
    prefixA: IntArray,
    prefixB: IntArray,
    common: IntArray
): Triple<ListNode?, ListNode?, ListNode?> {

    val commonHead = buildList(common)

    val headA = buildList(prefixA)
    val headB = buildList(prefixB)

    if (headA != null) getTail(headA)?.next = commonHead
    if (headB != null) getTail(headB)?.next = commonHead

    val finalA = headA ?: commonHead
    val finalB = headB ?: commonHead

    return Triple(finalA, finalB, commonHead)
}

fun main() {
    val solver = LinkedListIntersection()

    data class TestCase(
        val headA: ListNode?,
        val headB: ListNode?,
        val expectedIntersection: ListNode?,
        val description: String
    )

    // Test 1: Standard intersection
    val (a1, b1, inter1) = buildIntersectingLists(
        prefixA = intArrayOf(4, 1),
        prefixB = intArrayOf(5, 6, 1),
        common = intArrayOf(8, 4, 5)
    )

    // Test 2: No intersection (completely separate lists)
    val a2 = buildList(intArrayOf(1, 2, 3))
    val b2 = buildList(intArrayOf(4, 5))

    // Test 3: Intersection at head (same list)
    val common3 = buildList(intArrayOf(7, 8, 9))
    val a3 = common3
    val b3 = common3

    // Test 4: One list is null
    val a4: ListNode? = null
    val b4 = buildList(intArrayOf(1, 2))

    // Test 5: B starts at intersection
    val (a5, b5, inter5) = buildIntersectingLists(
        prefixA = intArrayOf(10, 20, 30),
        prefixB = intArrayOf(),
        common = intArrayOf(40, 50)
    )

    val tests = listOf(
        TestCase(a1, b1, inter1, "Standard intersection in middle"),
        TestCase(a2, b2, null, "No intersection"),
        TestCase(a3, b3, common3, "Intersection at head (same list)"),
        TestCase(a4, b4, null, "One list is null"),
        TestCase(a5, b5, inter5, "One list starts exactly at intersection")
    )

    var passed = 0

    for ((idx, test) in tests.withIndex()) {
        println("\nRunning Test #${idx + 1}: ${test.description}")

        try {
            val actual = solver.getIntersectionNode(test.headA, test.headB)
            val ok = actual === test.expectedIntersection

            if (ok) {
                println("✅ PASS #${idx + 1}: ${test.description}")
                passed++
            } else {
                println("❌ FAIL #${idx + 1}: ${test.description}")
                println("   Expected = ${test.expectedIntersection?.value}")
                println("   Got      = ${actual?.value}")
            }
        } catch (e: Exception) {
            println("💥 ERROR in Test #${idx + 1}: ${test.description}")
            println("   ${e.message}")
        }
    }

    println("\n==============================")
    println("Linked List Intersection Summary")
    println("==============================")
    println("Total Tests : ${tests.size}")
    println("Passed      : $passed")
    println("Failed      : ${tests.size - passed}")
}

/**
 * Time Complexity: O(m+n)
 * In the worst case, each list is traversed twice giving 2⋅M+2⋅N, which is equivalent to O(N+M).
 * This is because the pointers firstly go down each list so that they can be "lined up" and then in the second
 * iteration, the intersection node is searched for.
 *
 * An interesting observation you might have made is that when the lists are of the same length, this algorithm only traverses each list once. This is because the pointers are already "lined up" from the start, so the additional pass is unnecessary.
 * Space Complexity: O(1)
 */

/**
 * Observe that while list A and list B could be different lengths, that the shared "tail" following the intersection
 * has to be the same length.
 * Imagine that we have two linked lists, A and B, and we know that their lengths are N and M respectively
 * (these can be calculated with O(1) space and in time proportional to the length of the list). We'll imagine that N=5
 * and M=8.
 * Two linked lists with question marks on their nodes. The first is 5 nodes long, and the second is 8 nodes long.
 * Because the "tails" must be the same length, we can conclude that if there is an intersection, then the intersection
 * node will be one of these 5 possibilities.
 * The two linked lists from above with arrows showing how the last 5 nodes of each list could be a match.
 * So, to check for each of these pairs, we would start by setting a pointer at the start of the shorter list, and a
 * pointer at the first possible matching node of the longer list. The position of this node is simply the difference
 * between the two lengths, that is, ∣M−N∣.
 *
 * The two linked lists from above with a p1 pointer at the head of the first, and a p2 pointer at the 4th node of the second.
 *
 * Then, we just need to step the two pointers through the list, each time checking whether or not the nodes are the same.
 *
 * In code, we could write this algorithm with 4 loops, one after the other, each doing the following:
 *
 * Calculate N; the length of list A.
 * Calculate M; the length of list B.
 * Set the start pointer for the longer list.
 * Step the pointers through the list together.
 * While this would have a time complexity of O(N+M) and a space complexity of O(1) and would be fine for an interview, we can still simplify the code a bit! As some quick reassurance, most people will struggle to come up with this next part by themselves. It takes practice and seeing lots of linked list and other math problems.
 *
 * If we say that c is the shared part, a is exclusive part of list A and b is exclusive part of list B, then we can have one pointer that goes over a + c + b and the other that goes over b + c + a. Have a look at the diagram below, and this should be fairly intuitive.
 *
 * Diagram showing that one pointer could go over a + c + b while the other goes over b + c + a, and then both will end up on the intersection node.
 *
 * This is the above algorithm in disguise - one pointer is essentially measuring the length of the longer list, and the other is measuring the length of the shorter list, and then placing the start pointer for the longer list. Then both are stepping through the list together. By seeing the solution in this way though, we can now implement it as a single loop.
 */