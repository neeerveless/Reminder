package com.mshr.reminder.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mshr.reminder.debug.Debug;
import com.mshr.reminder.service.MainService;

/**
 * Created by MSHR on 2015/10/12.
 */
public class ScheduleReceiver extends BroadcastReceiver {
  private Context mContext;

  @Override
  public void onReceive(Context context, Intent intent) {
    mContext  = context;
    Debug.errorLog();
    Debug.shortToast(mContext);

    startService();
  }

  private void startService() {
    Intent intent = new Intent(mContext, MainService.class);
    mContext.startService(intent);
  }
}
