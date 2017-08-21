package com.scx.applet.timetask;

import com.alibaba.fastjson.JSONObject;
import com.scx.applet.model.Weather;
import com.scx.applet.repository.WeatherRepository;
import com.scx.applet.tools.weather.BaiduWeather;
import com.scx.util.FormatDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时更新天气
 */
@Component
public class WeatherTask {

    private static final Logger log = LoggerFactory.getLogger(WeatherTask.class);

    @Autowired
    private WeatherRepository weatherRepository;

    @Scheduled(cron = "0 0/5 0-23 * * ?")
    public void getInfo() {
        log.info("------WeatherGetInfo----------");
        JSONObject jsonObject = BaiduWeather.getWeather("汾阳");
        String error = jsonObject.getString("error");
        if ("0".equals(error)) {
            JSONObject today = (JSONObject) jsonObject.getJSONArray("results").getJSONObject(0).getJSONArray("weather_data").remove(0);
            String date = today.getString("date");
            String[] split = date.split("\\(");
            today.put("date", split[0].trim());
            today.put("now", split[1].replaceAll("[^-+.\\d]", ""));
            jsonObject.getJSONArray("results").getJSONObject(0).put("today", today);
            Weather weather = weatherRepository.findOneByCityId("142303");
            weather.setTime(FormatDate.formatDateToStr());
            weather.setInfo(jsonObject.getJSONArray("results").get(0).toString());
            weatherRepository.save(weather);
        }
    }
}
