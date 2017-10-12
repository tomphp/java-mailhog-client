package io.tomoram.mailhog_client.api.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.tomoram.mailhog_client.HTTPClient;
import io.tomoram.mailhog_client.model.Messages;

import java.io.IOException;

public final class MessageListFetcher {
    private HTTPClient http;

    public MessageListFetcher(final HTTPClient http) {
        this.http = http;
    }

    public Messages fetchFrom(final String url) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Messages.class, new MessagesDeserializer());
        mapper.registerModule(module);

        Messages messages = null;

        try {
            messages = mapper.readValue(http.get(url), Messages.class);
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }

        return messages;
    }
}
