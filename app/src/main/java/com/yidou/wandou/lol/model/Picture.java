package com.yidou.wandou.lol.model;

import java.util.List;


public class Picture
{
    private int totalPage;
    private int totalRecord;
    private int pageNum;
    private int pageSize;
    private List<DataBean> data;

    public int getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

    public int getTotalRecord()
    {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord)
    {
        this.totalRecord = totalRecord;
    }

    public int getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(int pageNum)
    {
        this.pageNum = pageNum;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public List<DataBean> getData()
    {
        return data;
    }

    public void setData(List<DataBean> data)
    {
        this.data = data;
    }

    public static class DataBean
    {
        /**
         * galleryId : 130714
         * title : 12.10 每日一污：听说，风油精是可以口？服？的？
         * description :
         * coverUrl : http://s1.dwstatic.com/group1/M00/7F/3F/cc469968e722ff534f53e8bfb1e19767.png
         * coverWidth : 195
         * coverHeight : 105
         * created : 1481339575
         * updated : 1481339576
         * picsum : 13
         * commentCount : 25
         * clicks : 0
         * modify_time : 2016-12-11 10:13:27
         * destUrl : http://box.dwstatic.com/unsupport.php?lolboxAction=toAlbumDetail&albumId=130714&type=jiongTu
         */

        private String galleryId;
        private String title;
        private String description;
        private String coverUrl;
        private String coverWidth;
        private String coverHeight;
        private String created;
        private String updated;
        private String picsum;
        private String commentCount;
        private String clicks;
        private String modify_time;
        private String destUrl;

        public String getGalleryId()
        {
            return galleryId;
        }

        public void setGalleryId(String galleryId)
        {
            this.galleryId = galleryId;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public String getDescription()
        {
            return description;
        }

        public void setDescription(String description)
        {
            this.description = description;
        }

        public String getCoverUrl()
        {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl)
        {
            this.coverUrl = coverUrl;
        }

        public String getCoverWidth()
        {
            return coverWidth;
        }

        public void setCoverWidth(String coverWidth)
        {
            this.coverWidth = coverWidth;
        }

        public String getCoverHeight()
        {
            return coverHeight;
        }

        public void setCoverHeight(String coverHeight)
        {
            this.coverHeight = coverHeight;
        }

        public String getCreated()
        {
            return created;
        }

        public void setCreated(String created)
        {
            this.created = created;
        }

        public String getUpdated()
        {
            return updated;
        }

        public void setUpdated(String updated)
        {
            this.updated = updated;
        }

        public String getPicsum()
        {
            return picsum;
        }

        public void setPicsum(String picsum)
        {
            this.picsum = picsum;
        }

        public String getCommentCount()
        {
            return commentCount;
        }

        public void setCommentCount(String commentCount)
        {
            this.commentCount = commentCount;
        }

        public String getClicks()
        {
            return clicks;
        }

        public void setClicks(String clicks)
        {
            this.clicks = clicks;
        }

        public String getModify_time()
        {
            return modify_time;
        }

        public void setModify_time(String modify_time)
        {
            this.modify_time = modify_time;
        }

        public String getDestUrl()
        {
            return destUrl;
        }

        public void setDestUrl(String destUrl)
        {
            this.destUrl = destUrl;
        }
    }
}
