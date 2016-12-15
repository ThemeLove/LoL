package com.yidou.wandou.lol.constract;

import com.yidou.wandou.lol.model.Videos;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public interface IVideos
{
    public interface Views
    {
        void showFenleiDatas(List<Videos> videos);

        void showError(String msg);
    }

    public interface presenter
    {
        void loadingFenleiNetDatas();

        void loadingFenleiCacheDatas(String p);
    }
}
