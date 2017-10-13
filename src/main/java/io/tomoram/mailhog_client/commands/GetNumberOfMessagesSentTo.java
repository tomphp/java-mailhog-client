package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.api.MessageListFetcher;
import io.tomoram.mailhog_client.exceptions.InvalidResponse;

public final class GetNumberOfMessagesSentTo {

    private final MessageListFetcher fetcher;

    public GetNumberOfMessagesSentTo(final MessageListFetcher fetcher) {
        this.fetcher = fetcher;
    }

    public int execute(final String recipientEmail) throws InvalidResponse {
        return fetcher.fetchFrom("/api/v2/messages?kind=to&query=" + recipientEmail).getCount();
    }
}
