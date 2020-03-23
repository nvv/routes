package com.routes.pathfinder.model

/**
 * @author Vlad Namashko
 */
data class TravelInfo<T>(
        val from: Destination<T>,
        val to: Destination<T>,
        val pathList: List<Itinerary<T>>
)