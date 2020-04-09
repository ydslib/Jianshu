package com.yds.jianshulib.base.mvp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Window;

import com.yds.jianshulib.utils.statusbar.StatusBarUtil;


/**
 * Created by yds
 * on 2019/8/3.
 */
public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {
    protected P mPresenter;

    protected abstract void initLayout(@Nullable Bundle savedInstanceState);

    protected abstract void initViews();

    protected abstract void initEvents();

    protected abstract P setPresenter();

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initLayout(saveInstanceState);
        mPresenter = setPresenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        initViews();
        initEvents();
//        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
//        StatusBarUtil.setTranslucentStatus(this);
//        StatusBarUtil.setRootView(this);
        StatusBarUtil.setStatusBarColor(this, 0xffffff);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
//        if (!StatusBarUtil.setStatusBarDarkTheme(this,true)){
//            StatusBarUtil.setStatusBarColor(this,0x55000000);
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
            mPresenter = null;
        }
    }

    @Override
    public Context getContext() {
        return this;
    }
}
