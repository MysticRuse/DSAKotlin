package com.mr.patterns.graphs

class GraphNode(var value: Int) {
    var neighbors: ArrayList<GraphNode?> = ArrayList()
}