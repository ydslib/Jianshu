package com.yds.mainmodule.manager;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.yds.mainmodule.onepixel.OnePixelReceiver;
import com.yds.jianshulib.utils.ObjectUtil;

/**
 * Created by yds
 * on 2019/8/2.
 */
public class OnePixelReceiverManager extends ReceiverManager {
    public OnePixelReceiverManager(){
        createReceiver(new OnePixelReceiver());
    }

    @Override
    public void register(Context context) {
        ObjectUtil.requireNonNull(context,"context is null");
        ObjectUtil.requireNonNull(receiver,"receiver is null");
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        context.registerReceiver(receiver,filter);
    }

    @Override
    public void unregister(Context context) {
        ObjectUtil.requireNonNull(context,"context is null");
        ObjectUtil.requireNonNull(receiver,"receiver is null");
        context.unregisterReceiver(receiver);
    }
}
