package com.yidou.wandou.lol.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.yidou.wandou.lol.Config;
import com.yidou.wandou.lol.base.BasePresenterImpl;
import com.yidou.wandou.lol.constract.IMomoent;
import com.yidou.wandou.lol.model.Moment;
import com.yidou.wandou.lol.utils.ApiManager;
import com.yidou.wandou.lol.utils.CacheUtil;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/15.
 */

public class MomentPresenter extends BasePresenterImpl implements IMomoent.Presenter
{
    private IMomoent.Views mViews;
    private CacheUtil mUtil;
    private Gson mGson;

    public MomentPresenter(Context context, IMomoent.Views views)
    {
        mViews = views;
        mUtil = CacheUtil.get(context);
        mGson = new Gson();
    }

    @Override

    public void loadingNetData()
    {
        Subscription s = ApiManager.getInstance().getMomentService()
                .getMomentservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Moment>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        mViews.showErrorMsg(e.getMessage());
                    }

                    @Override
                    public void onNext(Moment moment)
                    {
                        mViews.showMomentDatas(moment.getData());
                        mUtil.put(Config.MOMENT_CACHE, moment.toString());
                    }
                });
        addSubcrible(s);
    }

    @Override
    public void loadingCacheData(String msg)
    {
        if (mUtil.getAsString(msg) != null)
        {
            Moment moment = mGson.fromJson(mUtil.getAsString(msg), Moment.class);
            mViews.showMomentDatas(moment.getData());
        }
    }
}
