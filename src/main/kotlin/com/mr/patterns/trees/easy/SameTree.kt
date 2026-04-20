package com.mr.patterns.trees.easy

import com.mr.patterns.TreeNode

/**
 * 100. Same Tree
 * Easy
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 * Example 1:
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 *
 * Example 2:
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 *
 * Example 3:
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 * Constraints:
 * The number of nodes in both trees is in the range [0, 100].
 * -10^4 <= Node.val <= 10^4
 */
class SameTree {

    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q ==  null) return true
        if (p == null || q == null) return false

        if (p.value != q.value) return false

        return isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right)
    }
}

fun main() {
    val solver = SameTree()

    fun runTest(testName: String, p: TreeNode?, q: TreeNode?, expected: Boolean) {
        val actual = solver.isSameTree(p, q)

        if (actual == expected) {
            println("PASSED: $testName -> $actual")
        } else {
            println("FAILED: $testName")
            println("Expected: $expected")
            println("Actual  : $actual")
        }
        println("--------------------------------------------------")
    }

    // Test 1: Both null trees
    runTest("Both null", null, null, true)

    // Test 2: One null, one non-null
    val t1 = TreeNode(1)
    runTest("One null tree", t1, null, false)

    // Test 3: Single node trees same
    val a1 = TreeNode(5)
    val b1 = TreeNode(5)
    runTest("Single node same", a1, b1, true)

    // Test 4: Single node trees different values
    val a2 = TreeNode(5)
    val b2 = TreeNode(10)
    runTest("Single node different", a2, b2, false)

    // Test 5: Same structure and values (balanced tree)
    // Tree P:        1
    //              /   \
    //             2     3
    //            / \
    //           4   5
    val p1 = TreeNode(1)
    p1.left = TreeNode(2)
    p1.right = TreeNode(3)
    p1.left!!.left = TreeNode(4)
    p1.left!!.right = TreeNode(5)

    // Tree Q:        1
    //              /   \
    //             2     3
    //            / \
    //           4   5
    val q1 = TreeNode(1)
    q1.left = TreeNode(2)
    q1.right = TreeNode(3)
    q1.left!!.left = TreeNode(4)
    q1.left!!.right = TreeNode(5)

    runTest("Same balanced tree", p1, q1, true)

    // Test 6: Same structure but different value in one node
    // Change Q's node 5 to 99
    val q2 = TreeNode(1)
    q2.left = TreeNode(2)
    q2.right = TreeNode(3)
    q2.left!!.left = TreeNode(4)
    q2.left!!.right = TreeNode(99)

    runTest("Same structure, different value", p1, q2, false)

    // Test 7: Different structure but same values
    // Tree P:        1
    //              /   \
    //             2     3
    // Tree Q:        1
    //              /
    //             2
    //              \
    //               3
    val p2 = TreeNode(1)
    p2.left = TreeNode(2)
    p2.right = TreeNode(3)

    val q3 = TreeNode(1)
    q3.left = TreeNode(2)
    q3.left!!.right = TreeNode(3)

    runTest("Different structure", p2, q3, false)

    // Test 8: Both left skewed identical
    //  4
    // /
    //3
    ///
    //2
    val p3 = TreeNode(4)
    p3.left = TreeNode(3)
    p3.left!!.left = TreeNode(2)

    val q4 = TreeNode(4)
    q4.left = TreeNode(3)
    q4.left!!.left = TreeNode(2)

    runTest("Left skewed identical", p3, q4, true)

    // Test 9: One tree has extra node
    val q5 = TreeNode(4)
    q5.left = TreeNode(3)
    q5.left!!.left = TreeNode(2)
    q5.left!!.left!!.left = TreeNode(1)

    runTest("One tree has extra node", p3, q5, false)

    // Test 10: Complex tree identical
    //         10
    //        /  \
    //       5    15
    //      / \     \
    //     3   7     20
    val p4 = TreeNode(10)
    p4.left = TreeNode(5)
    p4.right = TreeNode(15)
    p4.left!!.left = TreeNode(3)
    p4.left!!.right = TreeNode(7)
    p4.right!!.right = TreeNode(20)

    val q6 = TreeNode(10)
    q6.left = TreeNode(5)
    q6.right = TreeNode(15)
    q6.left!!.left = TreeNode(3)
    q6.left!!.right = TreeNode(7)
    q6.right!!.right = TreeNode(20)

    runTest("Complex identical tree", p4, q6, true)
}