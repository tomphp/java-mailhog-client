package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.api.MessageListFetcher;
import io.tomoram.mailhog_client.exceptions.InvalidResponse;
import io.tomoram.mailhog_client.exceptions.RequestFailed;

public final class GetNumberOfMessagesSentTo {

    private final MessageListFetcher fetcher;

    public GetNumberOfMessagesSentTo(final MessageListFetcher fetcher) {
        this.fetcher = fetcher;
    }

    public int execute(final String recipientEmail) throws InvalidResponse, RequestFailed {
        return fetcher.fetchFrom("/api/v2/messages?kind=to&query=" + recipientEmail).getCount();
    }
}
