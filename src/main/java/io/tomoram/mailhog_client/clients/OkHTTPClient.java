package io.tomoram.mailhog_client.clients;

import io.tomoram.mailhog_client.HTTPClient;
import io.tomoram.mailhog_client.exceptions.RequestFailed;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public final class OkHTTPClient implements HTTPClient {

    public static final int SUCCESS_STATUS_CODE = 200;

    private final String baseUrl;

    public OkHTTPClient(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String get(final String path) throws RequestFailed {
        Request request = new Request.Builder()
            .url(url(path))
            .build();

        return executeRequest(path, request);
    }

    @Override
    public String delete(final String path) {
        return null;
    }

    private String executeRequest(final String path, final Request request) throws RequestFailed {
        OkHttpClient client = new OkHttpClient();

        try {
            Response response = client.newCall(request).execute();
            assertSuccess(path, response, SUCCESS_STATUS_CODE);

            return response.body().string();
        } catch (IOException e) {
            throw RequestFailed.withHttpLibraryError("GET", url(path), e);
        }
    }

    private void assertSuccess(final String path, final Response response, final int success) throws RequestFailed {
        if (response.code() != success) {
            throw RequestFailed.withErrorStatusCode(response.code(), "GET", url(path));
        }
    }

    private String url(final String path) {
        return baseUrl + path;
    }
}
