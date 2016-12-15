package com.yidou.wandou.lol.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.yidou.wandou.lol.Config;
import com.yidou.wandou.lol.base.BasePresenterImpl;
import com.yidou.wandou.lol.constract.IHeadLine;
import com.yidou.wandou.lol.model.HeadLine;
import com.yidou.wandou.lol.utils.ApiManager;
import com.yidou.wandou.lol.utils.CacheUtil;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/7.
 */

public class HLinePresenterImpl extends BasePresenterImpl implements IHeadLine.presenter
{
    private IHeadLine.Views mViews;
    private Gson mGson;
    private CacheUtil mUtil;

    public HLinePresenterImpl(Context context,IHeadLine.Views views)
    {
        mViews = views;
        mGson = new Gson();
        mUtil = CacheUtil.get(context);
    }

    @Override
    public void loadingDataFromNet(int p)
    {
        Subscription s = ApiManager.getInstance().getHeaderService()
                .getVideoServiec(p)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HeadLine>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        mViews.showError("错的" + e.getMessage());
                    }

                    @Override
                    public void onNext(HeadLine line)
                    {
                        mViews.showInfo(line.getData());
                        mUtil.put(Config.HEAD_COOKIE, mGson.toJson(line));
                    }
                });
        addSubcrible(s);
    }

    @Override
    public void loadingDataFromCache(String p)
    {
        if (mUtil.getAsJSONObject(p) != null)
        {
            HeadLine line = mGson.fromJson(mUtil.getAsJSONObject(p).toString(), HeadLine.class);
            mViews.showInfo(line.getData());
        }
    }
}
