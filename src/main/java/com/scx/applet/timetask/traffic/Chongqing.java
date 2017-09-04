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
import java.util.Calendar;
import java.util.UUID;

@Component
public class Chongqing {

    private static final Logger log = LoggerFactory.getLogger(Chongqing.class);

    @Autowired
    private TrafficRepository trafficRepository;

    private static String URL = "http://cx.cqjt.gov.cn/zjcx/getControlListroadCtrlNews.html";
    private static String result;
    private static JSONArray data;

    public void getTraffic() throws IOException {
        result = HttpClientUtil.getResult(URL, "GET");
        data = JSONObject.parseObject(result).getJSONArray("data");
        int count = 0;
        for (int i = 0; i < data.size(); i++) {
            JSONObject o = (JSONObject) data.get(i);
            String info = o.getString("info");
            Traffic one = trafficRepository.findOneByInfo(info);
            if (one == null) {
                Traffic t = new Traffic();
                t.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                t.setCity("50");
                t.setName(o.getString("subject"));
                t.setTime(Calendar.getInstance().get(Calendar.YEAR) + "-" + o.getString("createdTime") + ":00");
                t.setType(o.getString("eventType"));
                t.setInfo(info);
                trafficRepository.save(t);
                count++;
            }
        }
        result = null;
        data = null;
        log.info("重庆新增" + count + "条数据");
    }
}