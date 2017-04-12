package com.example.wgt1986.myapplication.materialdialogs;

import android.app.Application;
import com.example.wgt1986.myapplication.BuildConfig;

/**
 * @author Aidan Follestad (afollestad)
 */
public class App extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    if (BuildConfig.DEBUG) {
    }
  }
}
