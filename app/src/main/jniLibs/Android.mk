LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := demo
LOCAL_SRC_FILES := LifecycleActivity.c
include $(BUILD_SHARED_LIBRARY)
APP_PLATFORM :=android-16