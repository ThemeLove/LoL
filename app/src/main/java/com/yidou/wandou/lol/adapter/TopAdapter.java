package com.yidou.wandou.lol.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidou.wandou.lol.Config;
import com.yidou.wandou.lol.R;
import com.yidou.wandou.lol.ui.Detail_Video;
import com.zanlabs.widget.infiniteviewpager.InfinitePagerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/7.
 */

public class TopAdapter extends InfinitePagerAdapter
{
    private Context mContext;
    private List<Integer> images;
    private String urls;

    public TopAdapter(Context context, List<Integer> images, String urls)
    {
        mContext = context;
        this.images = images;
        this.urls = urls;
    }

    @Override
    public int getItemCount()
    {
        return images == null ? 0 : images.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup container)
    {
        View views;
        ViewHolders holders;
        if (convertView == null)
        {
            views = LayoutInflater.from(mContext).inflate(R.layout.ft_home_banner_item, container, false);
            holders = new ViewHolders(views);
            views.setTag(holders);
        }else
        {
            views = convertView;
            holders = (ViewHolders) views.getTag();
        }
        Uri uri = Uri.parse("res://" +
                mContext.getPackageName() +
                "/" + images.get(position));
        holders.mDraweeView.setImageURI(uri);//加载缓存图片
        views.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext, Detail_Video.class);
                intent.putExtra(Config.TAG, urls);
                intent.putExtra(Config.TITLE, "广告详情...");
                mContext.startActivity(intent);
            }
        });
        return views;
    }

    class ViewHolders
    {
        @BindView(R.id.banner_drawee)
        SimpleDraweeView mDraweeView;
        public ViewHolders(View view)
        {
            ButterKnife.bind(this, view);
        }
    }
}
