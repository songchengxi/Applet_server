package com.scx.applet.timetask.traffic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scx.applet.model.Traffic;
import com.scx.applet.repository.TrafficRepository;
import com.scx.util.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 湖南
 */
@Component
public class Hunan {

    private static final Logger log = LoggerFactory.getLogger(Hunan.class);

    @Autowired
    private TrafficRepository trafficRepository;

    private String result;
    private JSONArray data;

    private static final String url = "http://hunangstapi.u-road.com/HuNanWebSite/index.php?/SqlServer/getTrafficMessage";

    public void getTraffic() throws IOException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("eventtype", "1");//1：为获取实时路况；1006002：施工事件；
        params.put("index", "1");
        params.put("roadlineid", "0");
        params.put("size", "10");
        result = HttpRequest.net(url, params, "POST");
        data = JSONObject.parseObject(result).getJSONArray("data");
        params.put("eventtype", "1006002");
        result = HttpRequest.net(url, params, "POST");
        JSONArray constructionData = JSONObject.parseObject(result).getJSONArray("data");
        data.addAll(constructionData);
        int count = 0;
        for (int i = 0; i < data.size(); i++) {
            JSONObject j = (JSONObject) data.get(i);
            Traffic one = trafficRepository.findOneByInfo(j.getString("reportout"));
            if (one == null) {
                count++;
                Traffic t = new Traffic();
                t.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                t.setCity("43");
                if (j.getString("shortname").contains("（")) {
                    t.setName(j.getString("shortname").split("（")[0]);
                } else {
                    t.setName(j.getString("shortname"));
                }
                t.setTime(j.getString("updatetime"));
                t.setType(j.getString("eventcausename"));
                t.setInfo(j.getString("reportout"));
                trafficRepository.save(t);
            }
        }
        result = null;
        data = null;
        log.info("湖南新增" + count + "条数据");
    }
}
