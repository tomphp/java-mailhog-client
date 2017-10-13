package io.tomoram.mailhog_client.api.v2;

import io.tomoram.mailhog_client.HTTPClient;
import io.tomoram.mailhog_client.exceptions.RequestFailed;
import io.tomoram.mailhog_client.helpers.JSON;
import io.tomoram.mailhog_client.model.Message;
import io.tomoram.mailhog_client.model.Messages;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class APIv2MessageListFetcherShould {

    private final HTTPClient http = mock(HTTPClient.class);
    private final APIv2MessageListFetcher fetcher = new APIv2MessageListFetcher(http);

    @Test
    public void
    parse_a_list_of_messages() throws RequestFailed {
        when(http.get("/messages")).thenReturn(JSON.messageCollection(JSON.singleMessage()));

        final Message message = Message.builder()
                .addRecipient("george@a-happy-place.com")
                .setSender("mental@rhino.com")
                .build();

        Messages expected = new Messages(1, Collections.singletonList(message));

        assertThat(fetcher.fetchFrom("/messages")).isEqualTo(expected);
    }
}