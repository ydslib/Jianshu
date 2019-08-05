package com.yds.jianshu.onepixel;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.yds.jianshu.manager.OnePixelActivityManager;

public class OnePixelActivity extends Activity{
    private static final String TAG = "[OnePixelActivity]";
    private OnePixelActivityManager manager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowAttributes();
        initData();

        Log.e(TAG,"onCreate");
    }
    private void initData(){
        manager = new OnePixelActivityManager();
        manager.registerActivityReference(this);//将引用传给OnePixelActivityManager
    }
    private void setWindowAttributes(){
        Window window = getWindow();
        window.setGravity(Gravity.LEFT|Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        params.height = 1;
        params.width = 1;
        window.setAttributes(params);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }
}
