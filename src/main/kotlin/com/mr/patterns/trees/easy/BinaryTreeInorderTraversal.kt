package com.mr.patterns.trees.easy

import com.mr.patterns.TreeNode

/**
 * 94. Binary Tree Inorder Traversal
 * Easy
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
 * Explanation:
 * Example 2:
 * Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
 * Output: [4,2,6,5,7,1,3,9,8]
 * Explanation:
 * Example 3:
 * Input: root = []
 * Output: []
 * Example 4:
 * Input: root = [1]
 * Output: [1]
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */

class BinaryTreeInorderTraversal {


    fun inorderTraversal(root: TreeNode?): List<Int> {
        return inorderTraversalRecursive(root)
        //return inorderTraversalIterative(root)
    }

    private fun inorderTraversalRecursive(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()

        fun dfs(node: TreeNode?) {
            if (node == null) return
            dfs(node.left)
            result.add(node.value)
            dfs(node.right)
        }

        dfs(root)
        return result
    }

    private fun inorderTraversalIterative(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        val stack = mutableListOf<TreeNode>()

        var node = root
        while (node != null || stack.isNotEmpty()) {
            while (node != null) {
                stack.add(node)
                node = node.left
            }
            node = stack.removeLast()
            result.add(node.value)
            node = node.right
        }

        return result
    }
}

fun main() {
    val solver = BinaryTreeInorderTraversal()

    fun runTest(testName: String, root: TreeNode?, expected: List<Int>) {
        val actual = solver.inorderTraversal(root)
        if (actual == expected) {
            println("PASSED: $testName -> $actual")
        } else {
            println("FAILED: $testName")
            println("Expected: $expected")
            println("Actual  : $actual")
        }
        println("--------------------------------------------------")
    }

    // Test 1: Empty tree
    runTest(
        testName = "Empty tree",
        root = null,
        expected = emptyList()
    )

    // Test 2: Single node
    val single = TreeNode(10)
    runTest(
        testName = "Single node",
        root = single,
        expected = listOf(10)
    )

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

    runTest(
        testName = "Balanced tree",
        root = balanced,
        expected = listOf(4, 2, 5, 1, 6, 3, 7)
    )

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

    runTest(
        testName = "Left skewed tree",
        root = leftSkewed,
        expected = listOf(1, 2, 3, 4, 5)
    )

    // Test 5: Right skewed tree
    // 1
    //  \
    //   2
    //    \
    //     3
    val rightSkewed = TreeNode(1)
    rightSkewed.right = TreeNode(2)
    rightSkewed.right!!.right = TreeNode(3)
    rightSkewed.right!!.right!!.right = TreeNode(4)

    runTest(
        testName = "Right skewed tree",
        root = rightSkewed,
        expected = listOf(1, 2, 3, 4)
    )

    // Test 6: Random tree (not BST)
    //        10
    //       /  \
    //      5    20
    //       \
    //        8
    //       /
    //      7
    val random = TreeNode(10)
    random.left = TreeNode(5)
    random.right = TreeNode(20)
    random.left!!.right = TreeNode(8)
    random.left!!.right!!.left = TreeNode(7)

    runTest(
        testName = "Random shaped tree",
        root = random,
        expected = listOf(5, 7, 8, 10, 20)
    )

    // Test 7: Tree with negative values
    //        0
    //      /   \
    //    -3     9
    //      \
    //      -2
    val negativeTree = TreeNode(0)
    negativeTree.left = TreeNode(-3)
    negativeTree.right = TreeNode(9)
    negativeTree.left!!.right = TreeNode(-2)

    runTest(
        testName = "Tree with negative values",
        root = negativeTree,
        expected = listOf(-3, -2, 0, 9)
    )
}