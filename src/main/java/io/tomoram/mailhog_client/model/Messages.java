package io.tomoram.mailhog_client.model;

import java.util.List;
import java.util.stream.Stream;

public final class Messages {
    private final int count;
    private final List<Message> messages;

    public Messages(final int count, final List<Message> messages) {
        this.count = count;
        this.messages = messages;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Messages messages1 = (Messages) o;

        return messages != null ? messages.equals(messages1.messages) : messages1.messages == null;
    }

    @Override
    public int hashCode() {
        return messages != null ? messages.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "messages=" + messages +
                '}';
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
