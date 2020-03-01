import java.util.*

data class Route<T, E>(
        val src: T,
        val dst: T,
        val cost: E
)

class Vertex<T>(
        val vertex: T
)

class Graph<T, E>(
        val vertices: MutableMap<T, Vertex<T>> = mutableMapOf(),
        val edges: MutableMap<Vertex<T>, LinkedList<Vertex<T>>> = mutableMapOf()
) {

    private val NEWLINE = System.getProperty("line.separator")

    private var edgeCount = 0

    fun putEdge(route: Route<T, E>) {

        val vertex1 = getVertex(route.src)
        val vertex2 = getVertex(route.dst)

        var vertexEdges = edges[vertex1]
        if (vertexEdges == null) {
            vertexEdges = LinkedList()
            edges[vertex1] = vertexEdges
        }

        vertexEdges.add(vertex2)

        edgeCount++
    }

    fun getVertex(key: T) =
            if (vertices.containsKey(key)) {
                vertices[key] ?: Vertex(key)
            } else {
                val vertex = Vertex(key)
                vertices[key] = vertex
                vertex
            }

    fun getEdges(vertex: T): Iterable<Vertex<T>> {
        return edges[getVertex(vertex)] ?: emptyList()
    }

    override fun toString(): String {
        val s = StringBuilder()
        s.append("${vertices.size} vertices, ${edgeCount} edges $NEWLINE")
        vertices.forEach { v ->
            s.append("${v.value.vertex}: ")
            for (w in getEdges(v.value.vertex)) {
                s.append("${w.vertex} ")
            }
            s.append(NEWLINE)
        }

        return s.toString()
    }
}

class PathFinder<T, E>(private val graph: Graph<T, E>) {

    private val onPath: MutableMap<Vertex<T>, Boolean> = mutableMapOf<Vertex<T>, Boolean>().apply {
        graph.vertices.forEach { item -> put(item.value, false) }
    }

    private val path = LinkedList<T>()
    private var numberOfPaths: Int = 0

    // use DFS
    fun search(from: T, to: T) {
        path.push(from)
        val vertex = graph.getVertex(from)
        onPath[vertex] = true

        if (from == to) {
            printCurrentPath()
            numberOfPaths++
        } else {
            graph.getEdges(from).filter { edge -> onPath[edge]?.not() == true }.forEach { edge -> search(edge.vertex, to) }
        }

        path.pop()
        onPath[vertex] = false
    }

    private fun printCurrentPath() {
        LinkedList<T>(path).apply {
            if (size >= 1) {
                print(pollLast())
            }
            while (!isEmpty()) {
                print(" -> " + pollLast())
            }
            println()
        }
    }

}

fun main() {

    val routes = arrayOf(
            Route(0, 1, 1),
            Route(0, 2, 2),
            Route(1, 3, 4),
            Route(2, 3, 2),
            Route(1, 2, 1),
            Route(3, 4, 4),
            Route(4, 5, 2),
            Route(5, 1, 6),
            Route(0, 4, 6),
            Route(5, 3, 5)
    )

    val graph = Graph<Int, Int>()
    routes.forEach { graph.putEdge(it) }

    val allpaths = PathFinder(graph)
    allpaths.search(0, 2)

}
