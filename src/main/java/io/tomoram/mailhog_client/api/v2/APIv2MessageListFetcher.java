package io.tomoram.mailhog_client.api.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.tomoram.mailhog_client.HTTPClient;
import io.tomoram.mailhog_client.exceptions.InvalidResponse;
import io.tomoram.mailhog_client.exceptions.RequestFailed;
import io.tomoram.mailhog_client.model.Messages;

import java.io.IOException;

public final class APIv2MessageListFetcher implements io.tomoram.mailhog_client.api.MessageListFetcher {
    private HTTPClient http;

    public APIv2MessageListFetcher(final HTTPClient http) {
        this.http = http;
    }

    @Override
    public Messages fetchFrom(final String url) throws InvalidResponse, RequestFailed {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Messages.class, new MessagesDeserializer());
        mapper.registerModule(module);

        Messages messages = null;

        try {
            messages = mapper.readValue(http.get(url), Messages.class);
        } catch (RequestFailed e) {
            throw e;
        } catch (IOException e) {
            throw InvalidResponse.forJSONParsingError("/url", e);
        }

        return messages;
    }
}
