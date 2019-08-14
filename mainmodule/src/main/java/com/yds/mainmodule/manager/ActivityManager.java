package com.yds.mainmodule.manager;

import android.app.Activity;
import android.content.Context;

import com.yds.jianshu.itf.Manager;
import com.yds.jianshu.utils.ObjectUtil;

/**
 * Created by yds
 * on 2019/8/2.
 */
public abstract class ActivityManager implements Manager {
    public abstract void startActivity(Context context);
    public void finishActivity(Activity activity){
        ObjectUtil.requireNonNull(activity,"activity is null");
        activity.finish();
    }
}
