package com.scx.applet.message.resp.entity;

/**
 * 音乐
 *
 * @author 宋程玺
 * @date 2015-08-08
 */
public class Music {
    //音乐标题
    private String Title;
    //音乐描述
    private String Description;
    //音乐链接
    private String MUsicUrl;
    //高质量音乐链接，WIFI环境优先使用该链接播放音乐
    private String HQMusicUrl;
    //缩略图的媒体id
    private String ThumbMediaId;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMUsicUrl() {
        return MUsicUrl;
    }

    public void setMUsicUrl(String mUsicUrl) {
        MUsicUrl = mUsicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String hQMusicUrl) {
        HQMusicUrl = hQMusicUrl;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

}
