package com.yidou.wandou.lol.presenter;

import android.util.Log;

import com.yidou.wandou.lol.base.BasePresenterImpl;
import com.yidou.wandou.lol.constract.IPictrues;
import com.yidou.wandou.lol.model.Picture;
import com.yidou.wandou.lol.utils.ApiManager;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/11.
 */

public class PicturePresenter extends BasePresenterImpl implements IPictrues.presenter
{
    private IPictrues.View mView;

    public PicturePresenter(IPictrues.View view)
    {
        mView = view;
    }

    @Override
    public void loadingDatasFromNet(String msg, int p)
    {
        Subscription s = ApiManager.getInstance().getPictureService()
                .getPictures(msg, p)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Picture>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Picture picture)
                    {
                        mView.showData(picture.getData());
                    }
                });
        addSubcrible(s);
    }
}
