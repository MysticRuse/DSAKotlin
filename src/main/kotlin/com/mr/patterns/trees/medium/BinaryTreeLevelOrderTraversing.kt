package com.mr.patterns.trees.medium

import com.mr.patterns.TreeNode

/**
 * 102. Binary Tree Level Order Traversal
 * Medium
 * Topics: Tree, BFS, Breadth-First Search Binary Tree
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 * Input: root = []
 * Output: []
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 */
class BinaryTreeLevelOrderTraversing {

    fun traverseLevelOrder(root: TreeNode?): List<List<Int>> {
        // BFS pattern
        val result =mutableListOf<MutableList<Int>>()
        if (root == null) return result

        val queue = ArrayDeque<TreeNode>()
        queue.add(root)

        while(queue.isNotEmpty()) {
            val qSize = queue.size
            val level = mutableListOf<Int>()

            for (i in 0 until qSize) {
                val node= queue.removeFirst()
                level.add(node.value)
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
                //if (node.left != null) queue.add(node.left!!)
                //if (node.right != null) queue.add(node.right!!)
            }
            result.add(level)
        }

        return result
    }
}

fun main() {
    val solver = BinaryTreeLevelOrderTraversing()

    var totalTests = 0
    var passedTests = 0
    var failedTests = 0

    fun runTest(testName: String, root: TreeNode?, expected: List<List<Int>>) {
        totalTests++
        val actual = solver.traverseLevelOrder(root)

        if (actual == expected) {
            passedTests++
            println("PASSED: $testName")
            println("Output: $actual")
        } else {
            failedTests++
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
    val single = TreeNode(1)
    runTest(
        testName = "Single node",
        root = single,
        expected = listOf(listOf(1))
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
        expected = listOf(
            listOf(1),
            listOf(2, 3),
            listOf(4, 5, 6, 7)
        )
    )

    // Test 4: Left skewed tree
    //     1
    //    /
    //   2
    //  /
    // 3
    val leftSkewed = TreeNode(1)
    leftSkewed.left = TreeNode(2)
    leftSkewed.left!!.left = TreeNode(3)

    runTest(
        testName = "Left skewed tree",
        root = leftSkewed,
        expected = listOf(
            listOf(1),
            listOf(2),
            listOf(3)
        )
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

    runTest(
        testName = "Right skewed tree",
        root = rightSkewed,
        expected = listOf(
            listOf(1),
            listOf(2),
            listOf(3)
        )
    )

    // Test 6: Random shape tree
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
        testName = "Random shape tree",
        root = random,
        expected = listOf(
            listOf(10),
            listOf(5, 20),
            listOf(8),
            listOf(7)
        )
    )

    // Summary
    println("\n================== TEST SUMMARY ==================")
    println("Total Tests Run : $totalTests")
    println("Passed          : $passedTests")
    println("Failed          : $failedTests")
    println("==================================================")
}

