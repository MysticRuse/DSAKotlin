package com.mr.patterns.trees.easy

import com.mr.patterns.trees.TreeNode

/**
 * 104. Maximum Depth of Binary Tree
 * Easy
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 *
 * Example 2:
 * Input: root = [1,null,2]
 * Output: 2
 * Constraints:
 * The number of nodes in the tree is in the range [0, 104].
 * -100 <= Node.val <= 100
 */
class MaxDepthOfBinaryTree {

    // TC: O(N) : We visit each node exactly once
    // SC: Worst case - tree completely unbalanced, call stack storage: O(N)
    //     Best case - tree completely balanced - height of tree: logN SC: O(logN)
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        val leftHeight = maxDepth(root.left)
        val rightHeight = maxDepth(root.right)
        return 1 + maxOf(leftHeight, rightHeight)
    }
}

fun main() {
    val solver = MaxDepthOfBinaryTree()

    fun runTest(testName: String, root: TreeNode?, expected: Int) {
        val actual = solver.maxDepth(root)
        if (actual == expected) {
            println("PASSED: $testName -> Depth = $actual")
        } else {
            println("FAILED: $testName")
            println("Expected: $expected")
            println("Actual  : $actual")
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
    //    / \   / \
    //   4  5  6  7
    val balanced = TreeNode(1)
    balanced.left = TreeNode(2)
    balanced.right = TreeNode(3)
    balanced.left!!.left = TreeNode(4)
    balanced.left!!.right = TreeNode(5)
    balanced.right!!.left = TreeNode(6)
    balanced.right!!.right = TreeNode(7)

    runTest("Balanced tree", balanced, 3)

    // Test 4: Left skewed tree
    //     5
    //    /
    //   4
    //  /
    // 3
    // ...
    val leftSkewed = TreeNode(5)
    leftSkewed.left = TreeNode(4)
    leftSkewed.left!!.left = TreeNode(3)
    leftSkewed.left!!.left!!.left = TreeNode(2)
    leftSkewed.left!!.left!!.left!!.left = TreeNode(1)

    runTest("Left skewed tree", leftSkewed, 5)

    // Test 5: Right skewed tree
    // 1
    //  \
    //   2
    //    \
    //     3
    //      \
    //       4
    val rightSkewed = TreeNode(1)
    rightSkewed.right = TreeNode(2)
    rightSkewed.right!!.right = TreeNode(3)
    rightSkewed.right!!.right!!.right = TreeNode(4)

    runTest("Right skewed tree", rightSkewed, 4)

    // Test 6: Unbalanced tree
    //        10
    //       /  \
    //      5    20
    //     /
    //    3
    //   /
    //  2
    val unbalanced = TreeNode(10)
    unbalanced.left = TreeNode(5)
    unbalanced.right = TreeNode(20)
    unbalanced.left!!.left = TreeNode(3)
    unbalanced.left!!.left!!.left = TreeNode(2)

    runTest("Unbalanced tree", unbalanced, 4)

    // Test 7: Random shape tree
    //        1
    //       /
    //      2
    //       \
    //        3
    //       /
    //      4
    val random = TreeNode(1)
    random.left = TreeNode(2)
    random.left!!.right = TreeNode(3)
    random.left!!.right!!.left = TreeNode(4)

    runTest("Random shape tree", random, 4)
}

