package com.yidou.wandou.lol.model;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class Videos
{


    /**
     * group : newmatch
     * name : 推荐视频
     * subCategory : [{"tag":"newest","name":"最新视频","icon":"http://img.dwstatic.com/lol/1606/329322932773/1465367768527.jpg","dailyUpdate":"21","url":"http://box.dwstatic.com/unsupport.php?lolboxAction=toVideoList&tag=newest"},{"tag":"wuxianhuoli","name":"大神直播","icon":"http://img.dwstatic.com/lol/1606/330622720649/1466674183369.jpg","des":"","url":"http://webpd.mbox.duowan.com/index.php?r=live/index","dailyUpdate":"0"},{"tag":"lolpifu","name":"皮肤视频","icon":"http://img.dwstatic.com/lol/1606/329668478323/1465713302704.jpg","playCount":"1000","pic":"http://img.dwstatic.com/lol/1606/329668478323/1465713302704.jpg","videoCount":"200","title":"皮肤视频","des":"完整收录LOL英雄所有皮肤视频效果，买皮肤前必看，不花冤枉钱。","dailyUpdate":"0","url":"http://box.dwstatic.com/unsupport.php?lolboxAction=toVideoList&tag=lolpifu"},{"tag":"banben","name":"版本更新","icon":"http://img.dwstatic.com/lol/1606/329248183709/1465294594073.jpg","dailyUpdate":"0","url":"http://box.dwstatic.com/unsupport.php?lolboxAction=toVideoList&tag=banben"},{"tag":"s6","name":"S6总决赛","icon":"http://img.dwstatic.com/lol/1609/338839298305/1474892266896.jpg","dailyUpdate":"0","url":"http://box.dwstatic.com/unsupport.php?lolboxAction=toVideoList&tag=s6"},{"tag":"xifalin","name":"主播大爆料","icon":"http://img.dwstatic.com/lol/1610/340310693540/1476356286574.jpg","dailyUpdate":"0","url":"http://box.dwstatic.com/unsupport.php?lolboxAction=toVideoList&tag=xifalin"},{"tag":"2016allstar","name":"全明星","icon":"http://img.dwstatic.com/lol/1612/345118905677/1481165063409.jpg","dailyUpdate":"0","url":"http://box.dwstatic.com/unsupport.php?lolboxAction=toVideoList&tag=2016allstar"}]
     */

    private String group;
    private String name;
    private List<SubCategoryBean> subCategory;

    public String getGroup()
    {
        return group;
    }

    public void setGroup(String group)
    {
        this.group = group;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<SubCategoryBean> getSubCategory()
    {
        return subCategory;
    }

    public void setSubCategory(List<SubCategoryBean> subCategory)
    {
        this.subCategory = subCategory;
    }

    public static class SubCategoryBean
    {
        /**
         * tag : newest
         * name : 最新视频
         * icon : http://img.dwstatic.com/lol/1606/329322932773/1465367768527.jpg
         * dailyUpdate : 21
         * url : http://box.dwstatic.com/unsupport.php?lolboxAction=toVideoList&tag=newest
         * des :
         * playCount : 1000
         * pic : http://img.dwstatic.com/lol/1606/329668478323/1465713302704.jpg
         * videoCount : 200
         * title : 皮肤视频
         */

        private String tag;
        private String name;
        private String icon;
        private String dailyUpdate;
        private String url;
        private String des;
        private String playCount;
        private String pic;
        private String videoCount;
        private String title;

        public String getTag()
        {
            return tag;
        }

        public void setTag(String tag)
        {
            this.tag = tag;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getIcon()
        {
            return icon;
        }

        public void setIcon(String icon)
        {
            this.icon = icon;
        }

        public String getDailyUpdate()
        {
            return dailyUpdate;
        }

        public void setDailyUpdate(String dailyUpdate)
        {
            this.dailyUpdate = dailyUpdate;
        }

        public String getUrl()
        {
            return url;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public String getDes()
        {
            return des;
        }

        public void setDes(String des)
        {
            this.des = des;
        }

        public String getPlayCount()
        {
            return playCount;
        }

        public void setPlayCount(String playCount)
        {
            this.playCount = playCount;
        }

        public String getPic()
        {
            return pic;
        }

        public void setPic(String pic)
        {
            this.pic = pic;
        }

        public String getVideoCount()
        {
            return videoCount;
        }

        public void setVideoCount(String videoCount)
        {
            this.videoCount = videoCount;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }
    }
}
