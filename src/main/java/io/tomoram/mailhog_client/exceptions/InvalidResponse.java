package io.tomoram.mailhog_client.exceptions;

import java.io.IOException;

public class InvalidResponse extends Throwable {
    public static InvalidResponse forJSONParsingError(final String url, final Throwable cause) {
        return new InvalidResponse();
    }
}
