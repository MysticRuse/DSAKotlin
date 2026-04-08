package com.mr.patterns.trees.easy

import com.mr.patterns.trees.TreeNode

/**
 * 111. Minimum Depth of Binary Tree
 * Easy
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Note: A leaf is a node with no children.
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 * Example 2:
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 * Constraints:
 * The number of nodes in the tree is in the range [0, 105].
 * -1000 <= Node.val <= 1000
 */
class MinDepthOfBinaryTree {

    fun minDepth(root: TreeNode?): Int {

        fun dfs(node: TreeNode?): Int {
            if (node == null) return 0

            // If left tree is empty, traverse right tree
            if (node.left == null) return 1 + dfs(node.right)

            // If right tree is empty, traverse left tree
            if (node.right == null) return 1 + dfs(node.left)

            // Both trees are not empty, traverse both trees
            return 1 + minOf(dfs(node.left), dfs(node.right))
        }

        return dfs(root)
    }
}

fun main() {
    val solver = MinDepthOfBinaryTree()

    var testNo = 1
    var passed = 0
    var failed = 0
    fun runTest(testName: String, root: TreeNode?, expected: Int) {
        val actual = solver.minDepth(root)
        if (actual == expected) {
            println("${testNo++}. PASSED: $testName -> minDepth = $actual")
            passed++
        } else {
            println("${testNo++}. FAILED: $testName")
            println("Expected: $expected")
            println("Actual  : $actual")
            failed++
        }
        println("--------------------------------------------------")
    }
    // Test 1: Empty tree
    runTest("Empty tree", null, 0)

    // Test 2: Single node
    val single = TreeNode(1)
    runTest("Single node", single, 1)

    // Test 3: Balanced tree
    //        1
    //      /   \
    //     2     3
    val balanced = TreeNode(1)
    balanced.left = TreeNode(2)
    balanced.right = TreeNode(3)
    runTest("Balanced tree", balanced, 2)

    // Test 4: Left skewed tree
    //     1
    //    /
    //   2
    //  /
    // 3
    val leftSkewed = TreeNode(1)
    leftSkewed.left = TreeNode(2)
    leftSkewed.left!!.left = TreeNode(3)
    runTest("Left skewed tree", leftSkewed, 3)

    // Test 5: Right skewed tree
    // 1
    //  \
    //   2
    //    \
    //     3
    val rightSkewed = TreeNode(1)
    rightSkewed.right = TreeNode(2)
    rightSkewed.right!!.right = TreeNode(3)
    runTest("Right skewed tree", rightSkewed, 3)

    // Test 6: Minimum depth on right side (short leaf exists)
    //        1
    //       / \
    //      2   3
    //     /
    //    4
    //   /
    //  5
    val tree1 = TreeNode(1)
    tree1.left = TreeNode(2)
    tree1.right = TreeNode(3)
    tree1.left!!.left = TreeNode(4)
    tree1.left!!.left!!.left = TreeNode(5)
    runTest("Min depth on right side", tree1, 2)

    // Test 7: Node with only one child (important tricky case)
    //      1
    //       \
    //        2
    //       /
    //      3
    val tree2 = TreeNode(1)
    tree2.right = TreeNode(2)
    tree2.right!!.left = TreeNode(3)
    runTest("One child chain zig-zag", tree2, 3)

    // Test 8: More complex tree
    //         10
    //        /  \
    //       5    15
    //            /
    //           12
    val tree3 = TreeNode(10)
    tree3.left = TreeNode(5)
    tree3.right = TreeNode(15)
    tree3.right!!.left = TreeNode(12)
    runTest("Complex tree", tree3, 2)

    // Test 9: Both sides deep but different min depth
    //          1
    //        /   \
    //       2     3
    //      /       \
    //     4         5
    //                \
    //                 6
    val tree4 = TreeNode(1)
    tree4.left = TreeNode(2)
    tree4.right = TreeNode(3)
    tree4.left!!.left = TreeNode(4)
    tree4.right!!.right = TreeNode(5)
    tree4.right!!.right!!.right = TreeNode(6)
    runTest("Different min depth between subtrees", tree4, 3)

    println("Total tests: ${testNo - 1}")
    println("Passed: $passed")
    println("Failed: $failed")
}