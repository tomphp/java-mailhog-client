package io.tomoram.mailhog_client;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.tomoram.mailhog_client.exceptions.InvalidResponse;
import io.tomoram.mailhog_client.exceptions.RequestFailed;
import io.tomoram.mailhog_client.helpers.JSON;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class MailboxShould {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort().dynamicHttpsPort());

    HTTPClient http = mock(HTTPClient.class);
    Mailbox mailbox = new Mailbox(http);

    @Test
    public void
    choose_a_default_HTTP_client_if_one_is_not_provided() throws InvalidResponse, RequestFailed {
        mailbox = new Mailbox("http://localhost:" + wireMockRule.port());

        stubFor(get(urlEqualTo("/api/v2/messages"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(JSON.messageCollection(JSON.singleMessage()))));

        assertThat(mailbox.getNumberOfMessages()).isEqualTo(1);
    }

    @Test
    public void
    return_the_number_of_messages_from_getNumberOfMessages() throws RequestFailed, InvalidResponse {
        when(http.get("/api/v2/messages")).thenReturn(JSON.messageCollection(JSON.singleMessage()));

        assertThat(mailbox.getNumberOfMessages()).isEqualTo(1);
    }

    @Test
    public void
    return_the_number_of_messages_for_the_given_recipient_from_getNumberOfMessagesSentTo() throws RequestFailed, InvalidResponse {
        when(http.get("/api/v2/messages?kind=to&query=test@example.com"))
                .thenReturn(JSON.messageCollection(JSON.singleMessage(), JSON.singleMessage()));

        assertThat(mailbox.getNumberOfMessagesSentTo("test@example.com")).isEqualTo(2);
    }

    @Test
    public void
    send_a_delete_request_when_calling_empty() throws RequestFailed {
        mailbox.empty();

        verify(http).delete("/api/v1/messages");
    }
}
