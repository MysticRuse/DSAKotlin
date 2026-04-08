package com.mr.patterns.trees.medium

import com.mr.patterns.trees.TreeNode
import kotlin.test.Test
import kotlin.test.assertEquals

class BinaryTreeLevelOrderTraversing {

    fun traverseLevelOrder(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()
        if (root == null) return result

        val queue = ArrayDeque<TreeNode>()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val qSize = queue.size
            val level = mutableListOf<Int>()

            for (i in 0 until qSize) {
                val node = queue.removeFirst()
                level.add(node.value)

                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
            result.add(level)
        }

        return result
    }
}

class BinaryTreeLevelOrderTraversingTest {

    private val solver = BinaryTreeLevelOrderTraversing()

    @Test
    fun testEmptyTree() {
        val result = solver.traverseLevelOrder(null)
        assertEquals(emptyList<List<Int>>(), result)
    }

    @Test
    fun testSingleNode() {
        val root = TreeNode(1)
        val result = solver.traverseLevelOrder(root)

        assertEquals(
            listOf(listOf(1)),
            result
        )
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

        val result = solver.traverseLevelOrder(root)

        assertEquals(
            listOf(
                listOf(1),
                listOf(2, 3),
                listOf(4, 5, 6, 7)
            ),
            result
        )
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

        val result = solver.traverseLevelOrder(root)

        assertEquals(
            listOf(
                listOf(1),
                listOf(2),
                listOf(3)
            ),
            result
        )
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

        val result = solver.traverseLevelOrder(root)

        assertEquals(
            listOf(
                listOf(1),
                listOf(2),
                listOf(3)
            ),
            result
        )
    }

    @Test
    fun testRandomShapeTree() {
        //        10
        //       /  \
        //      5    20
        //       \
        //        8
        //       /
        //      7
        val root = TreeNode(10)
        root.left = TreeNode(5)
        root.right = TreeNode(20)
        root.left!!.right = TreeNode(8)
        root.left!!.right!!.left = TreeNode(7)

        val result = solver.traverseLevelOrder(root)

        assertEquals(
            listOf(
                listOf(10),
                listOf(5, 20),
                listOf(8),
                listOf(7)
            ),
            result
        )
    }
}