package com.yidou.wandou.lol.ui.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.yidou.wandou.lol.Config;
import com.yidou.wandou.lol.R;
import com.yidou.wandou.lol.base.BaseFragment;
import com.yidou.wandou.lol.constract.IRadio;
import com.yidou.wandou.lol.constract.IVideos;
import com.yidou.wandou.lol.model.Radio;
import com.yidou.wandou.lol.model.Videos;
import com.yidou.wandou.lol.presenter.RadioPresenter;
import com.yidou.wandou.lol.presenter.VideoPresenter;
import com.yidou.wandou.lol.ui.decoration.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/12/7.
 */

public class VideoFt extends BaseFragment implements IVideos.Views, IRadio.Views
{

    @BindView(R.id.ft_video_toolbar)
    Toolbar mFtVideoToolbar;
    @BindView(R.id.ft_video_fenlei)
    Button mFtVideoFenlei;
    @BindView(R.id.ft_video_zhibo)
    Button mFtVideoZhibo;
    @BindView(R.id.ft_video_recycler)
    RecyclerView mFtVideoRecycler;
    @BindView(R.id.ft_video_refresh)
    SwipeRefreshLayout mFtVideoRefresh;

    private Unbinder mUnbinder;

    private int button_daohang = 0;//分类导航
    private int button_zhibo = 1;//大神直播间

    //分类导航三兄弟
    private BaseRecyclerAdapter<Videos> mVideoAdapter;
    private BaseRecyclerAdapter<Videos.SubCategoryBean> mIconAdapter;
    private VideoPresenter mVideoPresenter;

    //视频直播
    private BaseRecyclerAdapter<Radio> mRadioAdapter;
    private RadioPresenter mRadioPresenter;

    private boolean connected;//判断网络
    private Handler mHandler = new Handler();

    @Override
    public int getLayoutId()
    {
        return R.layout.ft_video;
    }

    @Override
    public void initVariables()
    {
        checkNet();
        mVideoPresenter = new VideoPresenter(getActivity(), this);
        mRadioPresenter = new RadioPresenter(getActivity(), this);
        //分类导航的适配器
        mVideoAdapter = new BaseRecyclerAdapter<Videos>(getActivity(), null, R.layout.ft_video_fenlei_item)
        {
            @Override
            protected void convert(BaseViewHolder helper, Videos item)
            {
                helper.setText(R.id.fenlei_name, item.getName());
                RecyclerView recyclerView = (RecyclerView) helper.getConvertView().findViewById(R.id.fenlei_subCategory);
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                //计算行数
                int linknumber =
                        item.getSubCategory().size() % 4 == 0 ? item.getSubCategory().size() / 4 : item.getSubCategory().size() / 4 + 1;
                ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
//                params.height = linknumber * (screenWidth / 4) + linknumber * dip2px(10);
                params.height = linknumber * dip2px(100);
                recyclerView.setLayoutParams(params);
                mIconAdapter = new BaseRecyclerAdapter<Videos.SubCategoryBean>(getActivity(), item.getSubCategory(), R.layout.ft_video_fenlei_icon_item)
                {
                    @Override
                    protected void convert(BaseViewHolder helper, Videos.SubCategoryBean item)
                    {
                        SimpleDraweeView draweeView = (SimpleDraweeView) helper.getConvertView().findViewById(R.id.fenlei_icon_simple);
                        draweeView.setImageURI(item.getIcon());
                        helper.setText(R.id.fenlei_icon_texts, item.getName());
                    }
                };
                recyclerView.setAdapter(mIconAdapter);
            }
        };
        //直播大神的适配器
        mRadioAdapter = new BaseRecyclerAdapter<Radio>(getActivity(), null, R.layout.ft_video_radio_item)
        {
            @Override
            protected void convert(BaseViewHolder helper, Radio item)
            {
                //直播名字判断
                if (item.getName().length() > 10)
                {
                    helper.setText(R.id.ft_radio_name, item.getName().substring(0, 6) + "...");
                } else
                {
                    helper.setText(R.id.ft_radio_name, item.getName());
                }
                //直播状态的判断
                TextView status = (TextView) helper.getConvertView().findViewById(R.id.ft_radio_status);
                if (item.getStatus().equals("1"))
                {
                    helper.setText(R.id.ft_radio_status, "正在直播");
                    status.setTextColor(getResources().getColor(R.color.colorBlack));
                    helper.setImageResource(R.id.ft_radio_status_img, R.drawable.external_live_starting_icon);
                } else
                {
                    helper.setText(R.id.ft_radio_status, "未开始");
                    helper.setImageResource(R.id.ft_radio_status_img, R.drawable.external_live_unstart_icon);
                }
                helper.setText(R.id.ft_radio_description, item.getDescription());
                helper.setText(R.id.ft_radio_website, item.getWebsite());
                SimpleDraweeView draweeView = (SimpleDraweeView) helper.getConvertView().findViewById(R.id.ft_radio_simple);
                draweeView.setImageURI(item.getImg());
            }
        };
    }

