package com.pathfinder.travel

import com.pathfinder.travel.model.Destination
import com.pathfinder.travel.model.Itinerary

/**
 * @author Vlad Namashko
 */
class PathPrinter<T> {

    fun print(from: Destination<T>, to: Destination<T>, pathList: List<Itinerary<T>>) {
        println("${from.name} -> ${to.name}: ")
        pathList.forEach { printCurrentPath(it) }
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