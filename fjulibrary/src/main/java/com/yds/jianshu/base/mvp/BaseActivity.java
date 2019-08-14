package com.yds.jianshu.base.mvp;

import android.app.Activity;

/**
 * Created by yds
 * on 2019/8/3.
 */
public abstract class BaseActivity<T> extends Activity implements IBaseView<T>{
    @Override
    public void makeToast(T text) {

    }
}
