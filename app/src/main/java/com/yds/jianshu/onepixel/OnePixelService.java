package com.yds.jianshu.onepixel;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.yds.jianshu.manager.OnePixelReceiverManager;
import com.yds.jianshu.manager.ReceiverManager;

public class OnePixelService extends Service {
    private ReceiverManager manager;
    public OnePixelService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        manager = new OnePixelReceiverManager();
        manager.register(this);//注册广播接收者
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        manager.unregister(this);
    }
}
