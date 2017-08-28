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
public class Hubei {

    private static final Logger log = LoggerFactory.getLogger(Hubei.class);

    @Autowired
    private TrafficRepository trafficRepository;

    private static String url = "http://www.02712122.com/index.php?/home/loadThreeTabMsg";
    private static String result;

    public void getTraffic() throws IOException {
        result = HttpClientUtil.getResult(url, "POST");
        JSONObject jsonObject = JSONObject.parseObject(result);
        int count = 0;
        JSONArray planWork = jsonObject.getJSONArray("planWork");
        for (int i = 0; i < planWork.size(); i++) {
            JSONObject o = (JSONObject) planWork.get(i);
            Traffic one = trafficRepository.findOneByInfo(o.getString("reportout"));
            if (one == null) {
                count++;
                Traffic t = new Traffic();
                t.setId(UUID.randomUUID().toString().replaceAll("-",""));
                t.setCity("42");
                t.setType("施工养护");
                if (o.getString("title").contains("（")){
                    t.setName(o.getString("title").split("（")[0]);
                } else {
                    t.setName(o.getString("title"));
                }
                t.setTime(o.getString("intime"));
                t.setInfo(o.getString("reportout"));
                trafficRepository.save(t);
            }
        }

        JSONArray roadEvent = jsonObject.getJSONArray("roadEvent");
        for (int i = 0; i < roadEvent.size(); i++) {
            JSONObject o = (JSONObject) roadEvent.get(i);
            Traffic one = trafficRepository.findOneByInfo(o.getString("reportout"));
            if (one == null) {
                count++;
                Traffic t = new Traffic();
                t.setId(UUID.randomUUID().toString().replaceAll("-",""));
                t.setCity("42");
                t.setType("事故、施工");
                if (o.getString("title").contains("（")){
                    t.setName(o.getString("title").split("（")[0]);
                } else {
                    t.setName(o.getString("title"));
                }
                t.setTime(o.getString("intime"));
                t.setInfo(o.getString("reportout"));
                trafficRepository.save(t);
            }
        }
        result = null;
        log.info("湖北新增" + count + "条数据");
    }
}