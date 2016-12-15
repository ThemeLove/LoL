package com.yidou.wandou.lol.constract;

import com.yidou.wandou.lol.model.AllHeros;
import com.yidou.wandou.lol.model.FreeHeros;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */

public interface IHeros
{
    //周免英雄
    public interface FreeViews
    {
        void showFreeHeros(List<FreeHeros.FreeBean> mList);

        void showFreeError(String s);
    }

    public interface FreePresenter
    {
        void loadingFreeHeroDatas();
    }

    //全部英雄
    public interface AllViews
    {
        void showAllHeros(List<AllHeros.AllBean> mList);

        void showAllError(String s);
    }

    public interface AllPresenter
    {
        void loadingAllHeroDatas();

    }
}
