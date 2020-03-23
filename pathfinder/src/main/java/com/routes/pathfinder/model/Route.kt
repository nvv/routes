package com.routes.pathfinder.model

import java.util.*

/**
 * @author Vlad Namashko
 */
data class Route<T>(
        val src: Destination<T>,
        val dst: Destination<T>,
        val cost: Double,
        val departure: Date,
        val arrival: Date
)