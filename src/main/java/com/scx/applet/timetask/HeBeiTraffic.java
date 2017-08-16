package com.scx.applet.timetask;

import com.scx.applet.model.Traffic;
import com.scx.applet.repository.TrafficRepository;
import com.scx.util.HttpClientUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class HeBeiTraffic {

    private static final Logger log = LoggerFactory.getLogger(HeBeiTraffic.class);

    @Autowired
    private TrafficRepository trafficRepository;

    Pattern idP = Pattern.compile("(?<=\\(').*(?='\\))");
    Pattern dateP = Pattern.compile("(?<=【).*(?=.0】)");
    Pattern typeP = Pattern.compile("(?<=因\\().*(?=\\))");

    private static String result;
    private static Document document;
    private static Element scrollDiv;

    @Scheduled(cron = "0 0/5 7-23 * * ?")
    public void getInfo() throws IOException {
        HttpClientUtil client = new HttpClientUtil();
        String url = "http://www.hb96122.com/indexAction.do?method=index";
        result = client.getResult(url, "GET");
        document = Jsoup.parse(result);
        scrollDiv = document.getElementById("scrollDiv");
        if (scrollDiv == null) {
            result = client.getResult(url, "GET");
            document = Jsoup.parse(result);
            scrollDiv = document.getElementById("scrollDiv");
        }
        Elements a = scrollDiv.getElementsByTag("a");

        int count = 0;
        String id = "";
        for (Element e : a) {
            String onclick = e.attr("onclick");
            Matcher m = idP.matcher(onclick);
            while (m.find()) {
                id = m.group();
            }
            Traffic one = trafficRepository.findOne(id);
            if (one != null) {
                log.info("河北新增" + count + "条数据");
                return;
            }

            Traffic t = new Traffic();
            t.setId(id);
            t.setCity("13");

            Matcher matcher = dateP.matcher(e.text());
            while (matcher.find()) {
                t.setTime(matcher.group());
            }
            t.setInfo(e.text().split("【")[0]);

            Matcher typeM = typeP.matcher(e.text());
            while (typeM.find()) {
                t.setType(typeM.group());
            }
            if (t.getType() == null) {
                t.setType("恢复正常");
            }
            try {
                trafficRepository.save(t);
                count++;
            } catch (Exception exception) {
                log.error("河北同步数据异常：{}；{}", exception.getMessage(), t);
            }
        }
        log.info("河北新增" + count + "条数据");
    }

    @Scheduled(cron = "0 0/30 0-6 * * ?")
    public void getInfo2() throws IOException {
        getInfo();
    }
}
