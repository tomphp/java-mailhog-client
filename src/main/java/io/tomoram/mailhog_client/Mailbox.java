package io.tomoram.mailhog_client;

import io.tomoram.mailhog_client.clients.OkHTTPClient;
import io.tomoram.mailhog_client.commands.GetNumberOfMessages;

public final class Mailbox {

    private final HTTPClient client;

    public Mailbox(final String apiUrl) {
        client = new OkHTTPClient(apiUrl);
    }

    public Mailbox(final HTTPClient client) {
        this.client = client;
    }

    public int getNumberOfMessages() {
        return new GetNumberOfMessages(client).execute();
    }
}
