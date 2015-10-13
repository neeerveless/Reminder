package com.mshr.reminder.util;

import com.mshr.reminder.constant.Constant;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MSHR on 2015/10/12.
 */
public class ScheduleUtil {

  private ScheduleUtil() {}

  public static Map<String, String> parseSchedule(Calendar calendar, String schedule) {
    String[] scheduleElements = schedule.split(Constant.SPACE, 4);
    if (!isBetweenNowAndAfterAlertTime(calendar, scheduleElements[Constant.START_TIME_INDEX]))
      return null;

    Map<String, String> scheduleMap = new HashMap<>();
    scheduleMap.put(Constant.TIME_KEY, StringUtil.stringBuild(
        scheduleElements[Constant.START_TIME_INDEX],
        scheduleElements[Constant.SEPARATER_INDEX],
        scheduleElements[Constant.END_TIME_INDEX]
    ));
    scheduleMap.put(Constant.TITLE_KEY, scheduleElements[Constant.TITLE_INDEX]);

    return scheduleMap;
  }

  private static boolean isBetweenNowAndAfterAlertTime(Calendar calendar, String startTime) {
    return isWithinAlertTime(calendar, startTime) && isFuture(calendar, startTime);
  }

  private static Calendar getScheduleCalender(Calendar calendar, String startTime) {
    Calendar scheduleCalendar  = (Calendar)calendar.clone();
    String[] startTimeElements = startTime.split(Constant.COLON);

    scheduleCalendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(startTimeElements[Constant.START_HOUR]));
    scheduleCalendar.set(Calendar.MINUTE, Integer.valueOf(startTimeElements[Constant.START_MINUTE]));

    return scheduleCalendar;
  }

  private static boolean isWithinAlertTime(Calendar calendar, String startTime) {
    Calendar scheduleCalendar = getScheduleCalender(calendar, startTime);
    Calendar alertCalendar    = (Calendar)calendar.clone();
    alertCalendar.add(Calendar.MINUTE, Constant.ALERT_TIME);

    boolean result = scheduleCalendar.compareTo(alertCalendar) <= 0 ? true : false;
    return result;
  }

  private static boolean isFuture(Calendar calendar, String startTime) {
    Calendar scheduleCalendar = getScheduleCalender(calendar, startTime);

    boolean result = scheduleCalendar.compareTo(calendar) < 0 ? false : true;
    return result;

  }
}
