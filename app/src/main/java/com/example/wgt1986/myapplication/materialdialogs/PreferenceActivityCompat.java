package com.example.wgt1986.myapplication.materialdialogs;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.wgt1986.myapplication.R;

@TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
public class PreferenceActivityCompat extends android.preference.PreferenceActivity {

  @SuppressWarnings("deprecation")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.preferences);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}