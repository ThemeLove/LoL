package com.yidou.wandou.lol.constract;

import com.yidou.wandou.lol.model.Picture;

import java.util.List;

/**
 * Created by Administrator on 2016/12/11.
 */

public interface IPictrues
{
    public interface View
    {
        void showData(List<Picture.DataBean> mList);

        void showError(String msg);
    }

    public interface presenter
    {
        void loadingDatasFromNet(String s, int p);
    }

}
