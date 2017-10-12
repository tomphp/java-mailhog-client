package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.HTTPClient;

public final class DeleteAllMessages {

    private final HTTPClient http;

    public DeleteAllMessages(HTTPClient http) {
        this.http = http;
    }

    public void execute() {
        http.delete("/api/v1/messages");
    }
}
