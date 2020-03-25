package com.routes.pathfinder.scheduler

import com.routes.pathfinder.model.Itinerary
import com.routes.pathfinder.model.ItineraryRoute

/**
 * @author Vlad Namashko
 */
class ItineraryScheduler<T> {

    fun isPathAcceptable(itinerary: Itinerary<T>): Boolean {
        var prevRoute: ItineraryRoute<T>? = null
        itinerary.routes.forEach {
            prevRoute?.let { prevRoute ->
                if (it.departure.time - prevRoute.arrival.time < 1800000) {
                    return false
                }
            }

            prevRoute = it
        }

        return true
    }

}
