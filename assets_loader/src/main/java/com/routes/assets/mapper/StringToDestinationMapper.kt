package com.routes.assets.mapper

import com.routes.pathfinder.model.Destination
import com.routes.utils.Mapper

/**
 * @author Vlad Namashko
 */
class StringToDestinationMapper : Mapper<String, Destination<Int>> {

    override fun map(item: String): Destination<Int> {
        return item.split(",")
                .run {
                    Destination(get(0).toInt(), get(1).trim())
                }
    }

}