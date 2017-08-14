package com.scx.applet.message.resp;

import com.scx.applet.message.resp.entity.Media;

/**
 * 回复图片消息
 *
 * @author 宋程玺
 * @date 2015-08-08
 */
public class ImageMessage extends BaseMessage {
    //图片
    private Media Image;

    public Media getImage() {
        return Image;
    }

    public void setImage(Media image) {
        Image = image;
    }

}
