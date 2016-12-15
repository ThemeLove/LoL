package com.yidou.wandou.lol.services;

import com.yidou.wandou.lol.model.Moment;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/15.
 */

public interface MomentService
{
    /**
     * 动态接口
     * http://box.dwstatic.com/apiNewsList.php?action=topic&topicId=164
     * @return
     */
    @GET("apiNewsList.php?action=topic&topicId=164")
    Observable<Moment> getMomentservable();
}
