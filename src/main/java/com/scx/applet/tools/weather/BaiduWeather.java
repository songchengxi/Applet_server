package com.scx.applet.tools.weather;

import com.alibaba.fastjson.JSONObject;
import com.scx.util.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author scx
 * @date 2017/4/19
 * @desc 百度天气   总请求数: 0.5万次/天
 */
public class BaiduWeather {

    private static final String AK = "d3L2fPy0CRqmqimlwl2tc25qdcNOrYih";

    private static final Logger log = LoggerFactory.getLogger(BaiduWeather.class);

    public static JSONObject getWeather(String city) {
        String url = "http://api.map.baidu.com/telematics/v3/weather";
        Map<String, String> params = new HashMap<>();
        params.put("location", city);
        params.put("output", "json");
        params.put("ak", AK);
        String json = HttpRequest.net(url, params, "GET");
        return JSONObject.parseObject(json);
    }
}
