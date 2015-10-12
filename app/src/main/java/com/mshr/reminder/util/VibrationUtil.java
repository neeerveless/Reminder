package com.mshr.reminder.util;

import android.content.Context;
import android.os.Vibrator;

public class VibrationUtil {

  private VibrationUtil() {}

  public static void vibrate(Context context, int vibrateTime) {
    Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
    vibrator.vibrate(vibrateTime);
  }

  public static void vibrate(Context context, int vibrateTime, int count, int interval) {
    createVibrationThread(context, vibrateTime, count, interval).start();
  }
  
  private static Thread createVibrationThread(final Context context, final int vibrateTime, final int count, final int interval) {
    Thread vibrationThread = new Thread() {
      public void run() {
        vibration(context, vibrateTime, count, interval);
      }
    };
    return vibrationThread;
  }
  
  private static void vibration(Context context, int vibrateTime, int count, int interval) {
    Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
    for (int i = 0; i < count; i++) {
      vibrator.vibrate(vibrateTime);
      sleep(vibrateTime + interval);
    }
  }
  
  private static void sleep(long time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {}
  }
}
