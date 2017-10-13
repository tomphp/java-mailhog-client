package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.api.MessageListFetcher;
import io.tomoram.mailhog_client.exceptions.InvalidResponse;
import io.tomoram.mailhog_client.exceptions.RequestFailed;

public final class GetNumberOfMessages {

    private final MessageListFetcher messageListFetcher;

    public GetNumberOfMessages(final MessageListFetcher messageListFetcher) {
        this.messageListFetcher = messageListFetcher;
    }

    public int execute() throws InvalidResponse, RequestFailed {
        return messageListFetcher.fetchFrom("/api/v2/messages").getCount();
    }
}
