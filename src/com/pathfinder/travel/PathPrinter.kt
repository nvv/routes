package com.pathfinder.travel

import com.pathfinder.travel.model.Destination
import com.pathfinder.travel.model.EdgeInfo
import java.util.*

/**
 * @author Vlad Namashko
 */
class PathPrinter<T> {

    fun print(from: Destination<T>, to: Destination<T>, pathList: List<LinkedList<Pair<Destination<T>, EdgeInfo?>>>) {
        println("${from?.name} -> ${to?.name}: ")
        pathList.forEach { printCurrentPath(it) }
    }

    private fun printCurrentPath(path: LinkedList<Pair<Destination<T>, EdgeInfo?>>) {
        var totalPrice = 0.0
        if (path.size >= 1) {
            val id = path.pollLast()
            print("${id.first?.name} (${id.first.id})")
        }
        while (!path.isEmpty()) {
            val id = path.pollLast()
            val destination = id.first
            print(" -> ${destination?.name} (${id.first.id})")
            id.second?.let {
                totalPrice += it.cost
            }
        }
        print(": $totalPrice")
        println()
    }

}