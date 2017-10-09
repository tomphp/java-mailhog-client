package io.tomoram.mailhog_client.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sun.xml.internal.bind.v2.model.core.TypeRef;
import io.tomoram.mailhog_client.model.Message;
import io.tomoram.mailhog_client.model.Messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessagesDeserializer extends StdDeserializer<Messages> {
    public MessagesDeserializer() {
        this(null);
    }

    public MessagesDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Messages deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode collectionNode = jsonParser.getCodec().readTree(jsonParser);

        ObjectMapper messageMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Message.class, new MessageDeserializer());
        messageMapper.registerModule(module);

        List<Message> messages = messageMapper.readValue(
                collectionNode.get("items").toString(),
                new TypeReference<List<Message>>() {});

        return new Messages(collectionNode.get("total").asInt(), messages);
    }
}
