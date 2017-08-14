package com.scx.applet.message.resp;

import com.scx.applet.message.resp.entity.Video;

/**
 * 回复视频消息
 *
 * @author 宋程玺
 * @date 2015-08-08
 */
public class VideoMessage extends BaseMessage {
    //视频消息
    private Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }

}
