package com.scx.applet.dao;

import com.scx.applet.model.Traffic;
import com.scx.applet.repository.TrafficRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JPA支持事务
 */
@Repository("trafficDao")
@Transactional(readOnly = false, rollbackFor = Throwable.class)
public class TrafficDao {

    @Autowired
    private TrafficRepository trafficRepository;

    public List<Traffic> findAll() {
        return trafficRepository.findAll();
    }
}
