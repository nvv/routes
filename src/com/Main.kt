package com

import com.pathfinder.travel.PathPrinter
import com.pathfinder.travel.TravelConnector
import com.pathfinder.travel.model.Destination
import com.pathfinder.travel.model.Route
import com.pathfinder.utils.doubleLet
import java.io.File
import java.text.SimpleDateFormat

fun main() {

    val destinations = mutableMapOf<Int, Destination<Int>>()
    File("assets/destinations.txt").forEachLine { line ->
        line.split(",")
                .run {
                    val id = get(0).toInt()
                    destinations[id] = Destination(id, get(1).trim())
                }
    }

    val format = SimpleDateFormat("hh:mmZ")
    val routes = mutableListOf<Route<Int>>()
    File("assets/routes.txt")
            .forEachLine { line ->
                line.split(",")
                        .run {
                            doubleLet(destinations[get(0).trim().toInt()], destinations[get(1).trim().toInt()]) { from, to ->
                                routes.add(Route(from, to, get(2).toDouble(), format.parse(get(3).trim()), format.parse(get(4).trim())))
                            }
                        }
            }

    val connector = TravelConnector<Int>()
    routes.forEach { connector.putEdge(it) }

    val printer = PathPrinter<Int>()

    printer.print(connector.search(0, 4))

    printer.print(connector.search(4, 0))

}
