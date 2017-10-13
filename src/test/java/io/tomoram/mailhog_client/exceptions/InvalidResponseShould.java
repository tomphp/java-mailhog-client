package io.tomoram.mailhog_client.exceptions;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class InvalidResponseShould {
    private InvalidResponse instance() {
        return InvalidResponse.forJSONParsingError("http://api", new Exception());
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
    have_a_factory_for_when_JSON_parsing_fails() {
        Exception cause = new Exception("Example cause");
        InvalidResponse exception = InvalidResponse.forJSONParsingError("http://api", cause);

        assertThat(exception.getMessage()).isEqualTo("There was an error when parsing the JSON response from http://api.");
        assertThat(exception.getCause()).isEqualTo(cause);
    }

}