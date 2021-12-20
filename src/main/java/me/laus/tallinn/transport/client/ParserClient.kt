package me.laus.tallinn.transport.client;

import java.util.ArrayList;

public interface ParserClient<T> {
    ArrayList<T> parse();
}
