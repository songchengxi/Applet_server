package com.scx.applet.controller;

import com.scx.applet.dao.TrafficDao;
import com.scx.applet.model.Traffic;
import com.scx.applet.repository.TrafficRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.Map;

/**
 * 高速路况
 */
@Controller("trafficController")
@RequestMapping("/traffic")
public class TrafficController {

    @Autowired
    private TrafficDao trafficDao;

    @Autowired
    private TrafficRepository trafficRepository;

    @GetMapping("/index")
    public String index() {
        return "/traffic/list";
    }

    /**
     * 获取全部
     */
    @PostMapping("/findAll")
    @ResponseBody
    public Object findAll() {
        return trafficRepository.findAll();
    }

    /**
     * 分页获取数据，从0开始
     */
    @PostMapping("/findByPage")
    @ResponseBody
    public Page<Traffic> findByPage(int page, String city) {
        Pageable pageable = new PageRequest(page, 10, new Sort(Sort.Direction.DESC, "time"));
        Specification<Traffic> spec = new Specification<Traffic>() {
            @Override
            public Predicate toPredicate(Root<Traffic> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return query.where(cb.equal(root.get("city").as(String.class), "14")).getRestriction();
            }
        };
        return trafficRepository.findAll(spec, pageable);
    }

    /**
     * 获取对象
     */
    @PostMapping("/get")
    @ResponseBody
    public Object get(String id) {
        return trafficRepository.findOne(id);
    }

    /**
     * 持久化
     */
    @PostMapping("/save")
    @ResponseBody
    public Map save(@RequestBody Traffic entity) {
        Map<String, Object> map = new HashMap<String, Object>();
        entity = trafficRepository.save(entity);
        map.put("result", entity.id);
        return map;
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ResponseBody
    public Map delete(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        trafficRepository.delete(id);
        map.put("id", id);
        return map;
    }
}