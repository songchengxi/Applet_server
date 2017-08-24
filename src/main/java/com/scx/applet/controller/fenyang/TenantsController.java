package com.scx.applet.controller.fenyang;

import com.scx.applet.model.fenyang.Tenants;
import com.scx.applet.repository.fenyang.TenantsRepository;
import com.scx.util.FormatDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.util.List;

/**
 * 租房
 */
@Controller
@RequestMapping("/tenants")
public class TenantsController {

    private static final Logger log = LoggerFactory.getLogger(TenantsController.class);

    @Autowired
    private TenantsRepository tenantsRepository;

    @PostMapping("/findByPage")
    @ResponseBody
    public Object findByPage(int page) throws ParseException {
        Pageable pageable = new PageRequest(page, 10, new Sort(Sort.Direction.DESC, "time"));
        Specification<Tenants> spec = new Specification<Tenants>() {
            @Override
            public Predicate toPredicate(Root<Tenants> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return query.getRestriction();
            }
        };

        Page<Tenants> all = tenantsRepository.findAll(spec, pageable);
        List<Tenants> content = all.getContent();
        for (Tenants t : content) {
            t.setTime(FormatDate.fromToday(t.getTime()));
        }
        return all;
    }


    @PostMapping("/add")
    @ResponseBody
    public Object add(Tenants t) {
        return tenantsRepository.save(t);
    }
}
