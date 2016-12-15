package com.yidou.wandou.lol;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2016/12/7.
 */

public class App extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        Fresco.initialize(this);
    }
}
