package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.api.MessageListFetcher;
import io.tomoram.mailhog_client.exceptions.InvalidResponse;

public final class GetNumberOfMessages {

    private final MessageListFetcher messageListFetcher;

    public GetNumberOfMessages(final MessageListFetcher messageListFetcher) {
        this.messageListFetcher = messageListFetcher;
    }

    public int execute() throws InvalidResponse {
        return messageListFetcher.fetchFrom("/api/v2/messages").getCount();
    }
}
