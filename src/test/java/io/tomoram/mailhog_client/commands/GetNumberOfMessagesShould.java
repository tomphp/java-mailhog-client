package io.tomoram.mailhog_client.commands;

import io.tomoram.mailhog_client.HTTPClient;
import io.tomoram.mailhog_client.exceptions.RequestFailed;
import io.tomoram.mailhog_client.helpers.JSON;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetNumberOfMessagesShould {
    HTTPClient http = mock(HTTPClient.class);
    GetNumberOfMessages command = new GetNumberOfMessages(http);

    @Test
    public void
    return_an_empty_list_when_there_are_no_messages() throws RequestFailed {
        when(http.get("/api/v2/messages")).thenReturn(
                "{\n" +
                        "    \"count\": 0,\n" +
                        "    \"items\": [],\n" +
                        "    \"start\": 0,\n" +
                        "    \"total\": 0\n" +
                        "}"
        );

        assertThat(command.execute()).isEqualTo(0);
    }

    @Test
    public void
    return_1_when_there_is_1_message() throws RequestFailed {
        when(http.get("/api/v2/messages")).thenReturn(
                "{\n" +
                        "    \"count\": 1,\n" +
                        "    \"items\": [\n" +
                        JSON.singleMessage() +
                        "    ],\n" +
                        "    \"start\": 0,\n" +
                        "    \"total\": 1\n" +
                        "}");

        assertThat(command.execute()).isEqualTo(1);
    }
}
