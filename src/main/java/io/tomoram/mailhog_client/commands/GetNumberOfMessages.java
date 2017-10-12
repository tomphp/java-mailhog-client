package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.HTTPClient;
import io.tomoram.mailhog_client.api.v2.MessageListFetcher;

public final class GetNumberOfMessages {

    private final HTTPClient http;

    public GetNumberOfMessages(HTTPClient http) {
        this.http = http;
    }

    public int execute() {
        return new MessageListFetcher(http).fetchFrom("/api/v2/messages").getCount();
    }

}
