package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.api.MessageListFetcher;
import io.tomoram.mailhog_client.model.Message;

import java.util.stream.Stream;

public final class GetAllMessages {

    private final MessageListFetcher messageListFetcher;

    public GetAllMessages(final MessageListFetcher messageListFetcher) {
        this.messageListFetcher = messageListFetcher;
    }

    public Stream<Message> execute() {
        return messageListFetcher.fetchFrom("/api/v2/messages").stream();
    }
}
