package com.routes.pathfinder.model

import java.util.*

/**
 * @author Vlad Namashko
 */
data class Itinerary<T>(val routes: List<ItineraryRoute<T>>)

data class ItineraryRoute<T>(
        val src: Destination<T>,
        val dst: Destination<T>,
        val cost: Double,
        val departure: Date,
        val arrival: Date
)