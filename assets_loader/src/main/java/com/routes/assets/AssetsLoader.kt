package com.routes.assets

import com.routes.pathfinder.model.Destination
import com.routes.pathfinder.model.Route
import com.routes.utils.doubleLet
import java.io.File
import java.text.SimpleDateFormat

/**
 * @author Vlad Namashko
 */
class AssetsLoader {

    fun getFileFromResources(fileName: String): File =
            javaClass.classLoader.getResource(fileName)?.let {
                File(it.file)
            } ?: throw IllegalArgumentException("File $fileName is not found!")

}