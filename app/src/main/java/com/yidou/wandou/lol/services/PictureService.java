package com.yidou.wandou.lol.services;

import com.yidou.wandou.lol.model.Picture;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/11.
 */

public interface PictureService
{
    /**
     * http://box.dwstatic.com/apiAlbum.php?action=l&albumsTag=jiongTu&p=1
     * @return 美图接口
     */
    @GET("apiAlbum.php?action=l")
    Observable<Picture> getPictures(@Query("albumsTag")String picture, @Query("p")int p);
}
