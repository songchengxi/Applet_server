package com.scx.applet.timetask.traffic;

import com.scx.applet.model.Traffic;
import com.scx.applet.repository.TrafficRepository;
import com.scx.util.HttpClientUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class Shandong {

    private static final Logger log = LoggerFactory.getLogger(Shandong.class);

    @Autowired
    private TrafficRepository trafficRepository;

    private static final String URL = "http://www.sdjtcx.com/showTraffic.do?method=trafficIndex";
//    private static final String URL = "http://www.sdjtcx.com/xwloptiondispatch.do?actionFlag=view&lxid=3";
    private static String result;
    private static Document document;
    private static Elements li;

    public void getTraffic() throws IOException {
        result = HttpClientUtil.getResult(URL, "GET");
        document = Jsoup.parse(result);
        li = document.getElementsByClass("xxlist_ul").get(3).getElementsByTag("li");
        int count = 0;
        for (int i = 0; i < li.size(); i = i + 2) {
            String info = li.get(i).text();
            Traffic one = trafficRepository.findOneByInfo(info);
            if (one == null) {
                Traffic t = new Traffic();
                t.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                t.setCity("37");
                t.setType("突发事件");
                t.setName(info.split("高速")[0] + "高速");
                String time = li.get(i + 1).text().replaceAll("\\[", "").replaceAll("]", "");
                t.setTime(time.trim());
                t.setInfo(info);
                trafficRepository.save(t);
                count++;
            }
        }
        li = null;
        document = null;
        result = null;
        log.info("山东新增" + count + "条数据");
    }
}
