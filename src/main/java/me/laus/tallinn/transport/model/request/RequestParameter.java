package me.laus.tallinn.transport.model.request;

import org.apache.http.NameValuePair;

public class RequestParameter implements NameValuePair {
    private final String name;
    private final String value;

    public RequestParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }
}
