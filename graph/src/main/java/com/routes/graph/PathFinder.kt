package com.routes.graph

import com.routes.graph.model.Edge
import com.routes.graph.model.Graph
import com.routes.graph.model.Vertex
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

        search(pathList, from, from, to, null,  null)

        return pathList
    }

    // use DFS
    private fun search(
            pathList: MutableList<List<Edge<T, E>>>,
            from: T,
            to: T,
            destination: T,
            currentPath: MutableList<Edge<T, E>>?,
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
                        pathList,
                        to,
                        edge.to.vertex,
                        destination,
                        currentPath ?: mutableListOf(),
                        edge.weight
                )
                currentPath?.removeAt(currentPath.size - 1)
            }
        }


        onPath[vertex] = false
    }

}