package com.scx.applet.controller;

import com.scx.applet.model.Weather;
import com.scx.applet.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 获取天气信息
 */
@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherRepository weatherRepository;

    @PostMapping(value = "/getWeather")
    @ResponseBody
    public Object getWeather(final String cityId) {
        Weather weather = weatherRepository.findOneByCityId("142303");
        return weather.getInfo();
    }
}