package me.laus.tallinn.transport.exception;

import me.laus.tallinn.transport.model.request.HttpMethod;

public class RequestBodyRequiredException extends RuntimeException {
    public RequestBodyRequiredException(HttpMethod method) {
        super(String.format("HTTP method %s cannot be requested without body ", method));
    }
}
