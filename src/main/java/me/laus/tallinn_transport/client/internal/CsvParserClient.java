package me.laus.tallinn_transport.client.internal;

import java.util.ArrayList;

public interface CsvParserClient<T> {
    ArrayList<T> parse();
}
