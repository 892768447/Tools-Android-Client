LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := tools
LOCAL_SRC_FILES := Tools.cpp
#LOCAL_CFLAGS := -fvisibility=hidden
#LOCAL_LDLIBS := -llog
LOCAL_LDLIBS := -L$(SYSROOT)/usr/lib -llog
#cmd-strip = $(TOOLCHAIN_PREFIX)strip --strip-debug -x $1

include $(BUILD_SHARED_LIBRARY)
