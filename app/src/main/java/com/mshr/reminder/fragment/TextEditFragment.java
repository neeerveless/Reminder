package com.mshr.reminder.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mshr.reminder.R;
import com.mshr.reminder.viewparts.OnInputInsectionListener;
import com.mshr.reminder.viewparts.OriginalFontTextView;
import com.mshr.reminder.viewparts.TextInsectionEmptyEditText;

/**
 * @author MSHR
 * タイトルとか空じゃいけないモノを入力する時に使えるクラス
 */
public class TextEditFragment extends BaseFragment implements OnInputInsectionListener {
  private TextInsectionEmptyEditText  mTextEdit;
  private OriginalFontTextView        mTextVaridatorTextView;
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mRootView = inflater.inflate(R.layout.fragment_text_edit, container, false);
    initialize();
    return mRootView;
  }
  
  public void setText(String text) {
    mTextEdit.setText(text);
  }
  
  public String getText() {
    return mTextEdit.getText().toString();
  }
  
  public boolean isInputSuccess() {
    return mTextEdit.isInputEmpty();
  }
  
  /**
   * 諸々を初期化する
   */
  protected void initialize() {
    initializeEditText();
    initializeTextView();
  }
  
  /**
   * xmlからEditTextを取得し、OnInputInsectionListenerを付ける
   */
  private void initializeEditText() {
    mTextEdit = (TextInsectionEmptyEditText) mRootView.findViewById(R.id.text_edit);
    mTextEdit.setOnInputInsectionListener(this);
  }
  
  /**
   * xmlからTextViewを取得する
   */
  private void initializeTextView() {
    mTextVaridatorTextView = (OriginalFontTextView) mRootView.findViewById(R.id.validator_text_view);
  }

  /**
   * 警告を表示する
   * @param message
   */
  private void displayAlert(String message) {
    mTextVaridatorTextView.setText(message);
  }
  
  @Override
  public void inputFailure(EditText editText) {
    displayAlert(getString(R.string.text_edit_alert));
  }

  @Override
  public void inputSuccess(EditText editText) {
    displayAlert(NULL_STRING);
  }

}
