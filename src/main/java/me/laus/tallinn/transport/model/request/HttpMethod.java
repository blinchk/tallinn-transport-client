package me.laus.tallinn.transport.model.request;


/**
 * HTTP method to fetch data from External API using {@link ExternalApiRequest}.
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc7231#section-4">RFC 7231 Section 4</a>
 */
public enum HttpMethod {
    GET,
    HEAD,
    POST,
    DELETE,
    PUT,
    CONNECT,
    OPTIONS,
    PATCH
}
