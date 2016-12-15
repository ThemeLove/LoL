package com.yidou.wandou.lol.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidou.wandou.lol.R;
import com.yidou.wandou.lol.base.BaseFragment;
import com.yidou.wandou.lol.ui.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/12/7.
 */

public class MineFt extends BaseFragment
{


    @BindView(R.id.ft_mine_simple)
    SimpleDraweeView mFtMineSimple;
    @BindView(R.id.ft_mine_rela)
    RelativeLayout mFtMineRela;
    private Unbinder mUnbinder;

    private String url = "res://com.yidou.wandou.lol/";

    @Override
    public int getLayoutId()
    {
        return R.layout.ft_mine;
    }

    @Override
    public void initVariables()
    {

    }

    @Override
    public void initViews(View view, Bundle savedInstanceState)
    {
        mUnbinder = ButterKnife.bind(this, view);
        mFtMineSimple.setImageURI(url + R.mipmap.box_chat_conversation_icon_default);
        mFtMineRela.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initLoadingDatas()
    {
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mUnbinder.unbind();
    }

}
