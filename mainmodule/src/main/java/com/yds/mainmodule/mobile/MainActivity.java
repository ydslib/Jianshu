package com.yds.mainmodule.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yds.mainmodule.R;
import com.yds.mainmodule.onepixel.OnePixelService;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "[MainActivity]";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }
    private void initData(){
        startOnePixelService();
    }

    private void startOnePixelService(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, OnePixelService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
