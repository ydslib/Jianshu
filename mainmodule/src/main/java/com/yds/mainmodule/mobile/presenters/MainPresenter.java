package com.yds.mainmodule.mobile.presenters;

import com.yds.jianshu.base.mvp.BasePresenter;
import com.yds.jianshu.base.mvp.IBaseView;
import com.yds.mainmodule.mobile.contract.MainContract;

/**
 * Created by yds
 * on 2019/8/29.
 */
public class MainPresenter extends BasePresenter<MainContract.IMainView> implements MainContract.IMainPresenter {
    private MainContract.IMainModel mModel;
    @Override
    public void attach(IBaseView view) {
        super.attach(view);
    }

    @Override
    public void detach() {
        super.detach();
    }
}
