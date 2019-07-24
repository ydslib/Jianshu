package com.yds.jianshu.mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapplication.R;
import com.yds.jianshu.onepixel.OnePixelManager;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "[MainActivity]";
    OnePixelManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new OnePixelManager();
        manager.registerOnePixelReceiver(this);//注册广播接收者
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterOnePixelReceiver(this);//Activity退出时解注册
    }
}
