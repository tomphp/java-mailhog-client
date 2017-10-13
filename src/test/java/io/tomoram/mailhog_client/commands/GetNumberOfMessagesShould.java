package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.api.MessageListFetcher;
import io.tomoram.mailhog_client.exceptions.InvalidResponse;
import io.tomoram.mailhog_client.exceptions.RequestFailed;
import io.tomoram.mailhog_client.model.Message;
import io.tomoram.mailhog_client.model.Messages;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetNumberOfMessagesShould {
    final MessageListFetcher fetcher = mock(MessageListFetcher.class);
    final GetNumberOfMessages command = new GetNumberOfMessages(fetcher);

    @Test
    public void
    return_an_empty_list_when_there_are_no_messages() throws RequestFailed, InvalidResponse {
        when(fetcher.fetchFrom("/api/v2/messages")).thenReturn(new Messages(0, new ArrayList<>()));

        assertThat(command.execute()).isEqualTo(0);
    }

    @Test
    public void
    return_1_when_there_is_1_message() throws RequestFailed, InvalidResponse {
        Message message = Message.builder()
                .addRecipient("jeff@my-name-is.com")
                .setSender("sales@miscellanious-products.com")
                .build();

        when(fetcher.fetchFrom("/api/v2/messages")).thenReturn(new Messages(1, Arrays.asList(message)));

        assertThat(command.execute()).isEqualTo(1);
    }
}
