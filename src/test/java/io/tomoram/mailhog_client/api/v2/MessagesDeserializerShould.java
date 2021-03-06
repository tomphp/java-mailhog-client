package io.tomoram.mailhog_client.api.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.tomoram.mailhog_client.api.v2.MessagesDeserializer;
import io.tomoram.mailhog_client.helpers.JSON;
import io.tomoram.mailhog_client.model.Message;
import io.tomoram.mailhog_client.model.Messages;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class MessagesDeserializerShould {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();

    @Before
    public void
    register_the_deserializer() {
        module.addDeserializer(Messages.class, new MessagesDeserializer());
        mapper.registerModule(module);
    }

    @Test
    public void
    deserialize_an_empty_collection() throws IOException {
        assertThat(mapper.readValue(JSON.messageCollection(), Messages.class).getCount()).isEqualTo(0);
    }

    @Test
    public void
    deserialize_a_collection_with_1_messages() throws IOException {
        String json = JSON.messageCollection(JSON.singleMessage());

        assertThat(mapper.readValue(json, Messages.class).getCount()).isEqualTo(1);
    }

    @Test
    public void
    deserialize_the_messages_in_collection() throws IOException {
        String json = JSON.messageCollection(JSON.singleMessage());

        Message expected = Message.builder()
                .setSender("mental@rhino.com")
                .addRecipient("george@a-happy-place.com")
                .build();

        assertThat(mapper.readValue(json, Messages.class).stream().findFirst().orElse(null)).isEqualTo(expected);
    }
}