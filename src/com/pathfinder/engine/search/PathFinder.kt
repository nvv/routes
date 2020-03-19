package com.pathfinder.engine.search

import com.pathfinder.engine.search.model.Edge
import com.pathfinder.engine.search.model.Graph
import com.pathfinder.engine.search.model.Vertex
import java.util.*

/**
 * @author Vlad Namashko
 */
class PathFinder<T, E>(private val graph: Graph<T, E>) {

    private val onPath = mutableMapOf<Vertex<T>, Boolean>().apply {
        graph.vertices.forEach { item -> put(item.value, false) }
    }

    private var numberOfPaths: Int = 0

    fun search(from: T, to: T): List<List<Edge<T, E>>> {
        val pathList = mutableListOf<List<Edge<T, E>>>()

        search(from, from, to, null, pathList,  null)

        return pathList
    }

    // use DFS
    private fun search(
            from: T,
            to: T,
            destination: T,
            currentPath: MutableList<Edge<T, E>>?,
            pathList: MutableList<List<Edge<T, E>>>,
            edgeWeight: E? = null) {

        edgeWeight?.let {
            currentPath?.add(Edge(graph.getVertex(from), graph.getVertex(to), it))
        }

        val vertex = graph.getVertex(to)
        onPath[vertex] = true

        if (to == destination) {
            pathList.add(LinkedList(currentPath))
            numberOfPaths++
        } else {
            graph.getEdges(to).filter { edge -> onPath[edge.to]?.not() == true }.forEach { edge ->
                search(
                        to,
                        edge.to.vertex,
                        destination,
                        currentPath ?: mutableListOf(),
                        pathList,
                        edge.weight
                )
                currentPath?.removeAt(currentPath.size - 1)
            }
        }


        onPath[vertex] = false
    }

}