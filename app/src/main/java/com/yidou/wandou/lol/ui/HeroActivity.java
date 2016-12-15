package com.yidou.wandou.lol.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.yidou.wandou.lol.Config;
import com.yidou.wandou.lol.R;
import com.yidou.wandou.lol.constract.IHeros;
import com.yidou.wandou.lol.model.AllHeros;
import com.yidou.wandou.lol.model.FreeHeros;
import com.yidou.wandou.lol.presenter.HeroPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeroActivity extends AppCompatActivity implements IHeros.FreeViews, IHeros.AllViews
{

    @BindView(R.id.ac_hero_toolbar)
    Toolbar mAcHeroToolbar;
    @BindView(R.id.ac_hero_tab)
    TabLayout mAcHeroTab;
    @BindView(R.id.ac_hero_recycler)
    RecyclerView mAcHeroRecycler;
    @BindView(R.id.ac_hero_refresh)
    SwipeRefreshLayout mAcHeroRefresh;
    @BindView(R.id.ac_hero_relative)
    RelativeLayout mAcHeroRelative;

    private String[] titles =
            {
                    "免费", "我的英雄", "全部"
            };

    private BaseRecyclerAdapter<FreeHeros.FreeBean> mFreeAdapter;
    private BaseRecyclerAdapter<AllHeros.AllBean> mAllAdapter;
    private HeroPresenter mPresenter;
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);
        ButterKnife.bind(this);
        initVaribales();//初始化变量
        initToolBar();//toolBar
        initViews(0);//初始化View
        initTabLayout();
    }

    private void initViews(int p)
    {
        mAcHeroRefresh.setColorSchemeResources(R.color.colorYellow);
        switch (p)
        {
            case 0:
                mAcHeroRelative.setVisibility(View.GONE);
                mAcHeroRecycler.setVisibility(View.VISIBLE);
                mAcHeroRefresh.setVisibility(View.VISIBLE);
                mPresenter.loadingFreeHeroDatas();
                StaggeredGridLayoutManager managerFree = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                mAcHeroRecycler.setLayoutManager(managerFree);
                mAcHeroRecycler.setAdapter(mFreeAdapter);
                refreshData(0);
                break;
            case 1:
                mAcHeroRelative.setVisibility(View.VISIBLE);
                mAcHeroRecycler.setVisibility(View.GONE);
                mAcHeroRefresh.setVisibility(View.GONE);
                break;
            case 2:
                mAcHeroRelative.setVisibility(View.GONE);
                mAcHeroRecycler.setVisibility(View.VISIBLE);
                mAcHeroRefresh.setVisibility(View.VISIBLE);
                mPresenter.loadingAllHeroDatas();
                StaggeredGridLayoutManager managerAll = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
                mAcHeroRecycler.setLayoutManager(managerAll);
                mAcHeroRecycler.setAdapter(mAllAdapter);
                refreshData(2);
                break;
            default:
                break;
        }
    }

    //下拉刷新
    private void refreshData(int i)
    {
        switch (i)
        {
            case 0:
                mAcHeroRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
                {
                    @Override
                    public void onRefresh()
                    {
                        mAcHeroRefresh.setRefreshing(true);
                        mHandler.postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                mPresenter.loadingFreeHeroDatas();
                                mAcHeroRefresh.setRefreshing(false);
                            }
                        }, 1200);
                    }
                });
                break;
            case 1:
                mAcHeroRefresh.setRefreshing(false);
                break;
            case 2:
                mAcHeroRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
                {
                    @Override
                    public void onRefresh()
                    {
                        mAcHeroRefresh.setRefreshing(true);
                        mHandler.postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                mPresenter.loadingAllHeroDatas();
                                mAcHeroRefresh.setRefreshing(false);
                            }
                        }, 1200);
                    }
                });
                break;
            default:
                break;
        }
    }

    private void initVaribales()
    {
        mPresenter = new HeroPresenter(this, this);
        mFreeAdapter = new BaseRecyclerAdapter<FreeHeros.FreeBean>(this, null, R.layout.ac_free_hero_item)
        {
            @Override
            protected void convert(BaseViewHolder helper, FreeHeros.FreeBean item)
            {
                SimpleDraweeView view = (SimpleDraweeView) helper.getConvertView().findViewById(R.id.hero_img);
                view.setImageURI(Config.HERO_IMG + item.getEnName() + ".jpg");
                helper.setText(R.id.hero_name, item.getTitle());
                helper.setText(R.id.hero_names, item.getCnName());
                helper.setText(R.id.hero_position, item.getLocation());
            }
        };
        mAllAdapter = new BaseRecyclerAdapter<AllHeros.AllBean>(this, null, R.layout.ac_all_hero_item)
        {
            @Override
            protected void convert(BaseViewHolder helper, AllHeros.AllBean item)
            {
                SimpleDraweeView view = (SimpleDraweeView) helper.getConvertView().findViewById(R.id.ac_all_hero_img);
                view.setImageURI(Config.HERO_IMG + item.getEnName() + ".jpg");
                helper.setText(R.id.ac_all_hero_name, item.getTitle());
            }
        };
    }

    private void initTabLayout()
    {
        setTables();//添加TableLayOut的标题
        mAcHeroTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                String s = tab.getText().toString();
                if ("免费".equals(s))
                {
                    initViews(0);
                } else if ("我的英雄".equals(s))
                {
                    initViews(1);
                } else if ("全部".equals(s))
                {
                    initViews(2);
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

    private void setTables()
    {
        for (int i = 0; i < titles.length; i++)
        {
            mAcHeroTab.addTab(mAcHeroTab.newTab().setText(titles[i]));
        }
    }

    private void initToolBar()
    {
        setSupportActionBar(mAcHeroToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle("英雄");
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
    public void showAllHeros(List<AllHeros.AllBean> mList)
    {
        mAllAdapter.setData(mList);
    }

    @Override
    public void showAllError(String s)
    {
        Log.e("info", "all--------------" + s);
    }

    @Override
    public void showFreeHeros(List<FreeHeros.FreeBean> mList)
    {
        mFreeAdapter.setData(mList);
    }

    @Override
    public void showFreeError(String s)
    {
        Log.e("info", "free--------------" + s);
    }
}
