package com.mshr.reminder.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.widget.Button;

import com.mshr.reminder.R;
import com.mshr.reminder.constant.Constant;
import com.mshr.reminder.debug.Debug;
import com.mshr.reminder.fragment.BaseFragmentActivity;
import com.mshr.reminder.fragment.PasswordEditFragment;
import com.mshr.reminder.fragment.TextEditFragment;
import com.mshr.reminder.receiver.ScheduleReceiver;
import com.mshr.reminder.util.ApplicationConfig;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {
  private static final int CID_TEXT_EDIT_ID = R.id.c_id_text_edit;
  private static final int PID_TEXT_EDIT_ID = R.id.p_id_text_edit;
  private static final int PASSWORD_EDIT_ID = R.id.password_edit;
  private static final int ON_BUTTON_ID     = R.id.on_button;
  private static final int OFF_BUTTON_ID    = R.id.off_button;

  private TextEditFragment      mCidTextEdit;
  private TextEditFragment      mPidTextEdit;
  private PasswordEditFragment  mPasswordEdit;
  private Button                mOnButton;
  private Button                mOffButton;


  private ApplicationConfig mApplicationConfig;
  private AlarmManager      mAlarmManager;
  private PendingIntent     mPendingIntent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mApplicationConfig = new ApplicationConfig(
        getApplicationContext(),
        Constant.CONFIG,
        Context.MODE_PRIVATE
    );

    initView();
    initAlarm();
  }

  private void deleteAllCookies() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      CookieSyncManager.createInstance(getApplicationContext());
      CookieManager cookieManager = CookieManager.getInstance();
      cookieManager.removeAllCookie();
    } else {
      CookieManager cookieManager = CookieManager.getInstance();
      cookieManager.removeAllCookies(new ValueCallback<Boolean>() {
        @Override
        public void onReceiveValue(Boolean value) {

        }
      });
    }
  }

  private PendingIntent getPendingIntent() {
    if (mPendingIntent != null)
      return mPendingIntent;

    Intent intent   = new Intent(getApplicationContext(), ScheduleReceiver.class);
    mPendingIntent  = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
    return mPendingIntent;
  }

  private  void initAlarm() {
    mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
  }

  private void initView() {
    mCidTextEdit  = (TextEditFragment)mFragmentManager.findFragmentById(CID_TEXT_EDIT_ID);
    mPidTextEdit  = (TextEditFragment)mFragmentManager.findFragmentById(PID_TEXT_EDIT_ID);
    mPasswordEdit = (PasswordEditFragment)mFragmentManager.findFragmentById(PASSWORD_EDIT_ID);
    mOnButton     = (Button)findViewById(ON_BUTTON_ID);
    mOffButton    = (Button)findViewById(OFF_BUTTON_ID);

    mCidTextEdit  .setText((String)mApplicationConfig.getData(Constant.C_ID));
    mPidTextEdit  .setText((String)mApplicationConfig.getData(Constant.P_ID));
    mPasswordEdit .setText((String)mApplicationConfig.getData(Constant.PASSWORD));

    mOnButton.setOnClickListener(this);
    mOffButton.setOnClickListener(this);
  }

  private boolean isInputSuccess() {
    boolean isCidValid      = mCidTextEdit.isInputSuccess();
    boolean isPidValid      = mPidTextEdit.isInputSuccess();
    boolean isPasswordValid = mPasswordEdit.isInputSuccess();
    return isCidValid && isPidValid && isPasswordValid;
  }

  private void startAlarmManager() {
    Debug.errorLog();
    Debug.shortToast(getApplicationContext());

    if (!isInputSuccess()) {
      return;
    }

    deleteAllCookies();

    mApplicationConfig
        .putData(Constant.C_ID,     mCidTextEdit.getText())
        .putData(Constant.P_ID,     mPidTextEdit.getText())
        .putData(Constant.PASSWORD, mPasswordEdit.getText())
        .commit();

    long firstTime  = SystemClock.elapsedRealtime();
    mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 10 * Constant.MINUTE, getPendingIntent());
  }

  private void stopAlarmManager() {
    Debug.errorLog();
    Debug.shortToast(getApplicationContext());

    mAlarmManager.cancel(getPendingIntent());
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case ON_BUTTON_ID:
        startAlarmManager();
        break;
      case OFF_BUTTON_ID:
        stopAlarmManager();
        break;
    }
  }
}
