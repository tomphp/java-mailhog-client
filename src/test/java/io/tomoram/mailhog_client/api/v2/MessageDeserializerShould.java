package io.tomoram.mailhog_client.api.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.tomoram.mailhog_client.api.v2.MessageDeserializer;
import io.tomoram.mailhog_client.helpers.JSON;
import io.tomoram.mailhog_client.model.Message;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageDeserializerShould {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();

    @Before
    public void
    register_the_deserializer() {
        module.addDeserializer(Message.class, new MessageDeserializer());
        mapper.registerModule(module);
    }

    @Test
    public void
    deserialize_a_message() throws IOException {
        String json = JSON.singleMessage();

        Message expected = Message.builder()
                .setSender("tomoram@tom-mac")
                .addRecipient("george@a-happy-place.com")
                .build();

        assertThat(mapper.readValue(json, Message.class)).isEqualTo(expected);
    }
}
