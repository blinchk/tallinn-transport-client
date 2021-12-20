package me.laus.tallinn.transport.exception

import me.laus.tallinn.transport.model.request.HttpMethod

class RequestBodyRequiredException(method: HttpMethod?) :
    RuntimeException(String.format("HTTP method %s cannot be requested without body ", method))