package com.routes.utils

/**
 * @author Vlad Namashko
 */
interface Mapper<T, K> {

    fun map(item: T): K

}