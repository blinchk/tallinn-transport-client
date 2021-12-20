package me.laus.tallinn.transport.model.request

/**
 * HTTP method to fetch data from External API using [ExternalApiRequest].
 * @see [RFC 7231 Section 4](https://datatracker.ietf.org/doc/html/rfc7231.section-4)
 */
enum class HttpMethod {
    GET, HEAD, POST, DELETE, PUT, CONNECT, OPTIONS, PATCH;

    val isBodyRequired: Boolean
        get() = this != GET && this != HEAD && this != OPTIONS
}