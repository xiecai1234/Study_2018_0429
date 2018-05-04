package com.example.xiecaibao.study.eschool.h5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.example.xiecaibao.study.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestH5Activity extends AppCompatActivity {
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.wv)
    WebView wv;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);
        ButterKnife.bind(this);

        //获取webSettings
        WebSettings settings = wv.getSettings();
        //让webView支持JS
        settings.setJavaScriptEnabled(true);
        //加载百度网页
//        wv.loadUrl("http://www.baidu.com/");
        //这个时候就能显示百度页面了
        //加载本地assets目录下的静态网页
        wv.loadUrl("file:///android_asset/123.html");
        //第一个参数把自身传给js 第二个参数是this的一个名字
        //这个方法用于让H5调用android方法
        wv.addJavascriptInterface(this, "android");
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                //参数 “javascript:”  +  js方法名
                wv.loadUrl("javascript:message()");
                break;
            case R.id.btn2:
                //在android调用js有参的函数的时候参数要加单引号
//                wv.loadUrl("javascript:message2('" name "')");
                break;
        }
    }

    //下面的两个方法是让网页来调的
    //这个注解必须加 因为 兼容问题
    @JavascriptInterface
    public void setMessage() {
        Toast.makeText(this, "我弹", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void setMessage(String name) {
        Toast.makeText(this, "我弹弹" + name, Toast.LENGTH_SHORT).show();
    }
}
