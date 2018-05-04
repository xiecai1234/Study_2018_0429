package com.example.xiecaibao.study.bean;


import com.example.xiecaibao.study.utils.LogX;

/**
 * Created by Carson_Ho on 17/9/8.
 */

public class Translation {


    private int status;

    private content content;
    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {
        LogX.d("xcb", content.out );
    }
}
