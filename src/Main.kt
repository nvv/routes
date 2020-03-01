import java.util.*

data class Destination<T>(
        val id: T,
        val name: String
)

data class Route<T, E>(
        val src: Destination<T>,
        val dst: Destination<T>,
        val cost: E
)

class Vertex<T>(
        val vertex: T
)

class Graph<T, E>(
        val vertices: MutableMap<T, Vertex<T>> = mutableMapOf(),
        val destinations: MutableMap<T, Destination<T>> = mutableMapOf(),
        val edges: MutableMap<Vertex<T>, LinkedList<Vertex<T>>> = mutableMapOf()
) {
    private val NEWLINE = System.getProperty("line.separator")

    private var edgeCount = 0

    fun putEdge(route: Route<T, E>) {
        val vertex1 = getVertex(route.src.id)
        val vertex2 = getVertex(route.dst.id)

        var vertexEdges = edges[vertex1]
        if (vertexEdges == null) {
            vertexEdges = LinkedList()
            edges[vertex1] = vertexEdges
        }

        vertexEdges.add(vertex2)

        destinations[route.src.id] = route.src
        destinations[route.dst.id] = route.dst
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
        s.append("${vertices.size} vertices, $edgeCount edges $NEWLINE")
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
                val id = pollLast()
                print("${graph.destinations[id]?.name} ($id)")
            }
            while (!isEmpty()) {
                val id = pollLast()
                print(" -> ${graph.destinations[id]?.name} ($id)")
            }

            println()
        }
    }
}

fun main() {

    val dst0 = Destination(0, "Vancouver")
    val dst1 = Destination(1, "Calgary")
    val dst2 = Destination(2, "Edmonton")
    val dst3 = Destination(3, "Winnipeg")
    val dst4 = Destination(4, "Toronto")
    val dst5 = Destination(5, "Montreal")

    val routes = arrayOf(
            Route(dst0, dst1, 1),
            Route(dst0, dst2, 2),
            Route(dst1, dst3, 4),
            Route(dst2, dst3, 2),
            Route(dst1, dst2, 1),
            Route(dst3, dst4, 4),
            Route(dst4, dst5, 2),
            Route(dst5, dst1, 6),
            Route(dst0, dst4, 6),
            Route(dst5, dst3, 5)
    )

    val graph = Graph<Int, Int>()
    routes.forEach { graph.putEdge(it) }

    val allpaths = PathFinder(graph)
    allpaths.search(0, 2)

}
