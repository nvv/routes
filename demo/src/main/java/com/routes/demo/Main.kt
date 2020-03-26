package com.routes.demo

import com.routes.assets.AssetsLoader
import com.routes.assets.RoutesLoader
import com.routes.pathfinder.PathPrinter
import com.routes.pathfinder.TravelConnector
import com.routes.pathfinder.model.Destination
import com.routes.pathfinder.model.Route
import com.routes.utils.doubleLet
import java.io.File
import java.text.SimpleDateFormat

fun main() {

    val connector = TravelConnector<Int>()
    RoutesLoader(AssetsLoader()).load().forEach { connector.putEdge(it) }

    val printer = PathPrinter<Int>()

    printer.print(connector.search(0, 4))

    printer.print(connector.search(4, 0))

}
