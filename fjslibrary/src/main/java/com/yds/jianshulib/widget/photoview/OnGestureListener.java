package com.yds.jianshulib.widget.photoview;

/**
 * Created by yds
 * on 2020/4/8.
 */
interface OnGestureListener {
    void onDrag(float dx, float dy);

    void onFling(float startX, float startY, float velocityX, float velocityY);

    void onScale(float scaleFactor, float focusX, float focusY);
}
