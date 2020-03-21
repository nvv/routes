package com.pathfinder.travel.model

import java.util.*

/**
 * @author Vlad Namashko
 */
data class Route<T>(
        val src: Destination<T>,
        val dst: Destination<T>,
        val cost: Double,
        val departure: Date? = null,
        val arrival: Date? = null
)