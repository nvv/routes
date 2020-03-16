package com.pathfinder.engine.search

import com.pathfinder.engine.search.model.Graph
import com.pathfinder.engine.search.model.Vertex
import java.util.*

/**
 * @author Vlad Namashko
 */
class PathFinder<T, E>(private val graph: Graph<T, E>) {

    private val onPath: MutableMap<Vertex<T>, Boolean> = mutableMapOf<Vertex<T>, Boolean>().apply {
        graph.vertices.forEach { item -> put(item.value, false) }
    }

    private val path = LinkedList<Pair<T, E?>>()
    private var numberOfPaths: Int = 0

    // use DFS
    fun search(from: T, to: T, pathList: MutableList<LinkedList<Pair<T, E?>>>, edgeInfo: E? = null) {
        path.push(Pair(from, edgeInfo))
        val vertex = graph.getVertex(from)
        onPath[vertex] = true

        if (from == to) {
            pathList.add(LinkedList(path))
            numberOfPaths++
        } else {
            graph.getEdges(from).filter { edge -> onPath[edge.toVertex]?.not() == true }.forEach { edge -> search(edge.toVertex.vertex, to, pathList, edge.edgeParams) }
        }

        path.pop()
        onPath[vertex] = false
    }

}