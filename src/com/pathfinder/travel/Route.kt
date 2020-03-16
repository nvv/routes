package com.pathfinder.travel

/**
 * @author Vlad Namashko
 */
data class Route<T>(
        val src: Destination<T>,
        val dst: Destination<T>,
        val cost: Double
)