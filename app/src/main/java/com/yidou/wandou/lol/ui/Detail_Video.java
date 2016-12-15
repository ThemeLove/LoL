package com.yidou.wandou.lol.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yidou.wandou.lol.Config;
import com.yidou.wandou.lol.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Detail_Video extends AppCompatActivity
{

    @BindView(R.id.detail_toolbar)
    Toolbar mDetailToolbar;
    @BindView(R.id.detail_web)
    WebView mDetailWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__video);
        ButterKnife.bind(this);
        initToolBar();

        initWebView();
    }

    private void initToolBar()
    {
        setSupportActionBar(mDetailToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initWebView()
    {
        Intent it = getIntent();
        String url = it.getStringExtra(Config.TAG);

        mDetailWeb.getSettings().setJavaScriptEnabled(true);//支持网页上button按钮的点击
        mDetailWeb.getSettings().setPluginState(WebSettings.PluginState.ON);

        mDetailWeb.setWebChromeClient(new WebChromeClient());
        mDetailWeb.loadUrl(url);

        setTitle(it.getStringExtra(Config.TITLE));


        mDetailWeb.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mDetailWeb.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mDetailWeb.onPause();
    }
}
