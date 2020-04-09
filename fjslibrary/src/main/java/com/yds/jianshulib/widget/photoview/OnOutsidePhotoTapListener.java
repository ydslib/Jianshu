package com.yds.jianshulib.widget.photoview;

/**
 * Created by yds
 * on 2020/4/8.
 */
import android.widget.ImageView;

/**
 * Callback when the user tapped outside of the photo
 */
public interface OnOutsidePhotoTapListener {

    /**
     * The outside of the photo has been tapped
     */
    void onOutsidePhotoTap(ImageView imageView);
}
