package com.mshr.reminder.viewparts;

import android.content.Context;
import android.util.AttributeSet;

import com.mshr.reminder.R;

/**
 * @author MSHR
 * 入力文字が空かどうかチェックできるクラス
 */
public class TextInsectionEmptyEditText extends ValidatorEditText {
  private static String EMPTY_STR;
  
  public TextInsectionEmptyEditText(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initialize(context);
  }

  public TextInsectionEmptyEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    initialize(context);
  }

  public TextInsectionEmptyEditText(Context context) {
    super(context);
    initialize(context);
  }
  
  public boolean isInputEmpty() {
    depriveFocus();
    String inputTextString = getText().toString();
    validate(inputTextString);
    return !inputTextString.equals(EMPTY_STR);
  }
  
  /**
   * 初期値を設定する
   */
  private void initialize(Context context) {
    EMPTY_STR = context.getString(R.string.empty_string);
  }
  
  /**
   * 引数で与えられた文字列がEmptyかチェック
   * @param inputTextString
   */
  private void validate(String inputTextString) {
    boolean isValidatable = onInputInsectionListener != null;
    if (isValidatable) {
      if (inputTextString.isEmpty()) {
        onInputInsectionListener.inputFailure(this);
      } else {
        onInputInsectionListener.inputSuccess(this);
      }
    }
  }
  
}
