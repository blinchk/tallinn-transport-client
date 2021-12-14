package me.laus.tallinn.transport.client.internal;

import java.util.ArrayList;

public interface CsvParserClient<T> {
    ArrayList<T> parse();
}
