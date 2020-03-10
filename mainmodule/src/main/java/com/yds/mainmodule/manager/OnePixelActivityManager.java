package com.yds.mainmodule.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.yds.mainmodule.onepixel.OnePixelActivity;
import com.yds.jianshulib.utils.ObjectUtil;

import java.lang.ref.WeakReference;

/**
 * Created by yds
 * on 2019/8/2.
 */
public class OnePixelActivityManager extends ActivityManager {
    private WeakReference<Activity> mActivity;

    /**
     * 将{@link OnePixelActivity}的引用保存起来，用于后面监测到屏幕亮时关闭{@link OnePixelActivity}，
     * 使用弱引用，是因为如果O{@link OnePixelActivity}被回收了，那么就不需要再保存该对象的引用用于
     * finish了
     * @param activity
     */
    public void registerActivityReference(Activity activity){
        ObjectUtil.requireNonNull(activity,"activity is null");
        mActivity = new WeakReference<Activity>(activity);
    }
    @Override
    public void startActivity(Context context) {
        ObjectUtil.requireNonNull(context,"context is null");
        Intent intent = new Intent();
        intent.setClass(context, OnePixelActivity.class);
        context.startActivity(intent);
    }
    public void finishOnePixelActivity(){
        ObjectUtil.requireNonNull(mActivity,"mActivity is null");
        Activity activity = mActivity.get();
        ObjectUtil.requireNonNull(activity,"activity is null");
        finishActivity(activity);
        mActivity = null;
    }
}
