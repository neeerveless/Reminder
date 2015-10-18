package com.mshr.reminder.debug;

import com.mshr.reminder.util.StringUtil;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MSHR
 * デバックする時にあると便利なクラス
 */
public class Debug {
  private static final int CALLER   = 5;
  private static final int STR_HEAD = 0;

  private static final String TAG = "TAG";
  private static final String MSG = "MSG";

  private Debug() {}

  /**
   * Gray Log
   *
   * @param msg 表示メッセ-ジ
   */
  public static void verboseLog(String msg) {
    log(Log.VERBOSE, makeLogElements(msg));
  }

  public static void verboseLog() {
    log(Log.VERBOSE, makeLogElements(""));
  }

  /**
   * Blue Log
   *
   * @param msg 表示メッセ-ジ
   */
  public static void debugLog(String msg) {
    log(Log.DEBUG, makeLogElements(msg));
  }

  public static void debugLog() {
    log(Log.DEBUG, makeLogElements(""));
  }

  /**
   * Green Log
   *
   * @param msg 表示メッセ-ジ
   */
  public static void infoLog(String msg) {
    log(Log.INFO, makeLogElements(msg));
  }

  public static void infoLog() {
    log(Log.INFO, makeLogElements(""));
  }

  /**
   * Orange Log
   *
   * @param msg 表示メッセ-ジ
   */
  public static void warnLog(String msg) {
    log(Log.WARN, makeLogElements(msg));
  }

  public static void warnLog() {
    log(Log.WARN, makeLogElements(""));
  }

  /**
   * Red Log
   *
   * @param msg 表示メッセ-ジ
   */
  public static void errorLog(String msg) {
    log(Log.ERROR, makeLogElements(msg));
  }

  public static void errorLog() {
    log(Log.ERROR, makeLogElements(""));
  }

  /**
   * Logの表示要素を作成
   *
   * @param msg 表示メッセージ
   * @return
   */
  private static Map<String, String> makeLogElements(String msg) {
    Map<String, String> logElements = new HashMap<>();

    StackTraceElement caller = getCaller();
    msg = StringUtil.stringBuild(getCallerMethodName(caller), StringUtil.COLON, msg);
    logElements.put(TAG, getCallerClassName(caller));
    logElements.put(MSG, msg);

    return logElements;
  }

  /**
   * Logを表示
   *
   * @param logType
   * @param logElements
   */
  private static void log(int logType, Map<String, String> logElements) {
    switch (logType) {
      case Log.VERBOSE:
        Log.v(logElements.get(TAG), logElements.get(MSG));
        break;
      case Log.DEBUG:
        Log.d(logElements.get(TAG), logElements.get(MSG));
        break;
      case Log.INFO:
        Log.i(logElements.get(TAG), logElements.get(MSG));
        break;
      case Log.WARN:
        Log.w(logElements.get(TAG), logElements.get(MSG));
        break;
      case Log.ERROR:
        Log.e(logElements.get(TAG), logElements.get(MSG));
        break;
    }
  }

  private static String makeToastMessage(String msg) {
    StackTraceElement caller = getCaller();
    msg = StringUtil.stringBuild(
        getCallerClassName(caller),
        StringUtil.COLON,
        getCallerMethodName(caller),
        StringUtil.COLON,
        msg
    );

    return msg;
  }

  /**
   * Toastを作成する
   *
   * @param msg      表示メッセージ
   * @param duration 表示時間
   * @return
   */
  private static Toast makeToast(Context context, String msg, int duration) {
    return Toast.makeText(context, makeToastMessage(msg), duration);
  }

  /**
   * Long Toast
   *
   * @param msg 表示メッセ-ジ
   */
  public static void longToast(Context context, String msg) {
    makeToast(context, msg, Toast.LENGTH_LONG).show();
  }

  public static void longToast(Context context) {
    makeToast(context, "", Toast.LENGTH_LONG).show();
  }

  /**
   * Short Toast
   *
   * @param msg 表示メッセージ
   */
  public static void shortToast(Context context, String msg) {
    makeToast(context, msg, Toast.LENGTH_SHORT).show();
  }

  public static void shortToast(Context context) {
    makeToast(context, "", Toast.LENGTH_SHORT).show();
  }

  private static StackTraceElement getCaller() {
    return Thread.currentThread().getStackTrace()[CALLER];
  }

  private static String getCallerClassName(StackTraceElement caller) {
    String fileName = caller.getFileName();
    return fileName.substring(STR_HEAD, fileName.lastIndexOf(StringUtil.DOT));
  }

  private static String getCallerMethodName(StackTraceElement caller) {
    return caller.getMethodName();
  }
}