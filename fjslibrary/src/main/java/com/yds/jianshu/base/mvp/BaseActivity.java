package com.yds.jianshu.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.yds.jianshu.utils.statusbar.StatusBarUtil;

/**
 * Created by yds
 * on 2019/8/3.
 */
public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView{
    protected P mPresenter;
    protected abstract void initLayout(@Nullable Bundle savedInstanceState);
    protected abstract void initViews();
    protected abstract void initEvents();

    protected abstract P setPresenter();

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initLayout(saveInstanceState);
        mPresenter = setPresenter();
        if(mPresenter!=null){
            mPresenter.attach(this);
        }
        initViews();
        initEvents();
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this,true)){
            StatusBarUtil.setStatusBarColor(this,0x55000000);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
        mPresenter = null;
    }

    @Override
    public Context getContext() {
        return this;
    }
}
