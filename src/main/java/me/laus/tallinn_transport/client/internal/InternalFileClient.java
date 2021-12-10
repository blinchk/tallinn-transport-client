package me.laus.tallinn_transport.client.internal;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class InternalFileClient {
    public FileReader readFile(String path) {
        try {
            return new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
