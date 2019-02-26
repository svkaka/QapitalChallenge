package com.ovrbach.qapitalchallenge;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;

/**
 * This {@link Interceptor} gets plugged into Retrofit's okhttp client and is used to return local
 * JSON data for unit testing instead of going over the network.
 */
public class UnitTestingHttpInterceptor implements Interceptor {

    private static final String FIXTURES_DIR = "src/test/fixtures";

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        System.out.println("");
        final Request request = chain.request();
        final HttpUrl requestedUrl = request.url();
        String requestedPath = requestedUrl.encodedPath();
        final String requestedMethod = request.method();

        if (!requestedPath.endsWith("/")) {
            requestedPath = requestedPath + "/";
        }

        final String fixtureJsonPath = FIXTURES_DIR + requestedPath + requestedMethod + ".json";
        final InputStream inputStream = new FileInputStream(fixtureJsonPath);
        Buffer input = new Buffer().readFrom(inputStream);

        final MediaType mediaType = MediaType.parse("application/json");
        final ResponseBody responseBody = ResponseBody.create(mediaType, input.size(), input);

        return new Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_2)
                .code(200)
                .body(responseBody)
                .message("")
                .build();
    }
}
