package com.yidou.wandou.lol.services;

import com.yidou.wandou.lol.model.Videos;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/14.
 */

public interface VideoService
{
    /**
     * 视频接口地址
     * http://box.dwstatic.com/apiVideoesNormalDuowan.php?src=duowan&action=c&sk=&sn=&pn=
     * @return
     */
    @GET("apiVideoesNormalDuowan.php?src=duowan&action=c&sk=&sn=&pn=")
    Observable<List<Videos>> getVideoService();
}
