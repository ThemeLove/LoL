package com.yidou.wandou.lol.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.yidou.wandou.lol.R;
import com.yidou.wandou.lol.constract.IHeadLine;
import com.yidou.wandou.lol.constract.IPictrues;
import com.yidou.wandou.lol.model.HeadLine;
import com.yidou.wandou.lol.model.Picture;
import com.yidou.wandou.lol.presenter.HLinePresenterImpl;
import com.yidou.wandou.lol.presenter.NewsPresenter;
import com.yidou.wandou.lol.presenter.PicturePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity implements IHeadLine.Views,IPictrues.View
{
    @BindView(R.id.news_toolbar)
    Toolbar mNewsToolbar;
    @BindView(R.id.news_recycler)
    RecyclerView mNewsRecycler;
    @BindView(R.id.news_refresh)
    SwipeRefreshLayout mNewsRefresh;
    @BindView(R.id.content_news)
    RelativeLayout mContentNews;
    @BindView(R.id.news_table)
    TabLayout mNewsTable;

    private String[] titles =
            {
                    "头条", "视频", "赛事","靓照","囧图","壁纸"
            };
    private String[] mStrings =
            {
                    "headlineNews","newsVideo","upgradenews",
                    "beautifulWoman","jiongTu","wallpaper"
            };
    private NewsPresenter mPresenter;
    private PicturePresenter mPicPresenter;
    private BaseRecyclerAdapter<HeadLine.DataBean> mAdapter;
    private Handler mHandler = new Handler();

    private BaseRecyclerAdapter<Picture.DataBean> mPicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        initToolBars();

        initVariables();
        initView(0);

        initTableLayout();
    }

    private void initView(final int p)
    {
        mNewsRefresh.setColorSchemeResources(R.color.colorYellow);
        if (p < 3)
        {
            mPresenter.loadingDataFromNet(mStrings[p], 1);
            mNewsRecycler.setLayoutManager(new LinearLayoutManager(this));
            mNewsRecycler.setAdapter(mAdapter);
            //上啦刷新
            mNewsRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
            {
                @Override
                public void onRefresh()
                {
                    mNewsRefresh.setRefreshing(true);
                    mHandler.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            mPresenter.loadingDataFromNet(mStrings[p],1);
                            Log.e("info", "___________________>" + p);
                        }
                    }, 1200);
                    mNewsRefresh.setRefreshing(false);
                }
            });
        }else
        {
            mPicPresenter.loadingDatasFromNet(mStrings[p],1);
            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            mNewsRecycler.setLayoutManager(manager);
            mNewsRecycler.setAdapter(mPicAdapter);
            mNewsRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
            {
                @Override
                public void onRefresh()
                {
                    mNewsRefresh.setRefreshing(true);
                    mHandler.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            mPicPresenter.loadingDatasFromNet(mStrings[p],1);
                            Log.e("info", "___________________>" + p);
                        }
                    }, 1200);
                    mNewsRefresh.setRefreshing(false);
                }
            });
        }



    }

    private void initVariables()
    {
        mPresenter = new NewsPresenter(this, this);
        mPicPresenter = new PicturePresenter(this);
        mAdapter = new BaseRecyclerAdapter<HeadLine.DataBean>(this, null, R.layout.ft_recycler_item)
        {
            @Override
            protected void convert(BaseViewHolder helper, HeadLine.DataBean item)
            {
                helper.setText(R.id.ft_home_titles, item.getTitle());
                String content = item.getContent();
                if (content.length() > 35)
                {
                    helper.setText(R.id.ft_home_contents, content.substring(0, 35) + "...");
                } else
                {
                    helper.setText(R.id.ft_home_contents, item.getContent());
                }
                helper.setText(R.id.ft_home_readCount, "阅读   " + item.getReadCount());
                SimpleDraweeView draweeView = (SimpleDraweeView) helper.getConvertView().findViewById(R.id.ft_home_simpleview);
                draweeView.setImageURI(item.getPhoto());
            }
        };
        mPicAdapter = new BaseRecyclerAdapter<Picture.DataBean>(this, null, R.layout.ac_pic_item)
        {
            @Override
            protected void convert(BaseViewHolder helper, Picture.DataBean item)
            {
                helper.setText(R.id.pic_text, item.getTitle());
                SimpleDraweeView draweeView = (SimpleDraweeView) helper.getConvertView().findViewById(R.id.pic_simple);
                draweeView.setImageURI(item.getCoverUrl());
            }
        };
    }

    private void initTableLayout()
    {
        setTable();
        mNewsTable.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                String s = tab.getText().toString();

                if ("头条".equals(s))
                {
                    initView(0);
                } else if ("视频".equals(s))
                {
                    initView(1);
                } else if ("赛事".equals(s))
                {
                    initView(2);
                } else if ("靓照".equals(s))
                {
                    initView(3);
                } else if ("囧图".equals(s))
                {
                    initView(4);
                } else if ("壁纸".equals(s))
                {
                    initView(5);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });
    }

    //tablayout设置标题
    private void setTable()
    {
        List<String> mTitles = new ArrayList<>();
        mTitles = Arrays.asList(titles);
        for (int i = 0; i < mTitles.size(); i++)
        {
            mNewsTable.addTab(mNewsTable.newTab().setText(mTitles.get(i)));
        }
    }

    private void initToolBars()
    {
        setSupportActionBar(mNewsToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle("资讯");
    }

    @Override
    public void showInfo(List<HeadLine.DataBean> mList)
    {
        mAdapter.setData(mList);
//        mNewsRecycler.setAdapter(mAdapter);
    }


    @Override
    public void showData(List<Picture.DataBean> mList)
    {
        mPicAdapter.setData(mList);
//        mNewsRecycler.setAdapter(mPicAdapter);
    }

    @Override
    public void showError(String msg)
    {
        Log.e("info", msg);
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
