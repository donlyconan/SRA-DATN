package com.donly.funny.app.utils

import android.util.Log

object LogUtils {
    const val DEBUG = true

    fun d(tag: String?, message: String): Int {
        if (DEBUG) {
            return Log.d(tag, message)
        }
        return -1
    }

    fun d(getClass: Class<*>, message: String): Int {
        if (DEBUG) {
            return Log.d(getClass.simpleName, message)
        }
        return -1
    }

    fun e(tag: String?, message: String): Int {
        if (DEBUG) {
            return Log.e(tag, message)
        }
        return -1
    }

    fun e(getClass: Class<*>, message: String): Int {
        if (DEBUG) {
            return Log.e(getClass.simpleName, message)
        }
        return -1
    }


    fun i(tag: String?, message: String): Int {
        if (DEBUG) {
            return Log.i(tag, message)
        }
        return -1
    }

    fun i(getClass: Class<*>, message: String): Int {
        if (DEBUG) {
            return Log.i(getClass.simpleName, message)
        }
        return -1
    }

    fun v(tag: String?, message: String): Int {
        if (DEBUG) {
            return Log.v(tag, message)
        }
        return -1
    }

    fun v(getClass: Class<*>, message: String): Int {
        if (DEBUG) {
            return Log.v(getClass.simpleName, message)
        }
        return -1
    }

    fun w(tag: String?, message: String): Int {
        if (DEBUG) {
            return Log.w(tag, message)
        }
        return -1
    }

    fun w(getClass: Class<*>, message: String): Int {
        if (DEBUG) {
            return Log.w(getClass.simpleName, message)
        }
        return -1
    }
}