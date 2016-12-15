package com.yidou.wandou.lol.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yidou.wandou.lol.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity
{

    @BindView(R.id.ac_login_bt)
    Button mAcLoginBt;
    @BindView(R.id.ac_login_edittext_one)
    EditText mAcLoginEdittextOne;
    @BindView(R.id.ac_login_edittext_two)
    EditText mAcLoginEdittextTwo;
    @BindView(R.id.ac_login_rela1)
    RelativeLayout mAcLoginRela1;
    @BindView(R.id.ac_login_rela2)
    RelativeLayout mAcLoginRela2;
    @BindView(R.id.ac_login_close)
    ImageView mAcLoginClose;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAcLoginEdittextOne.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus)
                {
                    mAcLoginRela1.setBackgroundResource(R.drawable.ac_login_edittext_bg);
                } else
                {
                    mAcLoginRela1.setBackgroundResource(R.color.colorBg);
                }
            }
        });
        mAcLoginEdittextTwo.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus)
                {
                    mAcLoginRela2.setBackgroundResource(R.drawable.ac_login_edittext_bg);
                } else
                {
                    mAcLoginRela2.setBackgroundResource(R.color.colorBg);
                }
            }
        });
        //关闭该登录界面
        mAcLoginClose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        //登录按钮
        mAcLoginBt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(LoginActivity.this, "您已成功登陆，谢谢~", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void choseOfBg(int position)//编辑框背景选择
//    {
//        switch (0)
//        {
//            case 0:
//                mAcLoginRela1.setBackgroundResource(R.drawable.ac_login_edittext_bg);
//                mAcLoginRela2.setBackgroundResource(R.color.colorBg);
//                break;
//            case 1:
//                mAcLoginRela1.setBackgroundResource(R.color.colorBg);
//                mAcLoginRela2.setBackgroundResource(R.drawable.ac_login_edittext_bg);
//                break;
//            default:
//                break;
//        }
//    }
}
