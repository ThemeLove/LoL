package com.yidou.wandou.lol.model;

import java.util.List;

/**
 * 周免英雄
 * Created by Administrator on 2016/12/13.
 */

public class FreeHeros
{

    /**
     * free : [{"enName":"Orianna","cnName":"奥莉安娜","title":"发条魔灵","tags":"mage","rating":"4,3,9,7","location":"中单","price":"6300,3000"},{"enName":"Kled","cnName":"克烈","title":"暴怒骑士","tags":"fighter","rating":"8,2,2,7","location":"上单","price":"6300,4500"},{"enName":"MasterYi","cnName":"易","title":"无极剑圣","tags":"assassin","rating":"10,4,2,4","location":"上单,中单,打野","price":"450,1000"},{"enName":"AurelionSol","cnName":"奥瑞利安·索尔","title":"铸星龙王","tags":"mage","rating":"2,3,8,7","location":"中单","price":"6300,4500"},{"enName":"Jax","cnName":"贾克斯","title":"武器大师","tags":"fighter","rating":"7,5,7,5","location":"上单,打野","price":"3150,2500"},{"enName":"Veigar","cnName":"维迦","title":"邪恶小法师","tags":"mage","rating":"2,2,10,7","location":"中单","price":"1350,1000"},{"enName":"Rengar","cnName":"雷恩加尔","title":"傲之追猎者","tags":"assassin","rating":"7,4,2,8","location":"上单,打野","price":"6300,4500"},{"enName":"Kassadin","cnName":"卡萨丁","title":"虚空行者","tags":"assassin","rating":"3,5,8,8","location":"中单","price":"3150,2500"},{"enName":"Nasus","cnName":"内瑟斯","title":"沙漠死神","tags":"fighter","rating":"7,5,6,6","location":"上单,打野","price":"3150,2500"},{"enName":"Trundle","cnName":"特朗德尔","title":"巨魔之王","tags":"fighter","rating":"7,6,2,5","location":"上单,打野","price":"3150,3000"},{"enName":"Ashe","cnName":"艾希","title":"寒冰射手","tags":"marksman,female ","rating":"7,3,2,4","location":"ADC","price":"450,1000"},{"enName":"Garen","cnName":"盖伦","title":"德玛西亚之力","tags":"fighter","rating":"7,7,1,5","location":"上单","price":"3150,1000"},{"enName":"Ryze","cnName":"瑞兹","title":"符文法师","tags":"mage","rating":"2,2,10,7","location":"上单,中单","price":"450,1000"}]
     * desc : *每周五更新周免
     */

    private String desc;
    private List<FreeBean> free;

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public List<FreeBean> getFree()
    {
        return free;
    }

    public void setFree(List<FreeBean> free)
    {
        this.free = free;
    }

    public static class FreeBean
    {
        /**
         * enName : Orianna
         * cnName : 奥莉安娜
         * title : 发条魔灵
         * tags : mage
         * rating : 4,3,9,7
         * location : 中单
         * price : 6300,3000
         */

        private String enName;
        private String cnName;
        private String title;
        private String tags;
        private String rating;
        private String location;
        private String price;

        public String getEnName()
        {
            return enName;
        }

        public void setEnName(String enName)
        {
            this.enName = enName;
        }

        public String getCnName()
        {
            return cnName;
        }

        public void setCnName(String cnName)
        {
            this.cnName = cnName;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public String getTags()
        {
            return tags;
        }

        public void setTags(String tags)
        {
            this.tags = tags;
        }

        public String getRating()
        {
            return rating;
        }

        public void setRating(String rating)
        {
            this.rating = rating;
        }

        public String getLocation()
        {
            return location;
        }

        public void setLocation(String location)
        {
            this.location = location;
        }

        public String getPrice()
        {
            return price;
        }

        public void setPrice(String price)
        {
            this.price = price;
        }
    }
}
