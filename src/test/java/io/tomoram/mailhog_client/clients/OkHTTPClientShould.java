package io.tomoram.mailhog_client.clients;

import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.tomoram.mailhog_client.HTTPClient;
import io.tomoram.mailhog_client.exceptions.RequestFailed;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

public class OkHTTPClientShould {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort().dynamicHttpsPort());

    private HTTPClient client;

    @Before
    public void
    create_an_instance_to_test() {
        client = new OkHTTPClient("http://localhost:" + wireMockRule.port());
    }

    @Test
    public void
    return_the_body_of_a_GET_request() throws RequestFailed {
        stubFor(get(urlEqualTo("/get-path"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("GET Request Body")));

        assertThat(client.get("/get-path")).isEqualTo("GET Request Body");
    }

    @Test(expected = RequestFailed.class)
    public void
    throw_an_exception_when_a_GET_request_fails() throws RequestFailed {
        stubFor(get(urlEqualTo("/failure"))
                .willReturn(aResponse().withFault(Fault.MALFORMED_RESPONSE_CHUNK)));

        client.get("/failure");
    }

    @Test(expected = RequestFailed.class)
    public void
    throw_an_exception_when_a_GET_request_returns_an_error_status_code() throws RequestFailed {
        stubFor(get(urlEqualTo("/error"))
                .willReturn(aResponse().withStatus(400)));

        client.get("/error");
    }

    @Test
    public void
    send_a_DELETE_request() throws RequestFailed {
        stubFor(delete(urlEqualTo("/thing-to-delete"))
                .willReturn(aResponse()
                        .withStatus(200)));

        client.delete("/thing-to-delete");
    }

    @Test(expected = RequestFailed.class)
    public void
    throw_an_exception_when_a_DELETE_request_fails() throws RequestFailed {
        stubFor(delete(urlEqualTo("/failure"))
                .willReturn(aResponse().withFault(Fault.MALFORMED_RESPONSE_CHUNK)));

        client.delete("/failure");
    }
}
