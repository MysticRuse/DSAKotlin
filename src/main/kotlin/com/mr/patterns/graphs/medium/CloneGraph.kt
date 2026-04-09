package com.mr.patterns.graphs.medium

import com.mr.patterns.graphs.GraphNode

/**
 * 133. Clone Graph
 * Medium
 * Topics: Hash Table, DFS< BFS, Graph Theory
 *
 * Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 * Test case format:
 * For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
 * An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 *
 * Example 1:
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * Example 2:
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 * Example 3:
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 * Constraints:
 * The number of nodes in the graph is in the range [0, 100].
 * 1 <= Node.val <= 100
 * Node.val is unique for each node.
 * There are no repeated edges and no self-loops in the graph.
 * The Graph is connected and all nodes can be visited starting from the given node.
 */
class CloneGraph {
    fun doGraphClone(node: GraphNode?): GraphNode? {
        if (node == null) return null
        val visited = mutableMapOf<GraphNode, GraphNode>()

        fun dfsHelper(node: GraphNode): GraphNode? {
            if (visited.containsKey(node)) return visited[node]

            val clonedNode = GraphNode(node.value)
            visited[node] = clonedNode

            for (neighbor in node.neighbors) {
                if (neighbor != null) {
                    clonedNode.neighbors.add(dfsHelper(neighbor))
                }
            }

            return clonedNode
        }

        return dfsHelper(node)
    }
    /**
     * HashMap and DFS
     * 1. Keep a map of the actual node and clonedNode.
     * 2. Run dfs for neighbors of the actual node.
     */
    // Time Complexity: O(N+M), where N is a number of nodes (vertices) and M is a number of edges.
    // Space Complexity: O(N). This space is occupied by the visited hash map and in addition to that, space would also be occupied by the recursion stack since we are adopting a recursive approach here.
    // The space occupied by the recursion stack would be equal to O(H) where H is the height of the graph.
    // Overall, the space complexity would be O(N).
}

// ------------------- MAIN FUNCTION TESTS -------------------

fun main() {
    fun validateClone(original: GraphNode?, clone: GraphNode?): Boolean {
        if (original == null && clone == null) return true
        if (original == null || clone == null) return false

        val visited = mutableSetOf<GraphNode>()
        val queue = ArrayDeque<Pair<GraphNode, GraphNode>>()

        queue.add(Pair(original, clone))

        while (queue.isNotEmpty()) {
            val (orig, cloned) = queue.removeFirst()

            // Must not be same reference
            if (orig === cloned) return false

            // Values must match
            if (orig.value != cloned.value) return false

            // Neighbors count must match
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

    var total = 0
    var pass = 0
    var fail = 0

    fun runTest(testName: String, node: GraphNode?, expectedNull: Boolean = false) {
        total++
        val solver = CloneGraph()
        val cloned = solver.doGraphClone(node)

        val result = if (expectedNull) cloned == null else validateClone(node, cloned)

        if (result) {
            pass++
            println("✅ PASS: $testName")
        } else {
            fail++
            println("❌ FAIL: $testName")
        }
    }

    // ---------- TEST CASE 1: Null Graph ----------
    runTest("Null Graph", null, expectedNull = true)

    // ---------- TEST CASE 2: Single Node (no neighbors) ----------
    val node1 = GraphNode(1)
    runTest("Single Node No Neighbors", node1)

    // ---------- TEST CASE 3: Two Nodes Connected ----------
    val node2 = GraphNode(1)
    val node3 = GraphNode(2)
    node2.neighbors.add(node3)
    node3.neighbors.add(node2)
    runTest("Two Nodes Undirected Connection", node2)

    // ---------- TEST CASE 4: Cycle Graph (triangle) ----------
    val a = GraphNode(1)
    val b = GraphNode(2)
    val c = GraphNode(3)

    a.neighbors.add(b)
    b.neighbors.add(c)
    c.neighbors.add(a)

    runTest("Cycle Graph Triangle", a)

    // ---------- TEST CASE 5: Self Loop ----------
    val self = GraphNode(7)
    self.neighbors.add(self)
    runTest("Self Loop Node", self)

    // ---------- SUMMARY ----------
    println("\n==================== TEST SUMMARY ====================")
    println("Total Tests  : $total")
    println("Passed Tests : $pass")
    println("Failed Tests : $fail")
    println("======================================================")
}