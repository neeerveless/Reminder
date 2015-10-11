package com.mshr.reminder.util;

public class StringUtil {
  private static final String DISPLAY_TIME_FORMAT = "%02d";
  public static final String COLON = ":";
  
  public static String stringBuild(Object...objects) {
    StringBuilder stringBuilder = new StringBuilder();
    for (Object object : objects) {
      stringBuilder.append(object);
    }
    return new String(stringBuilder);
  }
  
  /**
   * intを決められたフォーマットでStringにする
   * @param time
   * @return
   */
  public static String toTimeFormat(int i) {
    return String.format(DISPLAY_TIME_FORMAT, Integer.valueOf(i));
  }
}
