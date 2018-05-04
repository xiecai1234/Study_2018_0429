package com.example.xiecaibao.study.okhttp3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.xiecaibao.study.utils.LogX;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpTestActivity extends AppCompatActivity {
    private static final String URL = "https://www.baidu.com";
    private String TAG = "xcb";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postDataAsync();
    }

    private void getDataSync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(URL).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        LogX.d(TAG, "response.code() is:" + response.code());
                        LogX.d(TAG, "response.message() is:" + response.message());
                        LogX.d(TAG, "response.body() is:" + response.body().string());
                    }
                } catch (IOException e) {
                    LogX.e(TAG, e.toString(), e);
                }

            }
        }).start();
    }

    private void getDataAsync() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(URL).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    LogX.d(TAG, "response.code() is:" + response.code());
                    LogX.d(TAG, "response.message() is:" + response.message());
                    LogX.d(TAG, "response.body() is:" + response.body().string());
                }
            }
        });
    }

    private void postDataAsync() {
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String jsonStr = "{\"username\":\"lisi\",\"nickname\":\"李四\"}";
        RequestBody requestBody = RequestBody.create(mediaType, jsonStr);
        Request request = new Request.Builder().url(URL).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogX.e(TAG, "onFailure:" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    LogX.d(TAG, "response.code() is:" + response.code());
                    LogX.d(TAG, "response.message() is:" + response.message());
                    LogX.d(TAG, "response.body() is:" + response.body().string());
                }
            }
        });
    }
}
