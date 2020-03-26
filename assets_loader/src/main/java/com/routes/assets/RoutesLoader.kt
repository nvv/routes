package com.routes.assets

import com.routes.assets.mapper.StringToDestinationMapper
import com.routes.assets.mapper.StringToRoutesMapper
import com.routes.pathfinder.model.Destination
import com.routes.pathfinder.model.Route
import java.io.File

/**
 * @author Vlad Namashko
 */
class RoutesLoader() {

    private val destinations = mutableMapOf<Int, Destination<Int>>()

    private val destinationMapper: StringToDestinationMapper = StringToDestinationMapper()
    private val routeMapper: StringToRoutesMapper = StringToRoutesMapper(destinations)

    fun load(): List<Route<Int>> {

        File("assets/destinations.txt").forEachLine { line ->
            val destination = destinationMapper.map(line)
            destinations[destination.id] = destination
        }

        val routes = mutableListOf<Route<Int>>()
        File("assets/routes.txt")
                .forEachLine { line ->
                    routeMapper.map(line)?.let {
                        routes.add(it)
                    }
                }

        return routes
    }

}