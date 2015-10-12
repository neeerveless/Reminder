package com.mshr.reminder.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mshr.reminder.constant.Constant;
import com.mshr.reminder.debug.Debug;
import com.mshr.reminder.util.NotificationUtil;

/**
 * Created by MSHR on 2015/10/12.
 */
public class MainService extends Service {
  private Debug   mDebug;
  private Context mContext;

  private WebViewClient mViewClient = new WebViewClient() {
    @Override
    public void onPageFinished(WebView webView, String url) {
      switch (url) {
        case Constant.TOP_URL:
          webView.loadUrl(Constant.DAILY_URL);
          break;
        case Constant.DAILY_URL:
          webView.loadUrl(Constant.LOAD_SCHDAY_SCRIPT);
          break;
      }
    }
  };

  @Override
  public void onCreate() {
    super.onCreate();
    mContext  = getApplicationContext();
    mDebug    = new Debug(mContext, getClass().getSimpleName());
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    mDebug.shortToast("onStart");

    initWebView();
    return super.onStartCommand(intent, flags, startId);
  }

  private void initWebView() {
    WebView mWebView = new WebView(mContext);
    mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    mWebView.getSettings().setLoadsImagesAutomatically(false);
    mWebView.getSettings().setJavaScriptEnabled(true);
    mWebView.addJavascriptInterface(this, "reminder");
    mWebView.setWebViewClient(mViewClient);
    mWebView.loadUrl(Constant.AUTO_LOGIN_HTML);
  }

  public void onLoadSCHDAY(String...schedules) {
    mDebug.shortToast("onLoadSCDAY");
    NotificationUtil notification = new NotificationUtil(mContext);
    notification.showNotifications(schedules);
    stopSelf();
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
}
