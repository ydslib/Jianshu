package com.example.myapplication;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.junit.Test;

/**
 * Created by yds
 * on 2019/8/10.
 */
public class HandlerTest {
    @Test
    public void test(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                    }
                };
                Handler handler1 = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                    }
                };
                Looper.loop();
                handler.sendMessage()
            }
        }).start();
    }
}
