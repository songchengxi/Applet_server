package com.scx.applet.message.req;

/**
 * 视频消息
 *
 * @author 宋程玺
 * @date 2015-08-08
 */
public class VideoMessage extends BaseMessage {
    //视频消息媒体id
    private String MediaId;
    //视频消息缩略图的媒体id
    private String ThumbMediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }


}
