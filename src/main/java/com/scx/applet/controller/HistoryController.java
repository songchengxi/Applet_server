package com.scx.applet.controller;

import com.scx.applet.model.history.Detail;
import com.scx.applet.model.history.Emperor;
import com.scx.applet.model.history.Event;
import com.scx.applet.model.history.History;
import com.scx.applet.repository.history.DetailRepository;
import com.scx.applet.repository.history.EmperorRepository;
import com.scx.applet.repository.history.EventRepository;
import com.scx.applet.repository.history.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 历史朝代
 */
@Controller("historyController")
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private DetailRepository detailRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EmperorRepository emperorRepository;

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

    @PostMapping("/findAllDetail")
    @ResponseBody
    public Object findAllDetail(String id) {
        Detail detail = detailRepository.findOne(id);
        List<Event> events = eventRepository.findByDynastyOrderBySortAsc(id);
        List<Emperor> emperors = emperorRepository.findByDynastyOrderBySortAsc(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("detail", detail);
        map.put("events", events);
        map.put("emperors", emperors);
        return map;
    }

    /**
     * 获取详细信息
     */
    @PostMapping("/getDetail")
    @ResponseBody
    public Object getDetail(String id) {
        return detailRepository.findOne(id);
    }

    /**
     * 保存详细信息
     */
    @PostMapping("/saveDetail")
    @ResponseBody
    public Map saveDetail(@RequestBody Detail t) {
        Map<String, Object> map = new HashMap<>();
        t = detailRepository.save(t);
        map.put("result", t.id);
        return map;
    }

    /**
     * 获取历史事件
     */
    @PostMapping("/getEvent")
    @ResponseBody
    public Object getEvent(String id) {
        return eventRepository.findByDynastyOrderBySortAsc(id);
    }

    /**
     * 保存历史事件
     */
    @PostMapping("/saveEvent")
    @ResponseBody
    public Map saveEvent(@RequestBody Event t) {
        Map<String, Object> map = new HashMap<>();
        t = eventRepository.save(t);
        map.put("result", t.id);
        return map;
    }

    @PostMapping("/getEventById")
    @ResponseBody
    public Object getEventById(String id) {
        return eventRepository.findOne(id);
    }

    @PostMapping("/deleteEventById")
    @ResponseBody
    public Map deleteEventById(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        eventRepository.delete(id);
        map.put("id", id);
        return map;
    }

    /**
     * 获取帝王信息
     */
    @PostMapping("/getEmperor")
    @ResponseBody
    public Object getEmperor(String id) {
        return emperorRepository.findByDynastyOrderBySortAsc(id);
    }

    /**
     * 保存帝王信息
     */
    @PostMapping("/saveEmperor")
    @ResponseBody
    public Map saveEmperor(@RequestBody Emperor t) {
        Map<String, Object> map = new HashMap<>();
        t = emperorRepository.save(t);
        map.put("result", t.getId());
        return map;
    }

    @PostMapping("/getEmperorById")
    @ResponseBody
    public Object getEmperorById(String id) {
        return emperorRepository.findOne(id);
    }

    @PostMapping("/deleteEmperorById")
    @ResponseBody
    public Map deleteEmperorById(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        emperorRepository.delete(id);
        map.put("id", id);
        return map;
    }
}
