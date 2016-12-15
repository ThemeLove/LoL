package com.yidou.wandou.lol.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.yidou.wandou.lol.Config;
import com.yidou.wandou.lol.base.BasePresenterImpl;
import com.yidou.wandou.lol.constract.IVideos;
import com.yidou.wandou.lol.model.Videos;
import com.yidou.wandou.lol.utils.ApiManager;
import com.yidou.wandou.lol.utils.CacheUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/14.
 */

public class VideoPresenter extends BasePresenterImpl implements IVideos.presenter
{
    IVideos.Views mViews;
    CacheUtil mUtil;
    Gson mGson;

    public VideoPresenter(Context context,IVideos.Views views)
    {
        mViews = views;
        mUtil = CacheUtil.get(context);
        mGson = new Gson();
    }

    @Override
    public void loadingFenleiNetDatas()
    {
        Subscription s = ApiManager.getInstance()
                .getVideoService().getVideoService()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Videos>>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        mViews.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Videos> videoses)
                    {
                        mViews.showFenleiDatas(videoses);
                        mViews.showError(videoses.toString());
                        mUtil.put(Config.VIDEO_CACHE,videoses.toArray());
                    }
                });
        addSubcrible(s);
    }

    @Override
    public void loadingFenleiCacheDatas(String p)
    {
        if (mUtil.getAsString(p) != null)
        {
            List<Videos> mList = mGson.fromJson(mUtil.getAsString(p), new TypeToken<List<Videos>>()
            {
            }.getType());
            mViews.showFenleiDatas(mList);
        }
    }
}
