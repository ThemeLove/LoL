package com.yidou.wandou.lol.services;

import com.yidou.wandou.lol.model.Radio;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/15.
 */

public interface RadioService
{
    /**
     * 视频直播的接口
     * http://webpd.mbox.duowan.com/index.php?r=live/getLiveData
     * @return
     */
    @GET("index.php?r=live/getLiveData")
    Observable<List<Radio>> getRadioService();

}
