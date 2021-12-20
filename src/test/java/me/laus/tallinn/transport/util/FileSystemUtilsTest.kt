package me.laus.tallinn.transport.util

import org.junit.jupiter.api.Assertions
import me.laus.tallinn.transport.util.FileSystemUtils
import org.junit.jupiter.api.Test

internal class FileSystemUtilsTest {
    @Test
    fun stopFileIsNotNull() {
        Assertions.assertNotNull(FileSystemUtils.DEFAULT_STOPS_PATH)
    }
}