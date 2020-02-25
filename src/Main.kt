data class Route<T, E>(val src: T, val dst: T, val cost: E)

class Vertex<T>(val vertex: T, var visited: Boolean = false, val connections: MutableList<Vertex<T>> = mutableListOf())

class Edge<T, E>(val vertex1: Vertex<T>, val vertex2: Vertex<T>, val value: E) {
    fun hasVertex(vertex: Vertex<T>) = vertex == vertex1 || vertex == vertex2

    fun oppositeVertex(vertex: Vertex<T>) = if (vertex == vertex1) vertex2 else vertex1

}

class Graph<T, E>(
        val vertices: MutableMap<T, Vertex<T>> = mutableMapOf(),
        val edges: MutableList<Edge<T, E>> = mutableListOf()
) {

    fun putVertex(route: Route<T, E>) {

        val vertex1 = getVertex(route.src)
        val vertex2 = getVertex(route.dst)
        val value = route.cost

        vertex1.connections.add(vertex2)

        edges.add(Edge(vertex1, vertex2, value))
    }

    fun getVertex(key: T) =
            if (vertices.containsKey(key)) {
                vertices[key] ?: Vertex(key)
            } else {
                val vertex = Vertex(key)
                vertices[key] = vertex
                vertex
            }

    fun countPaths(start: Vertex<T>, destination: Vertex<T>) {
        val pathList = mutableListOf<MutableList<Vertex<T>>>()
        start.connections.filter { !it.visited }.forEach {
            it.visited = true
            val path = mutableListOf<Vertex<T>>()
            path.add(it)
            count(it, destination, path)?.let {
                pathList.add(path)
            }
        }
        println(pathList.size)
    }

    fun count(start: Vertex<T>, destination: Vertex<T>, path: MutableList<Vertex<T>>): MutableList<Vertex<T>>? {
        if (start != destination) {
            start.connections.filter { !it.visited }.forEach {
                it.visited = true
                path.add(it)
                count(it, destination, path)
            }
        }

        return if (start == destination) path else null
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
    routes.forEach { graph.putVertex(it) }

    graph.countPaths(graph.vertices[0]!!, graph.vertices[2]!!)
}
