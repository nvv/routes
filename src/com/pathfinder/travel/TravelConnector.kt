package com.pathfinder.travel

import com.pathfinder.utils.doubleLet
import com.pathfinder.engine.search.PathFinder
import com.pathfinder.engine.search.model.Graph
import com.pathfinder.travel.model.*
import java.lang.RuntimeException

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

    fun search(fromId: T, toId: T): TravelInfo<T> {
        val from = destinations[fromId]
        val to = destinations[toId]

        if (from == null || to == null) throw RuntimeException("No valid src or dst ids")

        val pathFinder = PathFinder(graph)
        val pathList = pathFinder.search(fromId, toId)

        return TravelInfo(
                from = from,
                to = to,
                pathList = pathList.map { path ->
                    Itinerary(path.mapNotNull { node ->
                        val fromDestination = destinations[node.from.vertex]
                        val toDestination = destinations[node.to.vertex]
                        doubleLet(fromDestination, toDestination) { from, to ->
                            ItineraryRoute(from, to, node.weight.cost)
                        }
                    })
                }
        )

    }
}