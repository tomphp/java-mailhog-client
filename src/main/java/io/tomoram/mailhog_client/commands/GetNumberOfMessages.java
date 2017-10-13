package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.api.MessageListFetcher;

public final class GetNumberOfMessages {

    private final MessageListFetcher messageListFetcher;

    public GetNumberOfMessages(final MessageListFetcher messageListFetcher) {
        this.messageListFetcher = messageListFetcher;
    }

    public int execute() {
        return messageListFetcher.fetchFrom("/api/v2/messages").getCount();
    }

}
