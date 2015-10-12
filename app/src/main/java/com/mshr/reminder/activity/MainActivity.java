package com.mshr.reminder.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import com.mshr.reminder.R;
import com.mshr.reminder.constant.Constant;
import com.mshr.reminder.debug.Debug;
import com.mshr.reminder.receiver.ScheduleReceiver;

public class MainActivity extends Activity implements View.OnClickListener {
  private static final int ON_BUTTON_ID  = R.id.on_button;
  private static final int OFF_BUTTON_ID = R.id.off_button;

  private Debug         mDebug;
  private Button        mOn_Button;
  private Button        mOff_Button;
  private AlarmManager  mAlarmManager;
  private PendingIntent mPendingIntent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mDebug = new Debug(getApplicationContext(), getClass().getSimpleName());

    initView();
    initAlarm();
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
    mOn_Button  = (Button)findViewById(ON_BUTTON_ID);
    mOff_Button = (Button)findViewById(OFF_BUTTON_ID);

    mOn_Button.setOnClickListener(this);
    mOff_Button.setOnClickListener(this);
  }

  private void startAlarmManager() {
    mDebug.shortToast("startAlarmManager");

    long firstTime  = SystemClock.elapsedRealtime();
    mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 10 * Constant.MINUTE, getPendingIntent());
  }

  private void stopAlarmManager() {
    mDebug.shortToast("stopAlarmManager");

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
