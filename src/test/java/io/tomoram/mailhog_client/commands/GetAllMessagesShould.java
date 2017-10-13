package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.HTTPClient;
import io.tomoram.mailhog_client.exceptions.RequestFailed;
import io.tomoram.mailhog_client.helpers.JSON;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllMessagesShould {
    HTTPClient http = mock(HTTPClient.class);
    GetAllMessages command = new GetAllMessages(http);

    @Test
    public void
    return_an_empty_stream_if_there_are_no_messages() throws RequestFailed {
        when(http.get("/api/v2/messages")).thenReturn(JSON.messageCollection());

        assertThat(command.execute().count()).isEqualTo(0);
    }

    @Test
    public void
    return_a_single_email_when_only_1_is_in_the_mailbox() throws RequestFailed {
        when(http.get("/api/v2/messages")).thenReturn(JSON.messageCollection(JSON.singleMessage()));

        assertThat(command.execute().count()).isEqualTo(1);
    }
}
