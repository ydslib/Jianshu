package com.yds.jianshu.onepixel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yds.jianshu.manager.OnePixelActivityManager;


public class OnePixelReceiver extends BroadcastReceiver {
    private static final String TAG = "[OnePixelReceiver]";
    private OnePixelActivityManager manager;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        manager = new OnePixelActivityManager();
        if (Intent.ACTION_SCREEN_ON.equals(action)){//如果亮屏，则关闭1像素Activity
            manager.finishOnePixelActivity();
        }else if(Intent.ACTION_SCREEN_OFF.equals(action)){//如果息屏，则开启1像素Activity
            manager.startActivity(context);
        }
    }
}
