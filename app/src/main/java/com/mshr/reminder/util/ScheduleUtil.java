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
    if (!isBetweenNowAndAfterTenMinutes(calendar, scheduleElements[Constant.START_TIME_INDEX]))
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

  private static boolean isBetweenNowAndAfterTenMinutes(Calendar calendar, String startTime) {
    return isWithinAfterTenMinute(calendar, startTime) && isFutuer(calendar, startTime);
  }

  private static Calendar getScheduleCalender(Calendar calendar, String startTime) {
    Calendar scheduleCalender  = (Calendar)calendar.clone();
    String[] startTimeElements = startTime.split(Constant.COLON);

    scheduleCalender.set(Calendar.HOUR  , Integer.valueOf(startTimeElements[Constant.START_HOUR]));
    scheduleCalender.set(Calendar.MINUTE, Integer.valueOf(startTimeElements[Constant.START_MINUTE]));

    return scheduleCalender;
  }

  private static boolean isWithinAfterTenMinute(Calendar calendar, String startTime) {
    Calendar scheduleCalender = getScheduleCalender(calendar, startTime);

    calendar.add(Calendar.MINUTE, Constant.ALERT_TIME);

    boolean result = scheduleCalender.compareTo(calendar) < 0 ? true : false;
    return result;
  }

  private static boolean isFutuer(Calendar calendar, String startTime) {
    Calendar scheduleCalender = getScheduleCalender(calendar, startTime);

    boolean result = scheduleCalender.compareTo(calendar) < 0 ? false : true;
    return result;

  }
}
