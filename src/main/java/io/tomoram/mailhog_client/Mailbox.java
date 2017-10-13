package io.tomoram.mailhog_client;

import io.tomoram.mailhog_client.api.v2.APIv2MessageListFetcher;
import io.tomoram.mailhog_client.clients.OkHTTPClient;
import io.tomoram.mailhog_client.commands.DeleteAllMessages;
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
        return new GetNumberOfMessages(new APIv2MessageListFetcher(client)).execute();
    }

    public void empty() {
        new DeleteAllMessages(client).execute();
    }
}
