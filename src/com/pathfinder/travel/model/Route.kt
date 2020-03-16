package com.pathfinder.travel.model

/**
 * @author Vlad Namashko
 */
data class Route<T>(
        val src: Destination<T>,
        val dst: Destination<T>,
        val cost: Double
)