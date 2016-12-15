package com.yidou.wandou.lol.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.yidou.wandou.lol.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaiKeActivity extends AppCompatActivity
{

    @BindView(R.id.ac_baike_toolbar)
    Toolbar mAcBaikeToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_ke);
        ButterKnife.bind(this);
        initToolBar();
    }

    private void initToolBar()
    {
        setSupportActionBar(mAcBaikeToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle("游戏百科");
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
}