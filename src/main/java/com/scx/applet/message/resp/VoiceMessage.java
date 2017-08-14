package com.scx.applet.message.resp;

import com.scx.applet.message.resp.entity.Media;

/**
 * 回复语音消息
 *
 * @author 宋程玺
 * @date 2015-08-08
 */
public class VoiceMessage extends BaseMessage {
    //语音
    private Media Voice;

    public Media getVoice() {
        return Voice;
    }

    public void setVoice(Media voice) {
        Voice = voice;
    }

}
