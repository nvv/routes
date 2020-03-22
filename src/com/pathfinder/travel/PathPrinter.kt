package com.pathfinder.travel

import com.pathfinder.travel.model.Destination
import com.pathfinder.travel.model.Itinerary
import com.pathfinder.travel.model.TravelInfo
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Vlad Namashko
 */
class PathPrinter<T> {

    private val dateFormatter = SimpleDateFormat("HH:mm")

    fun print(travelInfo: TravelInfo<T>) {
        println("${travelInfo.from.name} -> ${travelInfo.to.name}: ")
        travelInfo.pathList.forEach { printCurrentPath(it) }
    }

    private fun printCurrentPath(path: Itinerary<T>) {
        val totalPrice = path.routes.sumByDouble { it.cost }

        path.routes.forEachIndexed { index, item ->
            if (index == 0) {
                print("${printDestination(item.src, departure = item.departure)} -> ${printDestination(item.dst, arrival = item.arrival)}")
            } else {
                print("(${dateFormatter.format(item.departure)}) -> ${printDestination(item.dst, arrival = item.arrival)}")
            }
        }
        print(": $totalPrice")
        println()
    }

    private fun printDestination(dst: Destination<T>, departure: Date? = null, arrival: Date? = null) =
            "${printDate(arrival)} ${dst.name} ${printDate(departure)}"

    private fun printDate(date: Date?) = if (date != null) "(${dateFormatter.format(date)})" else ""
}