package com.scx.applet.controller;

import com.scx.applet.model.History;
import com.scx.applet.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 历史朝代
 */
@Controller("historyController")
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository;

    @GetMapping("/index")
    public String index() {
        return "/history/list";
    }

    /**
     * 获取全部
     */
    @PostMapping("/findAll")
    @ResponseBody
    public Object findAll() {
        return historyRepository.findAllByOrderByIdAsc();
    }

    /**
     * 分页获取数据，从0开始
     */
    @PostMapping("/findByPage")
    @ResponseBody
    public Object findByPage(int page) {
        PageRequest pageable = new PageRequest(page, 10, new Sort(Sort.Direction.DESC, "id"));
        return historyRepository.findAll(pageable);
    }

    /**
     * 获取对象
     */
    @PostMapping("/get")
    @ResponseBody
    public Object get(String id) {
        return historyRepository.findOne(id);
    }

    /**
     * 持久化
     */
    @PostMapping("/save")
    @ResponseBody
    public Map save(@RequestBody History entity) {
        Map<String, Object> map = new HashMap<String, Object>();
        entity = historyRepository.save(entity);
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
        historyRepository.delete(id);
        map.put("id", id);
        return map;
    }
}
