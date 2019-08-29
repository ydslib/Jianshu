package com.yds.jianshu.base.mvp;

/**
 * Created by yds
 * on 2019/8/29.
 */
public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter{
    protected V mView;
    @SuppressWarnings("unchecked")
    @Override
    public void attach(IBaseView view) {
        mView = (V) view;
    }

    @Override
    public void detach() {
        mView = null;
    }
}
