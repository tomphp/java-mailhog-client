package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.HTTPClient;
import io.tomoram.mailhog_client.exceptions.RequestFailed;

public final class DeleteAllMessages {

    private final HTTPClient http;

    public DeleteAllMessages(final HTTPClient http) {
        this.http = http;
    }

    public void execute() throws RequestFailed {
        http.delete("/api/v1/messages");
    }
}
