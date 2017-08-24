package com.scx.applet.controller.fenyang;

import com.scx.applet.model.fenyang.Job;
import com.scx.applet.repository.fenyang.JobRepository;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

/**
 * 求职
 */
@Controller
@RequestMapping("/job")
public class JobController {

    private static final Logger log = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobRepository jobRepository;

    @PostMapping(value = "/findByPage")
    @ResponseBody
    public Object findByPage(int page) throws ParseException {
        Pageable pageable = new PageRequest(page, 10, new Sort(Sort.Direction.DESC, "time"));
        Specification<Job> spec = new Specification<Job>() {
            @Override
            public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return query.getRestriction();
            }
        };
        Page<Job> all = jobRepository.findAll(spec, pageable);
        List<Job> content = all.getContent();
        for (Job j : content) {
            j.setTime(FormatDate.fromToday(j.getTime()));

        }
        return all;
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Object add(Job job) throws UnsupportedEncodingException {
        job.setTime(FormatDate.formatDateToStr());
        return jobRepository.save(job);
    }
}
