import pathfinder.PathFinder
import pathfinder.model.Graph
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

class PathPrinter<T, E>() {

    private val graph = Graph<T>()

    private val destinations = mutableMapOf<T, Destination<T>>()

    fun putEdge(route: Route<T, E>) {
        graph.putEdge(route.src.id, route.dst.id)

        destinations[route.src.id] = route.src
        destinations[route.dst.id] = route.dst
    }

    fun search(from: T, to: T) {
        val pathFinder = PathFinder(graph)
        val pathList = mutableListOf<LinkedList<T>>()
        pathFinder.search(from, to, pathList)
        pathList.forEach { printCurrentPath(it) }
    }

    private fun printCurrentPath(path: LinkedList<T>) {
        if (path.size >= 1) {
            val id = path.pollLast()
            print("${destinations[id]?.name} ($id)")
        }
        while (!path.isEmpty()) {
            val id = path.pollLast()
            print(" -> ${destinations[id]?.name} ($id)")
        }

        println()
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

    val printer = PathPrinter<Int, Int>()
    routes.forEach { printer.putEdge(it) }

    printer.search(0, 2)

}
