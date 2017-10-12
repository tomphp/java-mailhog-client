package io.tomoram.mailhog_client.exceptions;

import java.io.IOException;

public final class RequestFailed extends IOException implements MailHogClientException {
    private RequestFailed(String message) {
        super(message);
    }

    private RequestFailed(String message, Throwable cause) {
        super(message, cause);
    }

    public static RequestFailed withHttpLibraryError(String method, String url, Throwable cause) {
        return new RequestFailed("There was an error when making a " + method + " request to " + url + ".", cause);
    }

    public static RequestFailed withErrorStatusCode(int statusCode, String method, String url) {
        return new RequestFailed(method + " request to " + url + " returned a " + statusCode + " status code.");
    }
}
