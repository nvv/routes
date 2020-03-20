package com.pathfinder.utils

/**
 * @author Vlad Namashko
 */
inline fun <T : Any, E : Any, R : Any> doubleLet(t: T?, e: E?, block: (T, E) -> R?): R? {
    return if (t != null && e != null) block(t, e) else null
}