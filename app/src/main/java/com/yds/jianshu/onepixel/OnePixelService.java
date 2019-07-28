package com.yds.jianshu.onepixel;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class OnePixelService extends Service {
    OnePixelManager manager;
    public OnePixelService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        manager = new OnePixelManager();
        manager.registerOnePixelReceiver(this);//注册广播接收者
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        manager.unregisterOnePixelReceiver(this);
    }
}
