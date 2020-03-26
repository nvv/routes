package com.routes.assets.mapper

import com.routes.pathfinder.model.Destination
import com.routes.pathfinder.model.Route
import com.routes.utils.Mapper
import com.routes.utils.doubleLet
import java.text.SimpleDateFormat

/**
 * @author Vlad Namashko
 */
class StringToRoutesMapper(
        private val destinations: Map<Int, Destination<Int>>,
        private val format: SimpleDateFormat = SimpleDateFormat("HH:mmZ")
) : Mapper<String, Route<Int>?> {

    override fun map(item: String): Route<Int>? =
            item.split(",")
                    .run {
                        doubleLet(destinations[get(0).trim().toInt()], destinations[get(1).trim().toInt()]) { from, to ->
                            Route(from, to, get(2).toDouble(), format.parse(get(3).trim()), format.parse(get(4).trim()))
                        }
                    }


}