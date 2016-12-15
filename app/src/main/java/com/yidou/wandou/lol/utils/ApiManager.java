package com.yidou.wandou.lol.utils;

import com.yidou.wandou.lol.Config;
import com.yidou.wandou.lol.model.HeadLine;
import com.yidou.wandou.lol.services.HLineService;
import com.yidou.wandou.lol.services.HeroService;
import com.yidou.wandou.lol.services.MomentService;
import com.yidou.wandou.lol.services.PictureService;
import com.yidou.wandou.lol.services.RadioService;
import com.yidou.wandou.lol.services.VideoService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/12/7.
 */

public class ApiManager
{
    private static ApiManager instances;

    //构造器私有化
    private ApiManager()
    {

    }

    /**
     * 单例模式
     *
     * @return
     */
    public static ApiManager getInstance()
    {
        if (instances == null)
        {
            synchronized (ApiManager.class)
            {
                if (instances == null)
                {
                    instances = new ApiManager();
                }
            }
        }
        return instances;
    }

    //头条的新闻
    public HLineService mService;
    public HLineService getHeaderService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Config.HEADERLINE)
                .build();
        if (retrofit != null)
        {
            mService = retrofit.create(HLineService.class);
        }
        return mService;
    }

    /**
     * 美图新闻
     */
    public PictureService mPictureService;
    public PictureService getPictureService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Config.HEADERLINE)
                .build();
        if (retrofit != null)
        {
            mPictureService = retrofit.create(PictureService.class);
        }
        return mPictureService;
    }

    /**
     * 英雄资料
     */
    public HeroService mHeroService;
    public HeroService getHeroService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Config.HERO)
                .build();
        if (retrofit != null)
        {
            mHeroService = retrofit.create(HeroService.class);
        }
        return mHeroService;
    }

    /**
     * 视频
     */
    public VideoService mVideoService;
    public VideoService getVideoService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Config.VIDEO)
                .build();
        if (retrofit != null)
        {
            mVideoService = retrofit.create(VideoService.class);
        }
        return mVideoService;
    }

    /**
     * 直播
     */
    public RadioService mRadioService;
    public RadioService getRadioService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Config.RADIO)
                .build();
        if (retrofit != null)
        {
            mRadioService = retrofit.create(RadioService.class);
        }
        return mRadioService;
    }

    /**
     * 动态接口
     */
    public MomentService mMomentService;
    public MomentService getMomentService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Config.HEADERLINE)
                .build();
        if (retrofit != null)
        {
            mMomentService = retrofit.create(MomentService.class);
        }
        return mMomentService;
    }
}
