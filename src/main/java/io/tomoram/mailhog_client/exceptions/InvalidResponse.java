package io.tomoram.mailhog_client.exceptions;

import java.io.IOException;

public class InvalidResponse extends IOException implements MailHogClientException {
    public InvalidResponse(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static InvalidResponse forJSONParsingError(final String url, final Throwable cause) {
        return new InvalidResponse("There was an error when parsing the JSON response from " + url +".", cause);
    }
}
