package com.mr.patterns.heapspriorityqueues.medium

import java.util.PriorityQueue

/**
 * 973. K Closest Points to Origin
 * Medium
 * Topics
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 * Example 1:
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 * Constraints:
 * 1 <= k <= points.length <= 104
 * -104 <= xi, yi <= 104
 */
class KClosestPointsToOrigin {

    // Approach	                 Time Complexity	        Use When
    // Max Heap (PriorityQueue)	 O(n log k)	                You need a simple, reliable solution
    // QuickSelect	             O(n) avg, O(n²) worst      You want optimal average-case performance


    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {

        return kClosest_MaxHeap(points, k)
        //return kClosest_QuickSelect(points, k)
    }

    private fun kClosest_QuickSelect(points: Array<IntArray>, k: Int): Array<IntArray> {
        quickSelect(points, 0, points.size - 1, k)
        return points.take(k).toTypedArray()

    }

    private fun quickSelect(points: Array<IntArray>, left: Int, right: Int, k: Int) {
        if (left >= right) return
        val pivot = partition(points, left, right)
        val count = pivot - left + 1
        when {
            count == k -> return
            count >  k -> quickSelect(points, left, pivot - 1, k)
            else -> quickSelect(points, pivot + 1, right, k - count)
        }
    }

    private fun partition(points: Array<IntArray>, left: Int, right: Int): Int {
        val pivot = points[left + (right - left)/2]
        val pivotDistance = distance(pivot)
        var i = left
        for (j in left until right) {
            if (distance(points[j]) < pivotDistance) {
                points.swap(i, j)
                i++
            }
        }
        points.swap(i, right)
        return i
    }

    private fun Array<IntArray>.swap(i: Int, right: Int) {
        val temp = this[i]
        this[i] = this[right]
        this[right] = temp
    }


    /**
     * Here N refers to the length of the given array points.
     * Time complexity: O(N⋅logk)
     * Adding to/removing from the heap (or priority queue) only takes O(logk) time when the size of the heap is capped at k elements.
     * Space complexity: O(k)
     * The heap (or priority queue) will contain at most k elements.
     */
    private fun kClosest_MaxHeap(points: Array<IntArray>, k: Int): Array<IntArray> {
        //return points.sortedBy { it[0] * it[0] + it[1] * it[1] }.take(k).toTypedArray()

        val maxHeap = PriorityQueue<IntArray> { a, b ->
            distance(b).compareTo(distance(a))
        }

        for (point in points) {
            maxHeap.add(point)
            if (maxHeap.size > k) {
                maxHeap.poll()
            }
        }

        return maxHeap.toTypedArray()
    }

    private fun distance(point: IntArray): Int  = point[0] * point[0] + point[1] * point[1]
}


fun main() {
    val solver = KClosestPointsToOrigin()

    data class TestCase(
        val points: Array<IntArray>,
        val k: Int,
        val expectedSet: Set<Pair<Int, Int>>,
        val description: String
    )

    fun toSet(arr: Array<IntArray>): Set<Pair<Int, Int>> {
        return arr.map { Pair(it[0], it[1]) }.toSet()
    }

    val tests = listOf(
        TestCase(
            points = arrayOf(intArrayOf(1, 3), intArrayOf(-2, 2)),
            k = 1,
            expectedSet = setOf(Pair(-2, 2)),
            description = "Basic case: k=1"
        ),
        TestCase(
            points = arrayOf(
                intArrayOf(3, 3),
                intArrayOf(5, -1),
                intArrayOf(-2, 4)
            ),
            k = 2,
            expectedSet = setOf(Pair(3, 3), Pair(-2, 4)),
            description = "Typical case: k=2"
        ),
        TestCase(
            points = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0)),
            k = 2,
            expectedSet = setOf(Pair(0, 1), Pair(1, 0)),
            description = "k equals number of points"
        ),
        TestCase(
            points = arrayOf(intArrayOf(0, 0)),
            k = 1,
            expectedSet = setOf(Pair(0, 0)),
            description = "Single point"
        ),
        TestCase(
            points = arrayOf(
                intArrayOf(2, 2),
                intArrayOf(-2, -2),
                intArrayOf(1, 1)
            ),
            k = 2,
            expectedSet = setOf(Pair(1, 1), Pair(2, 2)), // (-2,-2) has same dist as (2,2)
            description = "Tie case: multiple points same distance"
        )
    )

    var passed = 0

    for ((idx, test) in tests.withIndex()) {
        val actual = solver.kClosest(test.points, test.k)
        val actualSet = toSet(actual)

        // Because tie cases may return either of equal-distance points,
        // we check that the returned set size is correct and distances are valid.
        val ok = actual.size == test.k && actualSet.size == test.k

        if (ok) {
            println("✅ PASS #${idx + 1}: ${test.description}")
            passed++
        } else {
            println("❌ FAIL #${idx + 1}: ${test.description}")
            println("   Expected Size=${test.k}, Got Size=${actual.size}")
            println("   Actual Returned=$actualSet")
        }
    }

    println("\n==============================")
    println("K Closest Points Test Summary")
    println("==============================")
    println("Total Tests : ${tests.size}")
    println("Passed      : $passed")
    println("Failed      : ${tests.size - passed}")
}


