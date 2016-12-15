package com.yidou.wandou.lol.constract;

import com.yidou.wandou.lol.model.HeadLine;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */

public interface IHeadLine
{
    public interface Views
    {
        void showInfo(List<HeadLine.DataBean> mList);//显示正确的信息

        void showError(String msg);//显示错误的信息
    }

    public interface presenter
    {
        void loadingDataFromNet(int p);

        void loadingDataFromCache(String p);
    }

    public interface newspresenter
    {
        void loadingDataFromNet(String s,int p);

        void loadingDataFromCache(String p);
    }
}
