package com.pathfinder.travel.model

/**
 * @author Vlad Namashko
 */
data class TravelInfo<T>(
        val from: Destination<T>,
        val to: Destination<T>,
        val pathList: List<Itinerary<T>>
)