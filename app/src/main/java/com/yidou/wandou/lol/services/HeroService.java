package com.yidou.wandou.lol.services;

import com.yidou.wandou.lol.model.AllHeros;
import com.yidou.wandou.lol.model.FreeHeros;

import java.util.Observer;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/13.
 */

public interface HeroService
{
    /**
     * 周免英雄接口
     * http://lolbox.duowan.com/phone/apiHeroes.php?type=free
     * @return
     */
    @GET("apiHeroes.php?type=free")
    Observable<FreeHeros> getFreeServices();

    /**
     * 所有英雄的接口
     * http://lolbox.duowan.com/phone/apiHeroes.php?type=all
     * @return
     */
    @GET("apiHeroes.php?type=all")
    Observable<AllHeros> getAllServices();
}
