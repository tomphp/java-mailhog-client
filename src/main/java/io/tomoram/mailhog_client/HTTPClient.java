package io.tomoram.mailhog_client;

import io.tomoram.mailhog_client.exceptions.RequestFailed;

public interface HTTPClient {
    String get(String path) throws RequestFailed;

    String delete(String path) throws RequestFailed;
}
