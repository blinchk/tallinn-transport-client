package me.laus.tallinn.transport.client.internal;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class InternalFileReaderClient {
    public FileReader readFile(String path) {
        try {
            return new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
