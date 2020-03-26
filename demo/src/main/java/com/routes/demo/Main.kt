package com.routes.demo

import com.routes.assets.RoutesLoader
import com.routes.pathfinder.PathPrinter
import com.routes.pathfinder.TravelConnector

fun main() {

    val connector = TravelConnector<Int>()
    RoutesLoader().load().forEach { connector.putEdge(it) }

    val printer = PathPrinter<Int>()

    printer.print(connector.search(0, 4))

    printer.print(connector.search(4, 0))

}
