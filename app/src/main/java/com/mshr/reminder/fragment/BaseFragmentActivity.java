package com.mshr.reminder.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * @author MSHR
 * FragmentActivityを使う時はextendsしたら良いと思うよ
 * FragmentManagerはよく使うし
 */
public abstract class BaseFragmentActivity extends FragmentActivity {
  protected FragmentManager mFragmentManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mFragmentManager = getSupportFragmentManager();
  }

}
