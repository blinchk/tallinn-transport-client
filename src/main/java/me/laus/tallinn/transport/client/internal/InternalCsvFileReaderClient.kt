package me.laus.tallinn.transport.client.internal

import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import com.opencsv.exceptions.CsvException
import java.io.FileReader
import java.io.IOException
import java.util.*

abstract class InternalCsvFileReaderClient(path: String) : InternalFileReaderClient() {
    private val fileReader: FileReader

    init {
        fileReader = initializeFileReader(path)
    }

    fun readCsv(): List<Array<String>>? {
        val csvParser = CSVParserBuilder().withSeparator(';').build()
        val csvReader = CSVReaderBuilder(fileReader).withCSVParser(csvParser).withSkipLines(1).build()
        try {
            return csvReader.readAll()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: CsvException) {
            e.printStackTrace()
        }
        return null
    }

    fun validateLength(result: List<Array<String>>, atLeastLength: Int): List<Array<String>> {
        return Objects.requireNonNull(result).stream().takeWhile { row: Array<String> -> row.size > atLeastLength }
            .toList()
    }
}