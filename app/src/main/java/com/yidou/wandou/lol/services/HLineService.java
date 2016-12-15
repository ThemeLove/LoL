package com.yidou.wandou.lol.services;

import com.yidou.wandou.lol.model.HeadLine;
import com.yidou.wandou.lol.model.Picture;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/7.
 */

public interface HLineService
{
    /**
     * http://box.dwstatic.com/apiNewsList.php?action=l&newsTag=headlineNews&p=1
     * 新闻接口
     */
    @GET("apiNewsList.php?action=l")
    Observable<HeadLine> getNewService(@Query("newsTag")String newsTag,@Query("p")int p);

    //视屏接口
    @GET("apiNewsList.php?action=l&newsTag=newsVideo")
    Observable<HeadLine> getVideoServiec(@Query("p") int p);




}
