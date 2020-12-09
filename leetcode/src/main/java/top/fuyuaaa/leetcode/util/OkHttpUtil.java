package top.fuyuaaa.leetcode.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author : fuyuaaa
 * @date : 2020-08-26 09:18
 */
@Slf4j
@SuppressWarnings("all")
public class OkHttpUtil {

    private static final OkHttpClient CLIENT = new OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS).build();
    private static final MediaType JSON = MediaType.parse("application/gson; charset=utf-8");

    public static String get(String url) {
        Request request = new Request.Builder().url(url).build();
        try (Response response = CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Unexpected code " + response);
            }
            return response.body().string();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @SuppressWarnings("all")
    public static Response getForResponse(String url, Map<String, Object> headers) {
        Request request = new Request.Builder().url(url).build();
        Response response;
        try {
            response = CLIENT.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException("Unexpected code " + response);
            }
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String post(String url, String json) {
        return postJson(url, json, null);
    }

    public static String postJson(String url, String json, Map<String, Object> headers) {
        Request request = requestBuilder(url, json, headers);
        try (Response response = CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Unexpected code " + response);
            }
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String postForm(String url, Map<String, Object> params, Map<String, Object> headers) {
        Request request = requestBuilder(url, params, headers);
        try (Response response = CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Unexpected code " + response);
            }
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Request requestBuilder(String url, String json, Map<String, Object> headers) {
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(body);

        if (null != headers && headers.size() != 0) {
            for (String key : headers.keySet()) {
                requestBuilder.header(key, String.valueOf(headers.get(key)));
            }
        }

        return requestBuilder.build();
    }

    private static Request requestBuilder(String url, Map<String, Object> params, Map<String, Object> headers) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (null != params && params.size() > 0) {
            params.forEach((key, value) -> formBuilder.add(key, String.valueOf(value)));
        }
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(formBuilder.build());

        if (null != headers && headers.size() != 0) {
            for (String key : headers.keySet()) {
                requestBuilder.header(key, String.valueOf(headers.get(key)));
            }
        }

        return requestBuilder.build();
    }

    @SuppressWarnings("all")
    public static Response postForResponse(String url, String json, Map<String, Object> headers) {

        Request request = requestBuilder(url, json, headers);
        Response response;
        try {
            response = CLIENT.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException("Unexpected code " + response);
            }
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
