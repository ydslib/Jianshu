package com.yds.jianshu.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.yds.jianshu.onepixel.OnePixelService;
import com.yds.jianshu.utils.FToast;
import com.yds.jianshu.utils.widget.constance.CommonImageType;

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
