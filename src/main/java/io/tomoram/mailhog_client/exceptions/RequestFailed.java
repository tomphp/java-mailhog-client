package io.tomoram.mailhog_client.exceptions;

import java.io.IOException;

public final class RequestFailed extends IOException implements MailHogClientException {
    private RequestFailed(final String message) {
        super(message);
    }

    private RequestFailed(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static RequestFailed withHttpLibraryError(final String method, final String url, final Throwable cause) {
        return new RequestFailed("There was an error when making a " + method + " request to " + url + ".", cause);
    }

    public static RequestFailed withErrorStatusCode(final int statusCode, final String method, final String url) {
        return new RequestFailed(method + " request to " + url + " returned a " + statusCode + " status code.");
    }
}
