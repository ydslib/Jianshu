package com.yds.jianshu.base.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by yds
 * on 2019/8/3.
 */
public abstract class BaseActivity<P extends IBasePresenter> extends Activity implements IBaseView{
    protected P mPresenter;
    protected abstract void initLayout(@Nullable Bundle savedInstanceState);
    protected abstract void initViews();
    protected abstract void initEvents();

    protected abstract P setPresenter();

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        initLayout(saveInstanceState);
        mPresenter = setPresenter();
        if(mPresenter!=null){
            mPresenter.attach(this);
        }
        initViews();
        initEvents();

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
