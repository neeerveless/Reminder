package com.mshr.reminder.viewparts;

import android.widget.EditText;

/**
 * @author MSHR
 * EditTextの入力をチェックするときのインターフェース
 */
public interface OnInputInsectionListener {
  public static final String NULL_STRING = "";
  /**
   * 入力失敗を知らせる
   * @param editText
   */
  public void inputFailure(EditText editText);
  /**
   * 入力成功を知らせる
   * @param editText
   */
  public void inputSuccess(EditText editText);
}
