package me.laus.tallinn.transport.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemUtilsTest {
    @Test
    void stopFileIsNotNull() {
        assertNotNull(FileSystemUtils.DEFAULT_STOPS_PATH);
    }
}