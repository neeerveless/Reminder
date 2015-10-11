package com.mshr.reminder.debug;

import com.mshr.reminder.util.StringUtil;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * @author MSHR
 * デバックする時にあると便利なクラス
 */
public class Debug {
  private Context context;
  private String TAG;
  
  /**
   * @param Context getApplicationContext()
   * @param tag     getClass().getSimpleName()
   */
  public Debug(Context Context, String tag) {
    this.context = Context;
    this.TAG = tag;
  }
  
  /**
   * Gray Log
   * @param msg 表示メッセ-ジ
   */
  public void verboseLog(String msg) {
    Log.v(TAG, msg);
  }
  
  /**
   * Blue Log
   * @param msg 表示メッセ-ジ
   */
  public void debugLog(String msg) {
    Log.d(TAG, msg);
  }
  
  /**
   * Green Log
   * @param msg 表示メッセ-ジ
   */
  public void infoLog(String msg) {
    Log.i(TAG, msg);
  }
  
  /**
   * Orange Log
   * @param msg 表示メッセ-ジ
   */
  public void warnLog(String msg) {
    Log.v(TAG, msg);
  }
  
  /**
   * Red Log
   * @param msg 表示メッセ-ジ
   */
  public void errorLog(String msg) {
    Log.e(TAG, msg);
  }
  
  /**
   * Toastを作成する
   * @param msg       表示メッセージ
   * @param duration  表示時間
   * @return
   */
  private Toast createToast(String msg, int duration) {
    String displayMessage = StringUtil.stringBuild(TAG, StringUtil.COLON, msg);
    return Toast.makeText(context, displayMessage, duration);
  }
  
  /**
   * Long Toast
   * @param msg 表示メッセ-ジ
   */
  public void longToast(String msg) {
    createToast(msg, Toast.LENGTH_LONG).show();
  }
  
  /**
   * Short Toast
   * @param msg 表示メッセージ
   */
  public void shortToast(String msg) {
    createToast(msg, Toast.LENGTH_SHORT).show();
  }
}
