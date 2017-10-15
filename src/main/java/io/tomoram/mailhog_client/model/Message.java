package io.tomoram.mailhog_client.model;

import java.util.ArrayList;
import java.util.Arrays;

public final class Message {
    private final String sender;
    private final String[] recipients;
    public Message(final String sender, final String[] recipients) {
        this.sender = sender;
        this.recipients = recipients;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (!sender.equals(message.sender)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(recipients, message.recipients);
    }

    @Override
    public int hashCode() {
        final int primeNumber = 31;
        int result = sender.hashCode();

        result = primeNumber * result + Arrays.hashCode(recipients);
        return result;
    }


    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", recipients=" + Arrays.toString(recipients) +
                '}';
    }

    public static final class Builder {
        private String sender;
        private ArrayList<String> recipients = new ArrayList<>();

        public Builder addRecipient(final String recipient) {
            recipients.add(recipient);
            return this;
        }

        public Builder setSender(final String sender) {
            this.sender = sender;
            return this;
        }

        public Message build() {
            return new Message(sender, recipients.toArray(new String[0]));
        }
    }
}
