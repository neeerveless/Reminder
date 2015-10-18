package com.mshr.reminder.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mshr.reminder.R;
import com.mshr.reminder.viewparts.OnInputInsectionListener;

/**
 * @author MSHR
 * パスワードとか空じゃいけないモノを入力する時に使えるクラス
 */
public class PasswordEditFragment extends TextEditFragment implements OnInputInsectionListener {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mRootView = inflater.inflate(R.layout.fragment_password_edit, container, false);
    initialize();
    return mRootView;
  }
}
