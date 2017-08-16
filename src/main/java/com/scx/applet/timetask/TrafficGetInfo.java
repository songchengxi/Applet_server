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

/**
 * 获取数据
 */
@Component
public class TrafficGetInfo {

    private final static Logger log = LoggerFactory.getLogger(TrafficGetInfo.class);

    @Autowired
    private TrafficRepository trafficRepository;

    private static String result;
    private static Document document;
    private static Elements paging;

    @Scheduled(cron = "0 0/5 7-23 * * ?")
    public void getInfo() throws IOException {
        HttpClientUtil httpClient = new HttpClientUtil();
        String url = "http://www.sxgajj.gov.cn/web/index.php/map/index.html";
        result = httpClient.getResult(url, "get");
        document = Jsoup.parse(result);
        paging = document.getElementsByClass("paging");
        if (paging.size() <= 0) {
            log.info("====山西高速==数据错误=======");
            return;
        }
        paging = paging.get(0).getElementsByTag("a");

        String[] back = {"333333", "F30", "99CC00", "87AACB", "ff9f19"};
        String[] code = {"1", "2", "3", "4", "5"};
        int count = 0;
        for (Element e : paging) {
            Traffic one = trafficRepository.findOne(e.id());
            if (one != null) {
                log.info("山西新增" + count + "条数据");
                return;
            }

            String attr = e.getElementsByTag("span").get(0).attr("style");
            for (int i = 0; i < back.length; i++) {
                if (attr.contains(back[i])) {
                    attr = code[i];
                    break;
                }
            }
            Traffic t = new Traffic();
            t.setId(e.id());
            t.setCity("14");
            t.setType(attr);
            t.setTime(e.getElementsByTag("span").get(0).text());
            t.setInfo(e.text().substring(20));
            try {
                trafficRepository.save(t);
                count++;
            } catch (Exception er) {
                log.error("山西同步数据异常：{}；{}", er.getMessage(), t);
            }
        }
        log.info("山西新增" + count + "条数据");
    }

    @Scheduled(cron = "0 0/30 0-6 * * ?")
    public void getInfo2() throws IOException {
        getInfo();
    }
}
