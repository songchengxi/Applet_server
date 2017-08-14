package com.scx.applet.service;

import com.scx.util.EmailUtil;
import com.scx.util.FormatDate;
import com.scx.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 小程序核心服务类
 *
 * @author 宋程玺
 * @date 2017-08-08
 */
public class CoreService {

    private static Logger logger = LoggerFactory.getLogger(CoreService.class);

    /**
     * 处理微信发来都请求
     */
    public static String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方账号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            logger.info("fromUserName:" + fromUserName);
            // 公众账号
            String toUserName = requestMap.get("ToUserName");
            logger.info("toUserName:" + toUserName);
            // 消息创建时间 （整型）
            String createTime = requestMap.get("CreateTime");
            logger.info("Date:{}", FormatDate.formatTime(createTime));

            // 消息类型
            String msgType = requestMap.get("MsgType");
            logger.info("msgType:" + msgType);

            StringBuilder text  = new StringBuilder();
            text.append("发送方账号(OpenID):").append(fromUserName);
            text.append("开发者微信号:").append(toUserName);
            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                // 文本消息内容
                String content = requestMap.get("Content");
                logger.info("content:" + content);
                text.append("文本消息内容:").append(content);
                EmailUtil.sendEmail("476214747@qq.com", "客服文本消息", text.toString());
            // 图片消息
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                // 取得图片地址
                String picUrl = requestMap.get("PicUrl");
                logger.info("PicUrl:" + picUrl);
                text.append("图片地址:").append(picUrl);
                EmailUtil.sendEmail("476214747@qq.com", "客服图片消息", text.toString());
                //点击客服事件
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                String eventType = requestMap.get("Event");
                logger.info("eventType:{}", eventType);
                text.append("事件类型:").append(eventType);
                EmailUtil.sendEmail("476214747@qq.com", "点击客服", text.toString());
            }
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            e.printStackTrace();
        }
        return respMessage;
    }
}
