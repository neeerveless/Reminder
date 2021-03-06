package com.mshr.reminder.constant;

/**
 * Created by MSHR on 2015/10/12.
 */
public class Constant {
  private Constant() {};

  public static final int SECOND      = 1000;
  public static final int MINUTE      = 60 * SECOND;
  public static final int ALERT_TIME  = 20;

  public static final String AUTO_LOGIN_HTML    = "auto_login.html";
  public static final String CONFIG             = "com.mshr.reminder.config";
  public static final String C_ID               = "com.mshr.reminder.cid";
  public static final String P_ID               = "com.mshr.reminder.pid";
  public static final String PASSWORD           = "com.mshr.reminder.password";
  public static final String MINE_TYPE          = "text/html";
  public static final String ENCODE             = "utf-8";
  public static final String TOP_URL            = "https://gws48.j-motto.co.jp/cgi-bin/JM0302814/dneo.cgi?";
  public static final String DAILY_URL          = "https://gws48.j-motto.co.jp/cgi-bin/JM0302814/dneo.cgi?cmd=schindex#cmd=schday";
  public static final String LOAD_SCHDAY_SCRIPT = "javascript:var array=[];$('.cal-item-box > a').each(function() {array.push($(this).attr('title'));});reminder.onLoadSCHDAY(array);";
  public static final String LOGIN_ERROR        = "ログインエラー｜クラウド型グループウェアならJ-MOTT";

  public static final int START_TIME_INDEX = 0;
  public static final int SEPARATER_INDEX  = 1;
  public static final int END_TIME_INDEX   = 2;
  public static final int TITLE_INDEX      = 3;

  public static final int START_HOUR   = 0;
  public static final int START_MINUTE = 1;

  public static final String SPACE = " ";
  public static final String COLON = ":";

  public static final String TIME_KEY   = "time";
  public static final String TITLE_KEY  = "title";
}
