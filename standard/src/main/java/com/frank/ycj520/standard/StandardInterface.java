package com.frank.ycj520.standard;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

public interface StandardInterface {
    public void onCreate(Bundle savedInstanceState);
    public void onStart();
    public void onResume();
    public void onPause();
    public void onStop();
    public void onDestroy();
    public void onSaveInstanceState(Bundle outState);
    public boolean onTouchEvent(MotionEvent event);
    public void onBackPressed();
    public void attach(Activity activity);

}
