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

@Component
public class Zhejiang {

    private static final Logger log = LoggerFactory.getLogger(Zhejiang.class);

    @Autowired
    private TrafficRepository trafficRepository;

//    private static final String URL = "http://www.zjetc.cn/Module/Handles/HandAshx/commonAshx.ashx?strFlag=timeRoad";
    private static final String URL = "http://gzcx.zjt.gov.cn/AjaxProxy/GetTrafficInfo.ashx";
    private static String result;
    private static JSONArray array;

    public void getTraffic() throws IOException {
        result = HttpClientUtil.getResult(URL, "GET");
        array = JSONObject.parseObject(result).getJSONArray("rows");
        int count = 0;
        for (Object anArray : array) {
            JSONObject j = (JSONObject) anArray;
            Traffic one = trafficRepository.findOneByInfo(j.getString("cn_all_gs_name") + j.getString("context"));
            if (one == null) {
                count++;
                Traffic t = new Traffic();
                t.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                t.setCity("33");
                t.setTime(FormatDate.formatDateToStr());
                t.setType(j.getString("reason"));
                t.setName(j.getString("gs_name") + j.getString("cn_gs_name") + "高速");
                t.setInfo(j.getString("cn_all_gs_name") + j.getString("context"));
                trafficRepository.save(t);
            }
        }
        result = null;
        array = null;
        log.info("浙江新增" + count + "条数据");
    }
}
