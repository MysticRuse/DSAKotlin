package com.mr.patterns.trees.medium

import com.mr.patterns.trees.TreeNode
import kotlin.test.Test
import kotlin.test.assertEquals

class BinaryTreeRightSideView {
    fun rightSideView(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()

        val result = mutableListOf<Int>()
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val qSize = queue.size
            for (i in 0 until qSize) {
                val node = queue.removeFirst()

                if (i == qSize - 1) result.add(node.value)

                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
        }
        return result
    }
}

class BinaryTreeRightSideViewTest {

    private val solver = BinaryTreeRightSideView()

    @Test
    fun testEmptyTree() {
        assertEquals(emptyList<Int>(), solver.rightSideView(null))
    }

    @Test
    fun testSingleNode() {
        val root = TreeNode(1)
        assertEquals(listOf(1), solver.rightSideView(root))
    }

    @Test
    fun testBalancedTree() {
        //        1
        //      /   \
        //     2     3
        //    / \   / \
        //   4  5  6  7
        val root = TreeNode(1)
        root.left = TreeNode(2)
        root.right = TreeNode(3)
        root.left!!.left = TreeNode(4)
        root.left!!.right = TreeNode(5)
        root.right!!.left = TreeNode(6)
        root.right!!.right = TreeNode(7)

        assertEquals(listOf(1, 3, 7), solver.rightSideView(root))
    }

    @Test
    fun testLeftSkewedTree() {
        //     1
        //    /
        //   2
        //  /
        // 3
        val root = TreeNode(1)
        root.left = TreeNode(2)
        root.left!!.left = TreeNode(3)

        assertEquals(listOf(1, 2, 3), solver.rightSideView(root))
    }

    @Test
    fun testRightSkewedTree() {
        // 1
        //  \
        //   2
        //    \
        //     3
        val root = TreeNode(1)
        root.right = TreeNode(2)
        root.right!!.right = TreeNode(3)

        assertEquals(listOf(1, 2, 3), solver.rightSideView(root))
    }

    @Test
    fun testMixedTree() {
        //        1
        //      /   \
        //     2     3
        //      \
        //       5
        //        \
        //         6
        val root = TreeNode(1)
        root.left = TreeNode(2)
        root.right = TreeNode(3)
        root.left!!.right = TreeNode(5)
        root.left!!.right!!.right = TreeNode(6)

        assertEquals(listOf(1, 3, 5, 6), solver.rightSideView(root))
    }

    @Test
    fun testDeeperLeftAffectsRightView() {
        //        10
        //       /  \
        //      5    20
        //       \
        //        8
        //         \
        //          9
        val root = TreeNode(10)
        root.left = TreeNode(5)
        root.right = TreeNode(20)
        root.left!!.right = TreeNode(8)
        root.left!!.right!!.right = TreeNode(9)

        assertEquals(listOf(10, 20, 8, 9), solver.rightSideView(root))
    }
}