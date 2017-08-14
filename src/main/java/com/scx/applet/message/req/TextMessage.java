package com.scx.applet.message.req;

/**
 * 文本消息
 *
 * @author 宋程玺
 * @date 2015-08-08
 */
public class TextMessage extends BaseMessage {
    //消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}
