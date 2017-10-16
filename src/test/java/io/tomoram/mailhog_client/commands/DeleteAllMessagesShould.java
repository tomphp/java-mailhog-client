package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.HTTPClient;
import io.tomoram.mailhog_client.exceptions.RequestFailed;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeleteAllMessagesShould {
    HTTPClient http = mock(HTTPClient.class);
    DeleteAllMessages command = new DeleteAllMessages(http);

    @Test
    public void
    send_a_delete_request_to_the_v1_api_message_collection() throws RequestFailed {
        command.execute();

        verify(http).delete("/api/v1/messages");
    }
}
