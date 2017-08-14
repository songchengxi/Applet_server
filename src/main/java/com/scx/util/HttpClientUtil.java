package com.scx.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpClientUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    private HttpClient client;

    public HttpClientUtil() {
        client = HttpClientBuilder.create().build();
    }

    public String getResult(String url, String method) throws IOException {
        log.info("解析地址:" + url);
        HttpRequestBase base;
        if ("post".equals(method) || "POST".equals(method)) {
            base = new HttpPost(url);
            log.info("请求方式:POST");
        } else {
            base = new HttpGet(url);
            log.info("请求方式:GET");
        }
        base.removeHeaders("Address");
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36";
        base.addHeader("LoginUser-Agent", userAgent);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();//设置请求和传输超时时间
        base.setConfig(requestConfig);
        HttpResponse response;
        try {
            response = client.execute(base);
        } catch (Exception e) {
            log.info("请求异常====" + e.getMessage());
            return "请求失败";
        }
        int statusCode = response.getStatusLine().getStatusCode();//状态码
        log.info("请求状态:" + statusCode);
        if (statusCode != 200) {
            String error = statusCode + "";
            log.error(url + "请求错误，错误编码：" + error);
        }
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String returnStr = EntityUtils.toString(entity, "utf-8");
            log.info("解析结果:" + returnStr);
            return returnStr;
        } else {
            log.info("解析结果为空");
            return null;
        }
    }
}