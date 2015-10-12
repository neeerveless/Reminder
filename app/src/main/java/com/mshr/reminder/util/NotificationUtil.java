package com.mshr.reminder.util;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;

import com.mshr.reminder.R;
import com.mshr.reminder.constant.Constant;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by MSHR on 2015/10/12.
 */
public class NotificationUtil {
  private Context   mContext;
  private Calendar  mCalendar;

  public NotificationUtil(Context context) {
    mContext  = context;
    mCalendar = Calendar.getInstance();
    mCalendar.setTimeInMillis(System.currentTimeMillis());
  }

  public void showNotifications(String...schedules) {
    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);
    mBuilder.setSmallIcon(R.drawable.ic_launcher);
    NotificationManagerCompat manager = NotificationManagerCompat.from(mContext);

    int index = 0;
    for (String schedule: schedules) {
      Map<String, String> scheduleMap = ScheduleUtil.parseSchedule(mCalendar, schedule);
      if (scheduleMap == null)
        continue;

      mBuilder.setContentTitle(scheduleMap.get(Constant.TITLE_KEY));
      mBuilder.setContentText(scheduleMap.get(Constant.TIME_KEY));
      mBuilder.setLights(Color.BLUE, 1 * Constant.SECOND, 1 * Constant.SECOND);
      mBuilder.setAutoCancel(true);

      manager.notify(++index, mBuilder.build());
    }

    if (index > 0)
      VibrationUtil.vibrate(mContext, 1 * Constant.SECOND, 5, 1 * Constant.SECOND);
  }
}
