package com.yds.jianshulib.widget.photoview;

import android.graphics.RectF;

/**
 * Created by yds
 * on 2020/4/8.
 */
public interface OnMatrixChangedListener {
    /**
     * Callback for when the Matrix displaying the Drawable has changed. This could be because
     * the View's bounds have changed, or the user has zoomed.
     *
     * @param rect - Rectangle displaying the Drawable's new bounds.
     */
    void onMatrixChanged(RectF rect);
}
