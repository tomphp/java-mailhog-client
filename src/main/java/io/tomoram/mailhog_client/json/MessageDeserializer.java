package io.tomoram.mailhog_client.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.tomoram.mailhog_client.model.Message;

import java.io.IOException;

public final class MessageDeserializer extends StdDeserializer<Message> {
    public MessageDeserializer() {
        this(null);
    }

    public MessageDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Message deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {

        JsonNode messageNode = jsonParser.getCodec().readTree(jsonParser);

        Message.Builder builder = Message.builder();
        builder.setSender(address(messageNode.get("From")));
        messageNode.get("To").forEach(toNode -> builder.addRecipient(address(toNode)));

        return builder.build();
    }

    private String address(JsonNode toNode) {
        return toNode.get("Mailbox").asText() + "@" + toNode.get("Domain").asText();
    }

}
