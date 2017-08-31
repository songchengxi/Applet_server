package com.scx.applet.timetask.traffic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scx.applet.model.Traffic;
import com.scx.applet.repository.TrafficRepository;
import com.scx.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class Jiangxi {

    private static final Logger log = LoggerFactory.getLogger(Jiangxi.class);

    @Autowired
    private TrafficRepository trafficRepository;

    private static final String URL = "http://218.65.87.14:8008/GzcxApi.aspx?key=haiwei602&Xw_state=1&Zc_tj=sslk&Zc_ys=0";
    private static String result;
    private static JSONObject jsonObject;
    private static JSONArray data;

    public void getTraffic() throws IOException {
        result = HttpClientUtil.getResult(URL, "GET");
        jsonObject = JSONObject.parseObject(result);
        Integer error_code = jsonObject.getInteger("error_code");
        int count = 0;
        if (0 == error_code) {
            data = jsonObject.getJSONObject("reason").getJSONArray("data");
            for (Object o : data) {
                JSONObject j = (JSONObject) o;
                String info = j.getString("F_CR_BIAOT");
                if (info.contains("天气预报")) {
                    continue;
                }
                Traffic one = trafficRepository.findOneByInfo(info);
                if (one == null) {
                    Traffic t = new Traffic();
                    t.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                    t.setCity("36");
                    t.setType("事故");
                    t.setName(info.split("高速")[0] + "高速");
                    //TODO
                    t.setTime(j.getString("F_CR_FABSJ").replaceAll("/", "-"));
                    t.setInfo(info);
                    trafficRepository.save(t);
                    count++;
                }
            }
        }
        result = null;
        jsonObject = null;
        data = null;
        log.info("江西新增" + count + "条数据");
    }
}