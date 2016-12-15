package com.yidou.wandou.lol.ui.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.yidou.wandou.lol.Config;
import com.yidou.wandou.lol.R;
import com.yidou.wandou.lol.base.BaseFragment;
import com.yidou.wandou.lol.constract.IMomoent;
import com.yidou.wandou.lol.model.Moment;
import com.yidou.wandou.lol.presenter.MomentPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/12/7.
 */

public class MomentFt extends BaseFragment implements IMomoent.Views
{
    @BindView(R.id.ft_moment_toolbar)
    Toolbar mFtMomentToolbar;
    @BindView(R.id.ft_moment_recycler)
    RecyclerView mFtMomentRecycler;
    private Unbinder mUnbinder;

    private MomentPresenter mPresenter;
    private BaseRecyclerAdapter<Moment.DataBeanX.DataBean.NewsBean> mAdapter;

    private SimpleDraweeView mSimpleDraweeView;//头部图片
    private TextView mTextView;//头部说明


    private boolean connected;//网络判断

    @Override
    public int getLayoutId()
    {
        return R.layout.ft_moment;
    }

    @Override
    public void initVariables()
    {
        checkNet();
        mPresenter = new MomentPresenter(getActivity(), this);
        mAdapter = new BaseRecyclerAdapter<Moment.DataBeanX.DataBean.NewsBean>(getActivity(), null, R.layout.ft_moment_item)
        {
            @Override
            protected void convert(BaseViewHolder helper, Moment.DataBeanX.DataBean.NewsBean item)
            {
                helper.setText(R.id.ft_moment_title, item.getTitle());
                //截止字符串的长度
                if (item.getContent().length() > 15)
                {
                    helper.setText(R.id.ft_moment_content, item.getContent().substring(1,16)+"...");
                }else
                {
                    helper.setText(R.id.ft_moment_content, item.getContent());
                }

                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) helper.getConvertView().findViewById(R.id.ft_moment_simple);
                simpleDraweeView.setImageURI(item.getPhoto());
            }
        };
    }

    @Override
    public void initViews(View view, Bundle savedInstanceState)
    {
        mUnbinder = ButterKnife.bind(this, view);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mFtMomentToolbar);
        mFtMomentRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFtMomentRecycler.setAdapter(mAdapter);
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.ft_moment_header, null);
        header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mSimpleDraweeView = (SimpleDraweeView) header.findViewById(R.id.ft_moment_header_simple);
        mTextView = (TextView) header.findViewById(R.id.ft_moment_header_title);
        mAdapter.addHeaderView(header);
    }

    @Override
    public void initLoadingDatas()
    {
        if (connected)
        {
            mPresenter.loadingNetData();
        }else
        {
            mPresenter.loadingCacheData(Config.MOMENT_CACHE);
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mUnbinder.unbind();
    }


    @Override
    public void showMomentDatas(List<Moment.DataBeanX> list)
    {
        mAdapter.setData(list.get(0).getData().getNews());
        mSimpleDraweeView.setImageURI(list.get(0).getData().getPhoto());
        mTextView.setText(list.get(0).getData().getContent());
    }

    @Override
    public void showErrorMsg(String msg)
    {
        Log.e("info", msg);
    }

    public void checkNet()//判断网络是否已经连接上
    {
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        connected = info != null && info.isConnected();
    }

}
