package io.tomoram.mailhog_client.commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.tomoram.mailhog_client.HTTPClient;
import io.tomoram.mailhog_client.json.MessagesDeserializer;
import io.tomoram.mailhog_client.model.Messages;

import java.io.IOException;

public final class GetNumberOfMessages {

    private final HTTPClient http;

    public GetNumberOfMessages(HTTPClient http) {
        this.http = http;
    }

    public long execute() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Messages.class, new MessagesDeserializer());
        mapper.registerModule(module);

        JsonNode collection = null;

        try {
            collection = mapper.readTree(http.get("/api/v2/messages"));
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }

        return collection.get("total").asLong();
    }

}
