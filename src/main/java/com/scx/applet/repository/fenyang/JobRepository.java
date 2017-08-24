package com.scx.applet.repository.fenyang;

import com.scx.applet.model.fenyang.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JobRepository extends JpaRepository<Job, String>, JpaSpecificationExecutor<Job> {
}
