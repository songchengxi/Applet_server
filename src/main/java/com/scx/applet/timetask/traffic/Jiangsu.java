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
public class Jiangsu {

    private static final Logger log = LoggerFactory.getLogger(Jiangsu.class);

    @Autowired
    private TrafficRepository trafficRepository;

    private static String url = "http://www.js96777.com/pubService/conditionList.htm";
    private static String result;
    private static Document document;
    private static Elements trs;

    public void getTraffic() throws IOException {
        result = HttpClientUtil.getResult(url, "get");
        document = Jsoup.parse(result);
        trs = document.getElementsByTag("table").get(1).getElementsByTag("tr");

        int count = 0;
        for (int i = 1; i < trs.size(); i++) {
            Elements td = trs.get(i).getElementsByTag("td");
            Traffic one = trafficRepository.findOneByInfo(td.get(6).text());
            if (one == null) {
                Traffic t = new Traffic();
                t.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                t.setCity("32");
                t.setName(td.get(1).text());
                t.setType(td.get(2).text());
                t.setTime(td.get(5).text() + ":00");
                t.setInfo(td.get(6).text());
                try {
                    trafficRepository.save(t);
                    count++;
                } catch (Exception er) {
                    log.error("江苏同步数据异常：{}；{}", er.getMessage(), t);
                }
            }
        }
        result = null;
        document = null;
        trs = null;
        log.info("江苏新增" + count + "条数据");
    }
}