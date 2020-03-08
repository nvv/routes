package pathfinder

import pathfinder.model.Graph
import pathfinder.model.Vertex
import java.util.*

/**
 * @author Vlad Namashko
 */
class PathFinder<T>(private val graph: Graph<T>) {

    private val onPath: MutableMap<Vertex<T>, Boolean> = mutableMapOf<Vertex<T>, Boolean>().apply {
        graph.vertices.forEach { item -> put(item.value, false) }
    }

    private val path = LinkedList<T>()
    private var numberOfPaths: Int = 0

    // use DFS
    fun search(from: T, to: T, pathList: MutableList<LinkedList<T>>) {
        path.push(from)
        val vertex = graph.getVertex(from)
        onPath[vertex] = true

        if (from == to) {
            pathList.add(LinkedList(path))
            numberOfPaths++
        } else {
            graph.getEdges(from).filter { edge -> onPath[edge]?.not() == true }.forEach { edge -> search(edge.vertex, to, pathList) }
        }

        path.pop()
        onPath[vertex] = false
    }

}