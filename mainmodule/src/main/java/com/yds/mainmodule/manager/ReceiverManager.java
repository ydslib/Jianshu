package com.yds.mainmodule.manager;

import android.content.BroadcastReceiver;
import android.content.Context;

import com.yds.jianshu.itf.Manager;

/**
 * Created by yds
 * on 2019/8/2.
 */
public abstract class ReceiverManager implements Manager {
    public BroadcastReceiver receiver;
    public abstract void register(Context context);
    public abstract void unregister(Context context);

    public void createReceiver(BroadcastReceiver receiver){
        this.receiver = receiver;
    }

}
