package com.yds.jianshulib.base.mvp;

/**
 * Created by yds
 * on 2019/8/29.
 */
public interface IBasePresenter<V extends IBaseView> {
    /**
     * used to attach the real Presenter
     * @param view
     */
    void attach(V view);

    /**
     * used to detach the real Presenter
     */
    void detach();
}
