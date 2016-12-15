package com.yidou.wandou.lol.constract;

import com.yidou.wandou.lol.model.Moment;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */

public interface IMomoent
{
    public interface Views
    {
        void showMomentDatas(List<Moment.DataBeanX> list);

        void showErrorMsg(String msg);
    }

    public interface Presenter
    {
        void loadingNetData();

        void loadingCacheData(String msg);
    }
}
