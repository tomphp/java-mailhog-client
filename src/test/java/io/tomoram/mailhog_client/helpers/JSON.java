package io.tomoram.mailhog_client.helpers;

import io.tomoram.mailhog_client.model.Message;

import java.util.Arrays;

public class JSON {
    public static String messageCollection(final String... messages) {
        return "{\n" +
                "    \"count\": " + messages.length + ",\n" +
                "    \"items\": [" + String.join(",", messages) + "],\n" +
                "    \"start\": 0,\n" +
                "    \"total\": " + messages.length + "\n" +
                "}";
    }

    public static String singleMessage() {
        return "{\n" +
                "  \"Content\": {\n" +
                "    \"Body\": \"New user info.\",\n" +
                "    \"Headers\": {\n" +
                "      \"Content-Transfer-Encoding\": [\n" +
                "        \"7bit\"\n" +
                "      ],\n" +
                "      \"Content-Type\": [\n" +
                "        \"text/plain; charset=UTF-8\"\n" +
                "      ],\n" +
                "      \"Date\": [\n" +
                "        \"Sun, 8 Oct 2017 23:40:21 +0100 (BST)\"\n" +
                "      ],\n" +
                "      \"MIME-Version\": [\n" +
                "        \"1.0\"\n" +
                "      ],\n" +
                "      \"Message-ID\": [\n" +
                "        \"<1894212037.2.1507502421747@tom-mac>\"\n" +
                "      ],\n" +
                "      \"Received\": [\n" +
                "        \"from tom-mac by mailhog.example (MailHog)\\r\\n          id VE-UM8tnD0fmpakIsxgp0RCTBH30jQuAazF3GzAj4as=@mailhog.example; Sun, 08 Oct 2017 22:40:21 +0000\"\n" +
                "      ],\n" +
                "      \"Return-Path\": [\n" +
                "        \"<tomoram@tom-mac>\"\n" +
                "      ],\n" +
                "      \"Subject\": [\n" +
                "        \"Welcome\"\n" +
                "      ],\n" +
                "      \"To\": [\n" +
                "        \"george@a-happy-place.com\"\n" +
                "      ]\n" +
                "    },\n" +
                "    \"MIME\": null,\n" +
                "    \"Size\": 251\n" +
                "  },\n" +
                "  \"Created\": \"2017-10-08T22:40:21.750865489Z\",\n" +
                "  \"From\": {\n" +
                "    \"Domain\": \"tom-mac\",\n" +
                "    \"Mailbox\": \"tomoram\",\n" +
                "    \"Params\": \"\",\n" +
                "    \"Relays\": null\n" +
                "  },\n" +
                "  \"ID\": \"VE-UM8tnD0fmpakIsxgp0RCTBH30jQuAazF3GzAj4as=@mailhog.example\",\n" +
                "  \"MIME\": null,\n" +
                "  \"Raw\": {\n" +
                "    \"Data\": \"Date: Sun, 8 Oct 2017 23:40:21 +0100 (BST)\\r\\nTo: george@a-happy-place.com\\r\\nMessage-ID: <1894212037.2.1507502421747@tom-mac>\\r\\nSubject: Welcome\\r\\nMIME-Version: 1.0\\r\\nContent-Type: text/plain; charset=UTF-8\\r\\nContent-Transfer-Encoding: 7bit\\r\\n\\r\\nNew user info.\",\n" +
                "    \"From\": \"tomoram@tom-mac\",\n" +
                "    \"Helo\": \"tom-mac\",\n" +
                "    \"To\": [\n" +
                "      \"george@a-happy-place.com\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"To\": [\n" +
                "    {\n" +
                "      \"Domain\": \"a-happy-place.com\",\n" +
                "      \"Mailbox\": \"george\",\n" +
                "      \"Params\": \"\",\n" +
                "      \"Relays\": null\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}
