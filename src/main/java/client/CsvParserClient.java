package client;

import java.util.ArrayList;

public interface CsvParserClient<T> {
    ArrayList<T> parse();
}
