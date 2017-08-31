package com.scx.applet.timetask.traffic;

import com.scx.applet.model.Traffic;
import com.scx.applet.repository.TrafficRepository;
import com.scx.util.HttpClientUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class Anhui {

    private static final Logger log = LoggerFactory.getLogger(Anhui.class);

    @Autowired
    private TrafficRepository trafficRepository;

    private static String URL;
    private static String result;
    private static Element document;

    public void getTraffic() throws IOException {
        URL = "http://60.166.52.120:8001/gzcx/sslk.do?type=3";
        result = HttpClientUtil.getResult(URL, "GET");
        document = Jsoup.parse(result);
        Elements list2 = document.getElementsByClass("list2").get(0).getElementsByTag("li");
        int count = 0;
        for (Element e : list2) {
            Elements a = e.getElementsByTag("a");
            String text = a.text();
            String date = e.getElementsByClass("date").get(0).text();
            String href = a.attr("href");
            URL = "http://60.166.52.120:8001/gzcx/" + href;
            result = HttpClientUtil.getResult(URL, "GET");
            document = Jsoup.parse(result);
            Element page = document.getElementsByClass("page").get(0);
            String p = page.getElementsByTag("p").get(1).text();
            Traffic one = trafficRepository.findOneByInfo(p);
            if (one == null) {
                Traffic t = new Traffic();
                t.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                t.setCity("34");
                t.setType("交通事故");
                t.setName(text);
                t.setTime(date);
                t.setInfo(p);
                trafficRepository.save(t);
                count++;
            }
        }
        result = null;
        document = null;
        log.info("安徽新增" + count + "条数据");
    }
}