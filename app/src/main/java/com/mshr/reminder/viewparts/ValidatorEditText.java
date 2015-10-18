package com.mshr.reminder.viewparts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnFocusChangeListener;

public class ValidatorEditText extends OriginalFontEditText implements OnFocusChangeListener {
  protected OnInputInsectionListener onInputInsectionListener = null;

  public ValidatorEditText(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initialize();
  }
  
  public ValidatorEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    initialize();
  }
  
  public ValidatorEditText(Context context) {
    super(context);
    initialize();
  }
  
  /**
   * OnTextChangedListenerを設定する
   * @param l Listener
   */
  public void setOnInputInsectionListener(OnInputInsectionListener l) {
    this.onInputInsectionListener = l;
  }
  
  private void initialize() {
    setOnFocusChangeListener(this);
  }
  
  /**
   * Focusを外す 
   */
  public void depriveFocus() {
    setFocusableInTouchMode(false);
    setFocusable(false);
    setFocusableInTouchMode(true);
    setFocusable(true);
  }
  
  /**
   * 修正する気があるみたいだからとりあえず警告を消す
   */
  private void temporarilySuccess() {
    boolean isValidatable = onInputInsectionListener != null;
    if (isValidatable) {
      onInputInsectionListener.inputSuccess(this);
    }
  }

  @Override
  public void onFocusChange(View v, boolean hasFocus) {
    if (hasFocus) {
      temporarilySuccess();
    }
  }
}
