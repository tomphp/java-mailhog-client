package io.tomoram.mailhog_client.commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.tomoram.mailhog_client.HTTPClient;
import io.tomoram.mailhog_client.json.MessagesDeserializer;
import io.tomoram.mailhog_client.model.Message;
import io.tomoram.mailhog_client.model.Messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public final class GetAllMessages {
    private final HTTPClient http;

    public GetAllMessages(HTTPClient http) {
        this.http = http;
    }

    public Stream<Message> execute() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Messages.class, new MessagesDeserializer());
        mapper.registerModule(module);

        Messages messages = null;

        try {
            messages = mapper.readValue(http.get("/api/v2/messages"), Messages.class);
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }

        return messages.stream();
    }
}
