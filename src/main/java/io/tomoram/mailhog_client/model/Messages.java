package io.tomoram.mailhog_client.model;

import java.util.List;
import java.util.stream.Stream;

public final class Messages {
    private final int count;
    private final List<Message> messages;

    public Messages(int count, List<Message> messages) {
        this.count = count;
        this.messages = messages;
    }

    public int getCount() {
        return count;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Stream<Message> stream() {
        return messages.stream();
    }
}
