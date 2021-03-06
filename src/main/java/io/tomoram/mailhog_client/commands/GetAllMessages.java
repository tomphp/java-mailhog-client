package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.api.MessageListFetcher;
import io.tomoram.mailhog_client.exceptions.InvalidResponse;
import io.tomoram.mailhog_client.exceptions.RequestFailed;
import io.tomoram.mailhog_client.model.Message;

import java.util.stream.Stream;

public final class GetAllMessages {

    private final MessageListFetcher messageListFetcher;

    public GetAllMessages(final MessageListFetcher messageListFetcher) {
        this.messageListFetcher = messageListFetcher;
    }

    public Stream<Message> execute() throws InvalidResponse, RequestFailed {
        return messageListFetcher.fetchFrom("/api/v2/messages").stream();
    }
}
