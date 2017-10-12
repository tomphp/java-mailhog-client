package io.tomoram.mailhog_client.exceptions;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestFailedShould {

    private RequestFailed instance() {
        return RequestFailed.withErrorStatusCode(500, "GET", "http://localhost");
    }

    @Test
    public void
    be_an_instance_of_MailHogClientException() {
        assertThat(instance())
                .isInstanceOf(MailHogClientException.class);
    }

    @Test
    public void
    be_an_instance_of_IOException() {
        assertThat(instance()).isInstanceOf(IOException.class);
    }

    @Test
    public void
    have_a_factory_for_when_the_underlying_HTTP_library_throws() {
        Exception cause = new Exception("Example cause");
        RequestFailed exception = RequestFailed.withHttpLibraryError("POST", "http://example.com", cause);

        assertThat(exception.getMessage()).isEqualTo("There was an error when making a POST request to http://example.com.");
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    public void
    have_a_factory_for_when_the_HTTP_request_has_an_error_status_code() {
        RequestFailed exception = RequestFailed.withErrorStatusCode(500, "GET", "http://example.net");

        assertThat(exception.getMessage()).isEqualTo("GET request to http://example.net returned a 500 status code.");
    }
}