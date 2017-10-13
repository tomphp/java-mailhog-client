package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.api.MessageListFetcher;

public final class GetNumberOfMessagesSentTo {

    private final MessageListFetcher fetcher;

    public GetNumberOfMessagesSentTo(final MessageListFetcher fetcher) {
        this.fetcher = fetcher;
    }

    public int execute(final String recipientEmail) {
        return fetcher.fetchFrom("/api/v2/messages?kind=to&query=" + recipientEmail).getCount();
    }
}
