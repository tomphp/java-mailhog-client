package io.tomoram.mailhog_client.api;

import io.tomoram.mailhog_client.exceptions.InvalidResponse;
import io.tomoram.mailhog_client.exceptions.RequestFailed;
import io.tomoram.mailhog_client.model.Messages;

public interface MessageListFetcher {
    Messages fetchFrom(String url) throws InvalidResponse, RequestFailed;
}
