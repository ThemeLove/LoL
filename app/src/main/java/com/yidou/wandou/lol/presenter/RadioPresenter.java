package com.yidou.wandou.lol.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yidou.wandou.lol.Config;
import com.yidou.wandou.lol.base.BasePresenterImpl;
import com.yidou.wandou.lol.constract.IRadio;
import com.yidou.wandou.lol.model.Radio;
import com.yidou.wandou.lol.model.Videos;
import com.yidou.wandou.lol.utils.ApiManager;
import com.yidou.wandou.lol.utils.CacheUtil;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/15.
 */

public class RadioPresenter extends BasePresenterImpl implements IRadio.Presenter
{
    IRadio.Views mViews;
    Gson mGson;
    CacheUtil mUtil;

    public RadioPresenter(Context context,IRadio.Views views)
    {
        mViews = views;
        mGson = new Gson();
        mUtil = CacheUtil.get(context);
    }

    //网络获取数据
    @Override
    public void loadingRadioNetDatas()
    {
        Subscription s = ApiManager.getInstance()
                .getRadioService()
                .getRadioService()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Radio>>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        mViews.showErrorDatas(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Radio> radios)
                    {
                        mViews.showRadioDatas(radios);
                        mUtil.put(Config.RADIO_CACHE, radios.toString());
                    }
                });
        addSubcrible(s);
    }

    //缓存中获取数据
    @Override
    public void loadingRadioCacheDatas(String msg)
    {
        if (mUtil.getAsString(msg) != null)
        {
            List<Radio> radios = mGson.fromJson(mUtil.getAsString(msg), new TypeToken<List<Radio>>()
            {
            }.getType());
            mViews.showRadioDatas(radios);
        }
    }
}
