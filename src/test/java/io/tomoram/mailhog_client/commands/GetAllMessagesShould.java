package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.api.MessageListFetcher;
import io.tomoram.mailhog_client.exceptions.RequestFailed;
import io.tomoram.mailhog_client.model.Message;
import io.tomoram.mailhog_client.model.Messages;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllMessagesShould {
    MessageListFetcher fetcher = mock(MessageListFetcher.class);

    GetAllMessages command = new GetAllMessages(fetcher);

    @Test
    public void
    return_an_empty_stream_if_there_are_no_messages() throws RequestFailed {
        when(fetcher.fetchFrom("/api/v2/messages")).thenReturn(new Messages(0, Arrays.asList()));

        assertThat(command.execute().count()).isEqualTo(0);
    }

    @Test
    public void
    return_a_single_email_when_only_1_is_in_the_mailbox() throws RequestFailed {
        Message message = Message.builder()
                .addRecipient("jeff@my-name-is.com")
                .setSender("sales@miscellanious-products.com")
                .build();

        when(fetcher.fetchFrom("/api/v2/messages")).thenReturn(new Messages(1, Arrays.asList(message)));
        assertThat(command.execute().count()).isEqualTo(1);
    }
}
