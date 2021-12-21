package me.laus.tallinn.transport.client.internal

import java.io.FileNotFoundException
import java.io.FileReader

open class InternalFileReaderClient {
    fun initializeFileReader(path: String): FileReader {
        return FileReader(path)
    }
}