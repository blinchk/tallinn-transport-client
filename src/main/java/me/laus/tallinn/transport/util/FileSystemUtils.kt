package me.laus.tallinn.transport.util

import java.nio.file.Paths

object FileSystemUtils {
    private val CURRENT_PATH = Paths.get("").toAbsolutePath().toString()
    @kotlin.jvm.JvmField
    val DEFAULT_STOPS_PATH = CURRENT_PATH + "/src/main/resources/stops.txt"
}