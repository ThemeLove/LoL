package com.yidou.wandou.lol.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.yidou.wandou.lol.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingActivity extends AppCompatActivity
{

    @BindView(R.id.loading_logo)
    ImageView mLoadingLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);
        initImageView();
    }

    private void initImageView()
    {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 1.0f);
        animation.setDuration(3000);
        mLoadingLogo.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
    }
}
