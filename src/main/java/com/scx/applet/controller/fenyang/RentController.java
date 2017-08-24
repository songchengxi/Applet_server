package com.scx.applet.controller.fenyang;

import com.scx.applet.model.fenyang.Rent;
import com.scx.applet.repository.fenyang.RentRepository;
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
 * 出租
 */
@Controller
@RequestMapping("/rent")
public class RentController {

    private static final Logger log = LoggerFactory.getLogger(RentController.class);

    @Autowired
    private RentRepository rentRepository;

    @PostMapping("/findByPage")
    @ResponseBody
    public Object findByPage(int page) throws ParseException {
        Pageable pageable = new PageRequest(page, 10, new Sort(Sort.Direction.DESC, "time"));
        Specification<Rent> spec = new Specification<Rent>() {
            @Override
            public Predicate toPredicate(Root<Rent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return query.getRestriction();
            }
        };
        Page<Rent> all = rentRepository.findAll(spec, pageable);
        List<Rent> content = all.getContent();
        for (Rent r : content) {
            r.setTime(FormatDate.fromToday(r.getTime()));
        }
        return all;
    }

    @PostMapping("/add")
    @ResponseBody
    public Object add(Rent r) {
        return rentRepository.save(r);
    }
}
