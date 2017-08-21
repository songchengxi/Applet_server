package com.scx.applet.repository;

import com.scx.applet.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WeatherRepository extends JpaRepository<Weather, String>, JpaSpecificationExecutor<Weather> {

    Weather findOneByCityId(String cityId);
}
