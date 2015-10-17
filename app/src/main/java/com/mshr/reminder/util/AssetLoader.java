package com.mshr.reminder.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by MSHR on 2015/10/17.
 */
public class AssetLoader {
  public static final String UTF8 = "UTF-8";

  private static final int READ_LENGTH = 8192;

  private static byte[] readStream(Context context, String assetName)
  throws IOException {
    AssetManager am = context.getAssets();
    BufferedInputStream bis = new BufferedInputStream(am.open(assetName));
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    byte[] bytes = new byte[READ_LENGTH];

    try {
      int length = 0;
      while ((length = bis.read(bytes, 0, READ_LENGTH)) > 0) {
        bos.write(bytes, 0, length);
      }

      return bos.toByteArray();
    } finally {
      try {
        bis.close();
        bos.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static String loadText(Context context, String assetName, String encode) {
    try {
      return new String(readStream(context, assetName), encode);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
