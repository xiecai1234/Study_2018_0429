package com.example.xiecaibao.study.volley;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.xiecaibao.study.utils.LogX;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

public class VolleyTestActivity extends AppCompatActivity{
    private static final String URL = "https://www.baidu.com";
    private static final String URL_JSON = "http://api.1-blog.com/biz/bizserver/article/list.do";
    private String TAG = "xcb";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogX.d(TAG, "response:" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogX.d(TAG, "error:" + error.getMessage(), error);
            }
        });

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL_JSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                LogX.d(TAG, "JSONObject response:" + response.toString());
                Article article = new Gson().fromJson(response.toString(), Article.class);
                List<Article.detail> list = article.getDetail();
                String title = list.get(0).getTitle();
                LogX.d(TAG, "title:" + title);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogX.d(TAG, "error:" + error.getMessage(), error);
            }
        });
        requestQueue.add(stringRequest);
        requestQueue.add(jsonObjectRequest);
    }
}
