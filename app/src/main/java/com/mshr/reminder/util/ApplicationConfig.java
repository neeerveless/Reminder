package com.mshr.reminder.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by MSHR on 2015/10/18.
 */
public class ApplicationConfig {
  private SharedPreferences         mData;
  private SharedPreferences.Editor  mEditor;

  public ApplicationConfig(Context context, String preferenceName, int mode) {
    mData   = context.getSharedPreferences(preferenceName, mode);
    mEditor = mData.edit();
  }

  public ApplicationConfig putData(String dataName, Object object) {
    if (object instanceof Boolean) {
      mEditor.putBoolean(dataName, (Boolean)object);
    } else if (object instanceof Float) {
      mEditor.putFloat(dataName, (Float)object);
    } else if (object instanceof Integer) {
      mEditor.putInt(dataName, (Integer)object);
    } else if (object instanceof Long) {
      mEditor.putLong(dataName, (Long)object);
    } else if (object instanceof String) {
      mEditor.putString(dataName, (String)object);
    }
    return this;
  }

  public Object getData(String dataName) {
    if (!mData.contains(dataName)) {
      return null;
    }
    return mData.getAll().get(dataName);
  }

  public void commit() {
    mEditor.commit();
  }
}
