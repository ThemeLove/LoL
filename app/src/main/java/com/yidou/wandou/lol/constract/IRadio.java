package com.yidou.wandou.lol.constract;

import com.yidou.wandou.lol.model.Radio;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */

public interface IRadio
{

    public interface Views
    {
        void showRadioDatas(List<Radio> mList);

        void showErrorDatas(String msg);
    }

    public interface Presenter
    {
        void loadingRadioNetDatas();

        void loadingRadioCacheDatas(String msg);
    }
}
