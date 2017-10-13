package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.api.MessageListFetcher;
import io.tomoram.mailhog_client.exceptions.InvalidResponse;
import io.tomoram.mailhog_client.exceptions.RequestFailed;
import io.tomoram.mailhog_client.model.Message;
import io.tomoram.mailhog_client.model.Messages;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetNumberOfMessagesSentToShould {

    private MessageListFetcher fetcher = mock(MessageListFetcher.class);
    private GetNumberOfMessagesSentTo command = new GetNumberOfMessagesSentTo(fetcher);

    @Test
    public void
    return_an_empty_stream_if_there_are_no_messages() throws RequestFailed, InvalidResponse {
        when(fetcher.fetchFrom("/api/v2/messages?kind=to&query=crazy@frog.com"))
                .thenReturn(new Messages(0, Arrays.asList()));

        assertThat(command.execute("crazy@frog.com")).isEqualTo(0);
    }

    @Test
    public void
    return_the_number_of_messages_found() throws RequestFailed, InvalidResponse {
        Message message = Message.builder().addRecipient("mad@badger.com").build();

        when(fetcher.fetchFrom("/api/v2/messages?kind=to&query=mad@badger.com"))
                .thenReturn(new Messages(1, Arrays.asList(message)));

        assertThat(command.execute("mad@badger.com")).isEqualTo(1);
    }
}