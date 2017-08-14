package com.scx.applet.message.resp;

import com.scx.applet.message.resp.entity.Music;

/**
 * 回复音乐消息
 *
 * @author 宋程玺
 * @date 2015-08-08
 */
public class MusicMessage extends BaseMessage {
    //音乐
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }

}
