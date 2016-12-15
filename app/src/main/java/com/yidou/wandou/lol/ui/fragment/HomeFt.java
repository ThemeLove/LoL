package com.yidou.wandou.lol.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.RequestLoadMoreListener;
import com.yidou.wandou.lol.Config;
import com.yidou.wandou.lol.R;
import com.yidou.wandou.lol.adapter.TopAdapter;
import com.yidou.wandou.lol.base.BaseFragment;
import com.yidou.wandou.lol.constract.IHeadLine;
import com.yidou.wandou.lol.model.HeadLine;
import com.yidou.wandou.lol.presenter.HLinePresenterImpl;
import com.yidou.wandou.lol.ui.BaiKeActivity;
import com.yidou.wandou.lol.ui.Detail_Video;
import com.yidou.wandou.lol.ui.Detail_Zhanji;
import com.yidou.wandou.lol.ui.HeroActivity;
import com.yidou.wandou.lol.ui.NewsActivity;
import com.zanlabs.widget.infiniteviewpager.InfiniteViewPager;
import com.zanlabs.widget.infiniteviewpager.indicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/12/7.
 */

public class HomeFt extends BaseFragment implements IHeadLine.Views
{
    @BindView(R.id.home_recycler)
    RecyclerView mHomeRecycler;
    @BindView(R.id.home_refresh)
    SwipeRefreshLayout mHomeRefresh;


    private Unbinder mUnbinder;

    private List<Integer> images;//滑动广告的图片地址


    private BaseRecyclerAdapter<HeadLine.DataBean> mAdapter;
    private HLinePresenterImpl mPresenter;
    private Handler mHandler;

    private boolean connected = false;//判断网络是否已经连接
    private List<Integer> pages = new ArrayList<>();
    private int page = 1;
    private List<HeadLine.DataBean> mDatas = new ArrayList<>();

    @Override
    public int getLayoutId()
    {
        return R.layout.ft_home;
    }

    @Override
    public void initVariables()
    {
        checkNet();//判断网络
        images = new ArrayList<>();
        images = Arrays.asList(Config.IMAG);

        mPresenter = new HLinePresenterImpl(getActivity(), this);
        mHandler = new Handler();
        pages.add(page);
    }

    @Override
    public void initViews(View view, Bundle savedInstanceState)
    {
        mUnbinder = ButterKnife.bind(this, view);
        initAllView();
    }

    private void initAllView()
    {
        mHomeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new BaseRecyclerAdapter<HeadLine.DataBean>(getActivity(), null, R.layout.ft_recycler_item)
        {
            @Override
            protected void convert(BaseViewHolder helper, final HeadLine.DataBean item)
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
                LinearLayout layout = (LinearLayout) helper.getConvertView().findViewById(R.id.ft_home_linear);
                layout.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(getActivity(), Detail_Video.class);
                        intent.putExtra(Config.TAG, item.getDestUrl());
                        intent.putExtra(Config.TITLE, "视频详情...");
                        getActivity().startActivity(intent);
                    }
                });
            }
        };
        mHomeRecycler.setAdapter(mAdapter);
        mAdapter.openLoadingMore(true);

        /**
         * 滑动广告窗体
         */
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.test, null);
        header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        //战绩查询
        ImageView image2 = (ImageView) header.findViewById(R.id.image2);
        image2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), Detail_Zhanji.class);
                getActivity().startActivity(intent);
            }
        });

        //资讯详情
        ImageView image1 = (ImageView) header.findViewById(R.id.image1);
        image1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(getActivity(), NewsActivity.class);
                getActivity().startActivity(it);
            }
        });

        //英雄
        ImageView image3 = (ImageView) header.findViewById(R.id.image3);
        image3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), HeroActivity.class);
                startActivity(intent);
            }
        });

        //百科
        ImageView image4 = (ImageView) header.findViewById(R.id.image4);
        image4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), BaiKeActivity.class);
                startActivity(intent);
            }
        });

        mAdapter.addHeaderView(header);
        InfiniteViewPager mHomeViewpager = (InfiniteViewPager) header.findViewById(R.id.home_viewpager);
        CirclePageIndicator mHomeIndicator = (CirclePageIndicator) header.findViewById(R.id.home_indicator);

        TopAdapter adapter = new TopAdapter(getActivity(), images, Config.IMG_URL);
        mHomeViewpager.setAdapter(adapter);
        mHomeViewpager.setAutoScrollTime(2000);
        mHomeViewpager.startAutoScroll();
        mHomeIndicator.setViewPager(mHomeViewpager);
        //下拉刷新
        mHomeRefresh.setColorSchemeResources(R.color.colorYellow);
        mHomeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                mHomeRefresh.setRefreshing(true);
                mHandler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        mHomeRefresh.setRefreshing(false);
                    }
                }, 1200);
                initLoadingDatas();
            }
        });
    }

    public void checkNet()//判断网络是否已经连接上
    {
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        connected = info != null && info.isConnected();
    }

    @Override//刷新数据,加载数据
    public void initLoadingDatas()
    {
        if (connected)
        {
            mPresenter.loadingDataFromNet(page);
        } else
        {
            mPresenter.loadingDataFromCache(Config.HEAD_COOKIE);
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mUnbinder.unbind();
    }


    @Override
    public void showInfo(final List<HeadLine.DataBean> mList)
    {
        mDatas.addAll(mList);
        mAdapter.setData(mDatas);

        mAdapter.setOnLoadMoreListener(new RequestLoadMoreListener()
        {
            @Override
            public void onLoadMoreRequested()
            {
                mHomeRecycler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (pages.size()<=2)
                        {
                            mHandler.postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Integer pageCount = pages.get(pages.size() - 1);
                                    pageCount += 1;
                                    pages.add(pageCount);
                                    mPresenter.loadingDataFromNet(pageCount);
                                    mAdapter.notifyDataChangeAfterLoadMore(false);
                                }
                            }, 3000);
                        } else
                        {
//                            mAdapter.setData(mList);
                            mAdapter.notifyDataChangeAfterLoadMore(false);
                            mAdapter.addNoMoreView();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void showError(String msg)
    {
        Log.e("info", "------------>" + msg);
    }

}
