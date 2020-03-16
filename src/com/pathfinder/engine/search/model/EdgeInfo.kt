package com.pathfinder.engine.search.model

/**
 * @author Vlad Namashko
 */
data class EdgeInfo<T, E>(val toVertex: Vertex<T>, val edgeParams: E)