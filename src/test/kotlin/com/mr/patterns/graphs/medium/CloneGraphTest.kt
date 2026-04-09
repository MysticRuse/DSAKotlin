package com.mr.patterns.graphs.medium

import com.mr.patterns.graphs.GraphNode
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CloneGraphTest {

    private fun validateClone(original: GraphNode?, clone: GraphNode?): Boolean {
        if (original == null && clone == null) return true
        if (original == null || clone == null) return false

        val visited = mutableSetOf<GraphNode>()
        val queue = ArrayDeque<Pair<GraphNode, GraphNode>>()

        queue.add(Pair(original, clone))

        while (queue.isNotEmpty()) {
            val (orig, cloned) = queue.removeFirst()

            // Must not be same object
            if (orig === cloned) return false

            // Value must match
            if (orig.value != cloned.value) return false

            // Neighbor size must match
            if (orig.neighbors.size != cloned.neighbors.size) return false

            if (visited.contains(orig)) continue
            visited.add(orig)

            for (i in orig.neighbors.indices) {
                val origNeighbor = orig.neighbors[i]
                val clonedNeighbor = cloned.neighbors[i]

                if (origNeighbor == null && clonedNeighbor == null) continue
                if (origNeighbor == null || clonedNeighbor == null) return false

                queue.add(Pair(origNeighbor, clonedNeighbor))
            }
        }

        return true
    }

    @Test
    fun testNullGraph() {
        val solver = CloneGraph()
        val cloned = solver.doGraphClone(null)
        assertNull(cloned)
    }

    @Test
    fun testSingleNodeNoNeighbors() {
        val solver = CloneGraph()

        val node = GraphNode(1)
        val cloned = solver.doGraphClone(node)

        assertTrue(validateClone(node, cloned))
    }

    @Test
    fun testTwoNodesUndirectedConnection() {
        val solver = CloneGraph()

        val node1 = GraphNode(1)
        val node2 = GraphNode(2)

        node1.neighbors.add(node2)
        node2.neighbors.add(node1)

        val cloned = solver.doGraphClone(node1)

        assertTrue(validateClone(node1, cloned))
    }

    @Test
    fun testCycleTriangleGraph() {
        val solver = CloneGraph()

        val a = GraphNode(1)
        val b = GraphNode(2)
        val c = GraphNode(3)

        a.neighbors.add(b)
        b.neighbors.add(c)
        c.neighbors.add(a)

        val cloned = solver.doGraphClone(a)

        assertTrue(validateClone(a, cloned))
    }

    @Test
    fun testSelfLoopGraph() {
        val solver = CloneGraph()

        val node = GraphNode(7)
        node.neighbors.add(node)

        val cloned = solver.doGraphClone(node)

        assertTrue(validateClone(node, cloned))
    }

    @Test
    fun testDisconnectedGraphClonesOnlyComponent() {
        val solver = CloneGraph()

        // Component 1
        val a = GraphNode(1)
        val b = GraphNode(2)
        a.neighbors.add(b)
        b.neighbors.add(a)

        // Component 2 (disconnected)
        val c = GraphNode(3)

        val cloned = solver.doGraphClone(a)

        // Clone should only contain component 1
        assertTrue(validateClone(a, cloned))
        assertNotNull(cloned)

        // Ensure cloned is not accidentally equal to disconnected node c
        assertNotEquals(c.value, cloned!!.value)
    }
}