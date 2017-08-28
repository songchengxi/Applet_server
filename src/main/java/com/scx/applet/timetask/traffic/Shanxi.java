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

/**
 * 陕西
 */
@Component
public class Shanxi {

    private static final Logger log = LoggerFactory.getLogger(Shanxi.class);

    @Autowired
    private TrafficRepository trafficRepository;

    private static final String url = "http://www.sxsglj.cn/api/getlukuang.ashx";
    private static String result;
    private static JSONObject jsonObject;
    private static JSONArray data;

    public void getTraffic() throws IOException {
        result = HttpClientUtil.getResult(url, "GET");
        jsonObject = JSONObject.parseObject(result);
        data = jsonObject.getJSONArray("data");
        int count = 0;
        for (int i = 0; i < data.size(); i++) {
            JSONObject j = (JSONObject) data.get(i);
            Traffic one = trafficRepository.findOneByInfo(j.getString("detail"));
            if (one == null) {
                count++;
                Traffic t = new Traffic();
                t.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                t.setCity("61");
                t.setName(j.getString("roadname"));
                t.setType(j.getString("infotype"));
                t.setTime(j.getString("startdate"));
                t.setInfo(j.getString("detail"));
                trafficRepository.save(t);
            }
        }
        result = null;
        jsonObject = null;
        data = null;
        log.info("陕西新增" + count + "条数据");
    }
}
