package com.scx.applet.controller;

import com.scx.applet.service.CoreService;
import com.scx.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @author scx
 * @date 2017/8/5
 * @desc 小程序
 */
@Controller
@RequestMapping("/applet")
public class AppletController {

    private static final Logger log = LoggerFactory.getLogger(AppletController.class);

    /**
     * 确认请求来自微信服务器
     */
    @GetMapping
    @ResponseBody
    public String doGet(HttpServletRequest request){
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随即字符串
        String echostr = request.getParameter("echostr");

        log.info("signature={};timestamp={};nonce={};echostr={};", signature, timestamp, nonce, echostr);

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return null;
    }

    /**
     * 处理微信服务器发来的消息
     */
    @PostMapping
    @ResponseBody
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        // 将请求，响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 调用核心业务类接收消息，处理消息
        String respMessage = CoreService.processRequest(request);
        log.info("respMessage:{}", respMessage);
        // 响应消息
        return respMessage;
    }
}
