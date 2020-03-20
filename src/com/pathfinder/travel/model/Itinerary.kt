package com.pathfinder.travel.model

/**
 * @author Vlad Namashko
 */
data class Itinerary<T>(val routes: List<ItineraryRoute<T>>)

data class ItineraryRoute<T>(
        val src: Destination<T>,
        val dst: Destination<T>,
        val cost: Double
)