package com.scx.applet.repository;

import com.scx.applet.model.Traffic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TrafficRepository extends JpaRepository<Traffic, String>, JpaSpecificationExecutor<Traffic> {

    Traffic findOneByInfo(String text);
}
