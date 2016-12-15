package com.yidou.wandou.lol.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yidou.wandou.lol.Config;
import com.yidou.wandou.lol.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Detail_Zhanji extends AppCompatActivity
{

    @BindView(R.id.detail_zhanji_toolbar)
    Toolbar mDetailZhanjiToolbar;
    @BindView(R.id.userName)
    EditText mUserName;//游戏名字

    @BindView(R.id.detail_start)
    Button mDetailStart;
    @BindView(R.id.service_text)//游戏大区名字
            TextView mServiceText;
    @BindView(R.id.service_frame)
    FrameLayout mServiceFrame;

    private boolean isStart = false;
    private String serviceName;//服务器名字

    public static final String[] SERVICES1 =
            {
                    "电信一", "电信二", "电信三",
                    "电信四", "电信五", "电信六",
                    "电信七", "电信八", "电信九",
                    "电信十", "电信十一", "电信十二",
                    "电信十三", "电信十四", "电信十五",
                    "电信十六", "电信十七", "电信十八",
                    "电信十九", "网通一", "网通二",
                    "网通三", "网通四", "网通五",
                    "网通六", "网通七", "教育一",
            };

    public static final String[] SERVICE2 =
            {
                    "艾欧尼亚 电信一", "祖安 电信二", "诺克萨斯 电信三",
                    "班德尔城 电信四", "皮尔特沃夫 电信五", "战争学院 电信六",
                    "巨神峰 电信七", "雷瑟守备 电信八", "裁决之地 电信九",
                    "黑色玫瑰 电信十", "暗影岛 电信十一", "钢铁烈阳 电信十二",
                    "均衡教派 电信十三", "水晶之痕 电信十四", "影流 电信十五",
                    "守望之海 电信十六", "征服之海 电信十七", "卡拉曼达 电信十八",
                    "皮城警备 电信十九", "比尔吉沃特 网通一", "德玛西亚 网通二",
                    "弗雷尔卓德 网通三", "无畏先锋 网通四", "恕瑞玛 网通五",
                    "扭曲丛林 网通六", "巨龙之巢 网通七", "教育网专区 教育一"
            };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__zhanji);
        ButterKnife.bind(this);

        serviceName = SERVICES1[0];
        initToolBars();
        initEdittext();
    }

    private void initEdittext()
    {
        mDetailStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!TextUtils.isEmpty(mUserName.getText()))//游戏名字不为空
                {
                    Intent intent = new Intent(Detail_Zhanji.this, Detail_Video.class);
                    String url = Config.URL_ZHANJI + serviceName + "&pn=" + mUserName.getText();
                    Log.e("info", "---->" + url);
                    intent.putExtra(Config.TAG, Config.URL_ZHANJI + serviceName + "&pn=" + mUserName.getText());
                    intent.putExtra(Config.TITLE, "战绩详情...");
                    startActivity(intent);
//                    Detail_Zhanji.this.finish();
                } else
                {
                    Toast.makeText(Detail_Zhanji.this, "不符合查询条件，请重新输入~", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mServiceFrame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Detail_Zhanji.this);
                final boolean[] itemCheck =
                        {
                                true, false, false,
                                false, false, false,
                                false, false, false,
                                false, false, false,
                                false, false, false,
                                false, false, false,
                                false, false, false,
                                false, false, false,
                                false, false, false,
                        };
                builder.setSingleChoiceItems(SERVICE2, 1, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        mServiceText.setText(SERVICE2[which]);
                        serviceName = SERVICES1[which];
                        builder.setCancelable(false);
                    }
                });
                builder.show();
            }
        });
    }



    private void initToolBars()
    {

        setSupportActionBar(mDetailZhanjiToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle("战绩查询。。。");
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
