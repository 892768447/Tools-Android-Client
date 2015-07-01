#include <sstream>
#include <string>
#include <map>
#include <time.h>
#include <sys/ptrace.h>
#include <android/log.h>
#include <jni.h>
#include "Tools.h"

#define DEBUG false
#define random(x) (rand()%x)
#define LOG_TAG "TOOLS"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG ,__VA_ARGS__) // 定义LOGD类

JNIEXPORT jstring JNICALL Java_zce_moyan_tools_util_Tools_encrypt(JNIEnv* env,
		jobject obj, jstring data) {
	//自己实现加密算法(需要与服务器的解密算法对应)
	return env->NewStringUTF("");
}

JNIEXPORT jstring JNICALL Java_zce_moyan_tools_util_Tools_decrypt(JNIEnv* env,
		jobject obj, jstring data) {
	//自己实现另类解密算法(需要与服务器的加密算法对应)
	return env->NewStringUTF("");
}
