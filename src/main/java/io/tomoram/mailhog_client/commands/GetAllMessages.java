package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.HTTPClient;
import io.tomoram.mailhog_client.api.v2.MessageListFetcher;
import io.tomoram.mailhog_client.model.Message;

import java.util.stream.Stream;

public final class GetAllMessages {
    private final HTTPClient http;

    public GetAllMessages(HTTPClient http) {
        this.http = http;
    }

    public Stream<Message> execute() {
        return new MessageListFetcher(http).fetchFrom("/api/v2/messages").stream();
    }
}
