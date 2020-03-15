import pathfinder.PathFinder
import pathfinder.model.Graph
import java.util.*

data class Destination<T>(
        val id: T,
        val name: String
)

data class Route<T>(
        val src: Destination<T>,
        val dst: Destination<T>,
        val cost: Double
)

data class EdgeInfo(val cost: Double)

class PathPrinter<T> {

    private val graph = Graph<T, EdgeInfo>()

    private val destinations = mutableMapOf<T, Destination<T>>()

    fun putEdge(route: Route<T>) {
        graph.putEdge(route.src.id, route.dst.id, EdgeInfo(route.cost))

        destinations[route.src.id] = route.src
        destinations[route.dst.id] = route.dst
    }

    fun search(from: T, to: T) {
        val pathFinder = PathFinder(graph)
        val pathList = mutableListOf<LinkedList<Pair<T, EdgeInfo?>>>()
        pathFinder.search(from, to, pathList)
        print(from, to, pathList)
    }

    private fun print(from: T, to: T, pathList: List<LinkedList<Pair<T, EdgeInfo?>>>) {
        println("${destinations[from]?.name} -> ${destinations[to]?.name}: ")
        pathList.forEach { printCurrentPath(it) }
    }

    private fun printCurrentPath(path: LinkedList<Pair<T, EdgeInfo?>>) {
        var totalPrice = 0.0
        if (path.size >= 1) {
            val id = path.pollLast()
            print("${destinations[id.first]?.name} (${id.first})")
        }
        while (!path.isEmpty()) {
            val id = path.pollLast()
            val destination = destinations[id.first]
            print(" -> ${destination?.name} (${id.first})")
            id.second?.let {
                totalPrice += it.cost
            }
        }
        print(": $totalPrice")
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
            Route(dst0, dst1, 100.0),
            Route(dst0, dst2, 150.0),
            Route(dst1, dst3, 220.0),
            Route(dst2, dst3, 240.0),
            Route(dst1, dst2, 88.0),
            Route(dst3, dst4, 210.0),
            Route(dst4, dst5, 110.0),
            Route(dst5, dst1, 300.0),
            Route(dst0, dst4, 350.0),
            Route(dst5, dst3, 270.0),
            Route(dst5, dst0, 430.0),
            Route(dst4, dst0, 360.0),
            Route(dst5, dst4, 100.0)
    )

    val printer = PathPrinter<Int>()
    routes.forEach { printer.putEdge(it) }

    printer.search(0, 4)

    printer.search(4, 0)

}
