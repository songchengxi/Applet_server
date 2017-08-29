package com.scx.applet.timetask.traffic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scx.applet.model.Traffic;
import com.scx.applet.repository.TrafficRepository;
import com.scx.util.FormatDate;
import com.scx.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Sichuan {

    private static final Logger log = LoggerFactory.getLogger(Sichuan.class);

    @Autowired
    private TrafficRepository trafficRepository;

    private static final String URL = "http://www.scjtonline.cn/findAllTrafficInfos";
    private static String result;
    private static JSONArray data;
    Pattern typeP = Pattern.compile("(?<=因).*(?=于)");

    public void getTraffic() throws IOException {
        result = HttpClientUtil.getResult(URL, "GET");
        data = JSONObject.parseArray(result);
        assert data != null;
        int count = 0;
        for (Object aData : data) {
            JSONObject j = (JSONObject) aData;
            String des = j.getString("des");
            Traffic one = trafficRepository.findOneByInfo(des);
            if (one == null) {
                Traffic t = new Traffic();
                t.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                t.setCity("51");
                t.setTime(FormatDate.formatDateToStr());
                t.setName(des.split(" ")[0]);
                Matcher typeM = typeP.matcher(des);
                while (typeM.find()) {
                    t.setType(typeM.group());
                }
                t.setInfo(des);
                System.out.println(t);
                trafficRepository.save(t);
                count++;
            }
        }
        result = null;
        data = null;
        log.info("四川新增" + count + "条数据");
    }
}
