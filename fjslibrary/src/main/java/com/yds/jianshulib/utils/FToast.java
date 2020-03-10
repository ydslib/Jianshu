package com.yds.jianshulib.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yds.jianshulib.R;
import com.yds.jianshulib.utils.widget.constance.CommonImageType;


/**
 * Created by yds
 * on 2019/8/3.
 */
public class FToast {
    /**
     * @param context
     * @param text 提示内容
     * @return
     */
    public static Toast makeText(Context context, String text) {
        return makeText(context, text, Toast.LENGTH_SHORT);
    }

    /**
     *
     * @param context
     * @param text 提示内容
     * @param type 图片显示类型{@link CommonImageType}
     * @return
     */
    public static Toast makeText(Context context, String text,CommonImageType type) {
        return makeText(context, text, Toast.LENGTH_SHORT,type);
    }

    /**
     *
     * @param context
     * @param text 提示内容
     * @param duration 显示时长，对应 Toast.LEHGTH_SHORT和Toast.LENGTH_LONG
     * @return
     */
    public static Toast makeText(Context context, String text, int duration) {
        return makeText(context, text, duration, CommonImageType.DEFAULT);
    }


    /**
     *
     * @param context
     * @param text 提示内容
     * @param duration 显示时长，对应 Toast.LEHGTH_SHORT和Toast.LENGTH_LONG
     * @param type 图片显示类型{@link CommonImageType}
     * @return
     */
    public static Toast makeText(Context context, String text, int duration, CommonImageType type) {
        int imageViewResID = CommonImageType.getCommonImageResID(type);

        return makeText(context, text, duration, imageViewResID);
    }

    /**
     *
     * @param context
     * @param text 提示内容
     * @param duration 显示时长，对应 Toast.LEHGTH_SHORT和Toast.LENGTH_LONG
     * @param imageViewResID 图片资源ID
     * @return
     */
    public static Toast makeText(Context context, String text, int duration, int imageViewResID) {
        ObjectUtil.requireNonNull(context, "context is null");
        if (text == null) {
            text = "提示信息为空";
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.f_toast, null);
        TextView mTextView = mView.findViewById(R.id.toast_msg);
        ImageView mImageView = mView.findViewById(R.id.toast_iv);
        if (imageViewResID > 0){
            mImageView.setImageResource(imageViewResID);
        }
        mTextView.setText(text);
        Toast mToast = new Toast(context);
        mToast.setGravity(Gravity.CENTER,0,0);
        mToast.setDuration(duration);
        mToast.setView(mView);

        return mToast;

    }


}
