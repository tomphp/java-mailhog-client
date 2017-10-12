package io.tomoram.mailhog_client.clients;

import io.tomoram.mailhog_client.HTTPClient;
import io.tomoram.mailhog_client.exceptions.RequestFailed;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public final class OkHTTPClient implements HTTPClient {

    private final String baseUrl;

    public OkHTTPClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String get(String path) throws RequestFailed {
        Request request = new Request.Builder()
            .url(url(path))
            .build();

        return executeRequest(path, request);
    }

    @Override
    public String delete(String path) {
        return null;
    }

    private String executeRequest(String path, Request request) throws RequestFailed {
        OkHttpClient client = new OkHttpClient();

        try {
            Response response = client.newCall(request).execute();
            assertSuccess(path, response, 200);

            return response.body().string();
        } catch (IOException e) {
            throw RequestFailed.withHttpLibraryError("GET", url(path), e);
        }
    }

    private void assertSuccess(String path, Response response, int success) throws RequestFailed {
        if (response.code() != success) {
            throw RequestFailed.withErrorStatusCode(response.code(), "GET", url(path));
        }
    }

    private String url(String path) {
        return baseUrl + path;
    }
}
