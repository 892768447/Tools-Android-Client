#include <jni.h>

#ifndef _Included_zce_moyan_tools_util_Tools
#define _Included_zce_moyan_tools_util_Tools
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jstring JNICALL Java_zce_moyan_tools_util_Tools_encrypt
  (JNIEnv *, jobject, jstring);

JNIEXPORT jstring JNICALL Java_zce_moyan_tools_util_Tools_decrypt
  (JNIEnv *, jobject, jstring);

#ifdef __cplusplus
}
#endif
#endif
