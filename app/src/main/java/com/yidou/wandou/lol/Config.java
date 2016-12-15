package com.yidou.wandou.lol;

/**
 * Created by Administrator on 2016/12/7.
 * 整个项目的一些常亮，数组，或者字符串
 */
public class Config
{
    /**
     * 我的联系方式，有什么问题或者好的想法~可以联系我！
     */
    public static final String TAG = "1070138445~";
    public static final String TITLE = "titles";


    //home界面的滑动广告图片地址；
    public static Integer[] IMAG =
            {
                    R.drawable.banner_1, R.drawable.banner_2,
                    R.drawable.banner_3, R.drawable.banner_4,
                    R.drawable.banner_4, R.drawable.banner_6
            };
    //home界面滑动广告图片详情地址
    public static final String IMG_URL = "https://github.com/548986";

    public static final String HEADERLINE = "http://box.dwstatic.com/";//home界面的消息接口
    public static final String HEAD_COOKIE = "headlineNews";
    public static final String NEWS_COOKIE = "资讯";

    //战绩查询接口
    //http://lolbox.duowan.com/phone/playerDetailNewFB.php?sn=电信九&pn=可爱的余大叔
    public static final String URL_ZHANJI = "http://lolbox.duowan.com/phone/playerDetailNewFB.php?sn=";


    /**
     * 英雄数据接口
     */
    public static final String HERO = "http://lolbox.duowan.com/phone/";
    public static final String HERO_IMG = "http://img.lolbox.duowan.com/champions/";

    /**
     * 视频接口地址
     */
    public static final String VIDEO = "http://box.dwstatic.com/";
    public static final String VIDEO_CACHE = "video";

    /**
     * 视频直播的接口地址
     */
    public static final String RADIO = "http://webpd.mbox.duowan.com/";
    public static final String RADIO_CACHE = "radio";

    /**
     * 动态接口地址
     */
    public static final String MOMENT_CACHE = "moment";

}
