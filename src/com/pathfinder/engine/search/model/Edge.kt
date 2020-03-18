package com.pathfinder.engine.search.model

/**
 * @author Vlad Namashko
 */
data class Edge<T, E>(
        val from: Vertex<T>,
        val to: Vertex<T>,
        val weight: E
)