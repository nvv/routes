package com.pathfinder.travel

import com.pathfinder.utils.doubleLet
import com.pathfinder.engine.search.PathFinder
import com.pathfinder.engine.search.model.Graph
import com.pathfinder.travel.model.*

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

    fun search(from: T, to: T): List<Itinerary<T>> {
        val pathFinder = PathFinder(graph)
        val pathList = pathFinder.search(from, to)

        return pathList.map { path ->
            Itinerary(path.mapNotNull { node ->
                doubleLet(destinations[node.from.vertex], destinations[node.to.vertex], { from, to ->
                    ItineraryRoute(from, to, node.weight.cost)
                })
            })
        }
    }
}