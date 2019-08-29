package com.yds.mainmodule.mobile.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yds.jianshu.base.mvp.BaseActivity;
import com.yds.mainmodule.R;
import com.yds.mainmodule.mobile.contract.MainContract;
import com.yds.mainmodule.mobile.presenters.MainPresenter;
import com.yds.mainmodule.onepixel.OnePixelService;

public class MainActivity extends BaseActivity<MainContract.IMainPresenter> implements MainContract.IMainView{
    private static final String TAG = "[MainActivity]";

    @Override
    protected void initLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        startOnePixelService();
    }

    @Override
    protected MainContract.IMainPresenter setPresenter() {
        return new MainPresenter();
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
