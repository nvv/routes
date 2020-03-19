package com.pathfinder.travel

import com.pathfinder.engine.search.PathFinder
import com.pathfinder.engine.search.model.Graph
import com.pathfinder.travel.model.Destination
import com.pathfinder.travel.model.EdgeInfo
import com.pathfinder.travel.model.Route
import java.util.*

/**
 * @author Vlad Namashko
 */
class TravelConnector<T> {

    private val graph = Graph<T, EdgeInfo>()

    private val destinations = mutableMapOf<T, Destination<T>>()

    fun putEdge(route: Route<T>) {
        graph.putEdge(route.src.id, route.dst.id, EdgeInfo(route.cost))

        destinations[route.src.id] = route.src
        destinations[route.dst.id] = route.dst
    }

    fun search(from: T, to: T): List<LinkedList<Pair<Destination<T>, EdgeInfo?>>> {
        val pathFinder = PathFinder(graph)
        val pathList = pathFinder.search(from, to)
        val result = mutableListOf<LinkedList<Pair<Destination<T>, EdgeInfo?>>>()
        pathList.forEach { path ->
//            val pathList = LinkedList<Pair<Destination<T>, EdgeInfo?>>()
//            path.forEach { item -> pathList.add(Pair(destinations[item.from] ?: destinations.values.first(), item.second)) }
//            result.add(pathList)
        }

        return result
    }
}