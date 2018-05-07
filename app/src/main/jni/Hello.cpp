//
// Created by Administrator on 2018/5/7.
//

#include "com_example_xiecaibao_study_jnitest_Hello.h"

JNIEXPORT jstring JNICALL Java_com_example_xiecaibao_study_jnitest_Hello_getFromC
  (JNIEnv * env, jclass jcs){
    return env->NewStringUTF("from cpp");
  }
