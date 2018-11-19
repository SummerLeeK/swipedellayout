//
// Created by Administrator on 2018/10/24 0024.
//

#include <jni.h>
// #include "com_example_administrator_myapplication_LifecycleActivity.h"

JNIEXPORT jstring JNICALL Java_com_example_administrator_myapplication_LifecycleActivity_getStrFromJni
  (JNIEnv *env, jobject obj){
  return (*env) -> NewStringUTF(env,"Hello, I'm from jni");
  }