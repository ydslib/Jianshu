package com.yds.jianshulib.utils.widget.constance;


import com.yds.jianshulib.R;

/**
 * Created by yds
 * on 2019/8/3.
 */
public enum CommonImageType {
    SUCCESS,//成功
    FAIL,//失败
    WARN,//警告
    ERROR,//错误
    DEFAULT,//默认
    YES,//是
    NO;//否

    public static int getCommonImageResID(CommonImageType type) {
        switch (type) {
            case FAIL:
            case WARN:
                return R.drawable.f_toast_alert;
            case SUCCESS:
            case YES:
                return R.drawable.f_toast_yes;
            case NO:
            case ERROR:
                return R.drawable.f_toast_no;
            default:
                return R.drawable.f_toast_alert;

        }
    }

}
