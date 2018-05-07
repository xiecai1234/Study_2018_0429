package com.example.xiecaibao.study.jnitest;

public class Hello {
    static {
        System.loadLibrary("Hello");
    }

    public static native String getFromC();
}
