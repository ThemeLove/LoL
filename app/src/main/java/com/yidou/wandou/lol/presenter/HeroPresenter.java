package com.yidou.wandou.lol.presenter;

import com.yidou.wandou.lol.base.BasePresenterImpl;
import com.yidou.wandou.lol.constract.IHeros;
import com.yidou.wandou.lol.model.AllHeros;
import com.yidou.wandou.lol.model.FreeHeros;
import com.yidou.wandou.lol.utils.ApiManager;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/13.
 */

public class HeroPresenter extends BasePresenterImpl implements IHeros.FreePresenter,IHeros.AllPresenter
{
    IHeros.AllViews mAllViews;
    IHeros.FreeViews mFreeViews;

    public HeroPresenter(IHeros.AllViews allViews, IHeros.FreeViews freeViews)
    {
        mAllViews = allViews;
        mFreeViews = freeViews;
    }

    @Override
    public void loadingAllHeroDatas()
    {
        Subscription s = ApiManager.getInstance()
                .getHeroService().getAllServices()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AllHeros>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        mAllViews.showAllError(e.getMessage());
                    }

                    @Override
                    public void onNext(AllHeros heros)
                    {
                        mAllViews.showAllHeros(heros.getAll());
                    }
                });
        addSubcrible(s);
    }

    @Override
    public void loadingFreeHeroDatas()
    {
        Subscription s = ApiManager.getInstance()
                .getHeroService().getFreeServices()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FreeHeros>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        mFreeViews.showFreeError(e.getMessage());
                    }

                    @Override
                    public void onNext(FreeHeros heros)
                    {
                        mFreeViews.showFreeHeros(heros.getFree());
                    }
                });
        addSubcrible(s);
    }
}
