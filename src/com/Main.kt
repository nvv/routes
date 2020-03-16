package com

import com.pathfinder.engine.search.PathFinder
import com.pathfinder.engine.search.model.Graph
import com.pathfinder.travel.PathPrinter
import com.pathfinder.travel.TravelConnector
import com.pathfinder.travel.model.Destination
import com.pathfinder.travel.model.EdgeInfo
import com.pathfinder.travel.model.Route
import java.util.*

fun main() {

    val dst0 = Destination(0, "Vancouver")
    val dst1 = Destination(1, "Calgary")
    val dst2 = Destination(2, "Edmonton")
    val dst3 = Destination(3, "Winnipeg")
    val dst4 = Destination(4, "Toronto")
    val dst5 = Destination(5, "Montreal")

    val routes = arrayOf(
            Route(dst0, dst1, 100.0),
            Route(dst0, dst2, 150.0),
            Route(dst1, dst3, 220.0),
            Route(dst2, dst3, 240.0),
            Route(dst1, dst2, 88.0),
            Route(dst3, dst4, 210.0),
            Route(dst4, dst5, 110.0),
            Route(dst5, dst1, 300.0),
            Route(dst0, dst4, 350.0),
            Route(dst5, dst3, 270.0),
            Route(dst5, dst0, 430.0),
            Route(dst4, dst0, 360.0),
            Route(dst5, dst4, 100.0)
    )

    val connector = TravelConnector<Int>()
    routes.forEach { connector.putEdge(it) }

    val printer = PathPrinter<Int>()

    printer.print(dst0, dst4, connector.search(0, 4))

    printer.print(dst4, dst0, connector.search(4, 0))

}
