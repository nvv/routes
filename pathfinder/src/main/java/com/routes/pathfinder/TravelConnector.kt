package com.routes.pathfinder

import com.routes.graph.PathFinder
import com.routes.graph.model.Graph
import com.routes.pathfinder.model.*
import com.routes.pathfinder.scheduler.ItineraryScheduler
import com.routes.utils.doubleLet
import java.lang.RuntimeException

/**
 * @author Vlad Namashko
 */
class TravelConnector<T> {

    private val graph = Graph<T, RouteInfo>()

    private val destinations = mutableMapOf<T, Destination<T>>()

    private val scheduler = ItineraryScheduler<T>()

    fun putEdge(route: Route<T>) {
        graph.putEdge(route.src.id, route.dst.id, RouteInfo(route.cost, route.departure, route.arrival))

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
                pathList = filterItinerary(pathList.map { path ->
                    Itinerary(path.mapNotNull { node ->
                        val fromDestination = destinations[node.from.vertex]
                        val toDestination = destinations[node.to.vertex]
                        doubleLet(fromDestination, toDestination) { from, to ->
                            ItineraryRoute(from, to, node.weight.cost, node.weight.departure, node.weight.arrival)
                        }
                    })
                })
        )
    }

    private fun filterItinerary(pathList: List<Itinerary<T>>): List<Itinerary<T>> {
        return pathList.filter { scheduler.isPathAcceptable(it) }
    }
}