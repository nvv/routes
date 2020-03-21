package com.pathfinder.travel

import com.pathfinder.travel.model.Destination
import com.pathfinder.travel.model.Itinerary
import com.pathfinder.travel.model.TravelInfo

/**
 * @author Vlad Namashko
 */
class PathPrinter<T> {

    fun print(travelInfo: TravelInfo<T>) {
        println("${travelInfo.from.name} -> ${travelInfo.to.name}: ")
        travelInfo.pathList.forEach { printCurrentPath(it) }
    }

    private fun printCurrentPath(path: Itinerary<T>) {
        val totalPrice = path.routes.sumByDouble { it.cost }

        path.routes.forEachIndexed { index, item ->
            if (index == 0) {
                print("${printDestination(item.src)} -> ${printDestination(item.dst)}")
            } else {
                print(" -> ${printDestination(item.dst)}")
            }
        }
        print(": $totalPrice")
        println()
    }

    private fun printDestination(dst: Destination<T>) = "${dst.name} (${dst.id})"
}