    @Override
    public void initViews(View view, Bundle savedInstanceState)
    {
        mUnbinder = ButterKnife.bind(this, view);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mFtVideoToolbar);
//        ((AppCompatActivity)getActivity()).setTitle("视频");
//        ((AppCompatActivity)getActivity()).setTitleColor(getResources().getColor(R.color.colorWhite));
        choseOfButton(0);
        mFtVideoFenlei.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                choseOfButton(0);
            }
        });
        mFtVideoZhibo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                choseOfButton(1);
            }
        });
        mFtVideoRefresh.setColorSchemeResources(R.color.colorYellow);
        mFtVideoRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mFtVideoRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    private void choseOfLoadingData(int p)
    {
        if (connected)
        {
            switch (p)
            {
                case 0://分类导航:
                    mVideoPresenter.loadingFenleiNetDatas();
                    break;
                case 1://大神直播
                    mRadioPresenter.loadingRadioNetDatas();
                    break;
                default:
                    break;
            }
        } else
        {
            switch (p)
            {
                case 0://分类导航:
                    mVideoPresenter.loadingFenleiCacheDatas(Config.VIDEO_CACHE);
                    break;
                case 1://大神直播
                    mRadioPresenter.loadingRadioCacheDatas(Config.RADIO_CACHE);
                    break;
                default:
                    break;
            }
        }
    }

    private void choseOfButton(int position)
    {
        switch (position)
        {
            case 0:
                mFtVideoFenlei.setBackgroundResource(R.drawable.video_toolbar_left_pressed);
                mFtVideoFenlei.setTextColor(getResources().getColor(R.color.colorWhite));
                mFtVideoZhibo.setBackgroundResource(R.drawable.video_toolbar_right_nomal);
                mFtVideoZhibo.setTextColor(getResources().getColor(R.color.colorVideoBlack));
                choseOfLoadingData(0);
                mFtVideoRecycler.setAdapter(mVideoAdapter);
                mFtVideoRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
                {
                    @Override
                    public void onRefresh()
                    {
                        mFtVideoRefresh.setRefreshing(true);
                        mHandler.postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                mFtVideoRefresh.setRefreshing(false);
                                choseOfLoadingData(0);
                            }
                        }, 2000);
                    }
                });
                break;
            case 1:
                mFtVideoFenlei.setBackgroundResource(R.drawable.video_toolbar_left_nomal);
                mFtVideoFenlei.setTextColor(getResources().getColor(R.color.colorVideoBlack));
                mFtVideoZhibo.setBackgroundResource(R.drawable.video_toolbar_right_nomal_pressed);
                mFtVideoZhibo.setTextColor(getResources().getColor(R.color.colorWhite));
                choseOfLoadingData(1);
                mFtVideoRecycler.setAdapter(mRadioAdapter);
                mFtVideoRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
                {
                    @Override
                    public void onRefresh()
                    {
                        mFtVideoRefresh.setRefreshing(true);
                        mHandler.postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                mFtVideoRefresh.setRefreshing(false);
                                choseOfLoadingData(1);
                            }
                        }, 2000);
                    }
                });
                break;
            default:
                break;
        }
    }

    /**
     * 加载数据
     */
    @Override
    public void initLoadingDatas()
    {
//        if (connected)
//        {
//            mVideoPresenter.loadingFenleiNetDatas();
//        } else
//        {
//            mVideoPresenter.loadingFenleiCacheDatas(Config.VIDEO_CACHE);
//        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mUnbinder.unbind();
    }


    @Override
    public void showFenleiDatas(List<Videos> videos)
    {
        mVideoAdapter.setData(videos);
    }


    @Override
    public void showError(String msg)
    {
        Log.e("info", "----------------->" + "..........." + msg);
    }

    //将dp转化成px
    private int dip2px(float dip)
    {
        float v = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return (int) (v + 0.5f);
    }

    public void checkNet()//判断网络是否已经连接上
    {
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        connected = info != null && info.isConnected();
    }

    @Override
    public void showRadioDatas(List<Radio> mList)
    {
        mRadioAdapter.setData(mList);
    }

    @Override
    public void showErrorDatas(String msg)
    {
        Log.e("info", "----------------->" + msg);
    }
}
