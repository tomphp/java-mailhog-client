package io.tomoram.mailhog_client;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.tomoram.mailhog_client.exceptions.RequestFailed;
import io.tomoram.mailhog_client.helpers.JSON;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MailboxShould {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort().dynamicHttpsPort());

    HTTPClient http = mock(HTTPClient.class);
    Mailbox mailbox = new Mailbox(http);

    @Test
    public void
    be_choose_a_default_HTTP_client_if_one_is_not_provided() {
        mailbox = new Mailbox("http://localhost:" + wireMockRule.port());

        stubFor(get(urlEqualTo("/api/v2/messages"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(
                                "{\n" +
                                        "    \"count\": 1,\n" +
                                        "    \"items\": [\n" +
                                        JSON.singleMessage() +
                                        "    ],\n" +
                                        "    \"start\": 0,\n" +
                                        "    \"total\": 1\n" +
                                        "}")));

        assertThat(mailbox.getNumberOfMessages()).isEqualTo(1);
    }

    @Test
    public void
    return_the_total_number_of_messages_it_contains() throws RequestFailed {
        when(http.get("/api/v2/messages")).thenReturn(
                "{\n" +
                        "    \"count\": 1,\n" +
                        "    \"items\": [\n" +
                        JSON.singleMessage() +
                        "    ],\n" +
                        "    \"start\": 0,\n" +
                        "    \"total\": 1\n" +
                        "}");


        assertThat(mailbox.getNumberOfMessages()).isEqualTo(1);
    }
}
