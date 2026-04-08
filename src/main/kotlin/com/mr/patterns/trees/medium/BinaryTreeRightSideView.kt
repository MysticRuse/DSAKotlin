package com.mr.patterns.trees.medium

import com.mr.patterns.trees.TreeNode

/**
 * 199. Binary Tree Right Side View
 * Medium
 * Topics: Tree, DFS, BFS, Binary Tree
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * Example 1:
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 * Example 2:
 * Input: root = [1,2,3,4,null,null,null,5]
 * Output: [1,3,4,5]
 * Explanation:
 * Example 3:
 * Input: root = [1,null,3]
 * Output: [1,3]
 * Example 4:
 * Input: root = []
 * Output: []
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
class BinaryTreeRightSideView {

    /**
     * Which approach to choose, BFS or DFS?
     * The problem is to return a list of the last elements from all levels, so it's way more natural to implement BFS here.
     * Time complexity is the same O(N) both for DFS and BFS since one has to visit all nodes.
     * Space complexity is O(H) for DFS and O(D) for BFS, where H is a tree height, and D is a tree diameter.
     * They both result in O(N) space in the worst-case scenarios: skewed tree for DFS and complete tree for BFS.
     *
     * BFS wins for this problem, so let's use the opportunity to check out three different BFS implementations with the queue.
     */

    fun rightSideView(root: TreeNode?): List<Int> {
        return rightSideViewBfs(root)
        //return rightSideViewDfs(root)
    }

    private fun rightSideViewBfs(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()

        val result = mutableListOf<Int>()
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val qSize = queue.size
            for (i in 0 until qSize) {
                val node = queue.removeFirst()

                // Add the node's value to the result if it's the last node in the level
                if (i == qSize - 1) result.add(node.value)

                // Add the children to the queue in the right order
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
        }
        return result.toList()
    }

    fun rightSideViewDfs(root: TreeNode?) : List<Int> {
        val result = mutableListOf<Int>()
        dfsHelper(root, 0, result)
        return result
    }

    fun dfsHelper(node: TreeNode?, level: Int, result: MutableList<Int>) {
        if (node == null) return

        if (level == result.size) result.add(node.value)

        // NOTE!! Since right side view, have to do dfs on right first.
        // Else will end up with left side view.
        node.right?.let { dfsHelper(it, level+1, result) }
        node.left?.let { dfsHelper(it, level+1, result) }
    }
}

fun main() {
    val solver = BinaryTreeRightSideView()

    var totalTests = 0
    var passedTests = 0
    var failedTests = 0

    fun runTest(testName: String, root: TreeNode?, expected: List<Int>) {
        totalTests++
        val actual = solver.rightSideView(root)

        if (actual == expected) {
            passedTests++
            println("PASSED: $testName -> $actual")
        } else {
            failedTests++
            println("FAILED: $testName")
            println("Expected: $expected")
            println("Actual  : $actual")
        }
        println("--------------------------------------------------")
    }

    // Test 1: Empty tree
    runTest("Empty tree", null, emptyList())

    // Test 2: Single node
    val single = TreeNode(1)
    runTest("Single node", single, listOf(1))

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

    runTest("Balanced tree", balanced, listOf(1, 3, 7))

    // Test 4: Left skewed tree
    //     1
    //    /
    //   2
    //  /
    // 3
    val leftSkewed = TreeNode(1)
    leftSkewed.left = TreeNode(2)
    leftSkewed.left!!.left = TreeNode(3)

    runTest("Left skewed tree", leftSkewed, listOf(1, 2, 3))

    // Test 5: Right skewed tree
    // 1
    //  \
    //   2
    //    \
    //     3
    val rightSkewed = TreeNode(1)
    rightSkewed.right = TreeNode(2)
    rightSkewed.right!!.right = TreeNode(3)

    runTest("Right skewed tree", rightSkewed, listOf(1, 2, 3))

    // Test 6: Mixed tree
    //        1
    //      /   \
    //     2     3
    //      \
    //       5
    //        \
    //         6
    val mixed = TreeNode(1)
    mixed.left = TreeNode(2)
    mixed.right = TreeNode(3)
    mixed.left!!.right = TreeNode(5)
    mixed.left!!.right!!.right = TreeNode(6)

    runTest("Mixed tree", mixed, listOf(1, 3, 5, 6))

    // Test 7: Right view changes due to deeper left subtree
    //        10
    //       /  \
    //      5    20
    //       \
    //        8
    //         \
    //          9
    val tree = TreeNode(10)
    tree.left = TreeNode(5)
    tree.right = TreeNode(20)
    tree.left!!.right = TreeNode(8)
    tree.left!!.right!!.right = TreeNode(9)

    runTest("Deeper left affects right view", tree, listOf(10, 20, 8, 9))

    // Summary
    println("\n================== TEST SUMMARY ==================")
    println("Total Tests Run : $totalTests")
    println("Passed          : $passedTests")
    println("Failed          : $failedTests")
    println("==================================================")
}