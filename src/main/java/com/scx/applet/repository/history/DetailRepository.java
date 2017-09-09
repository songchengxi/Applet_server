package com.scx.applet.repository.history;

import com.scx.applet.model.history.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DetailRepository extends JpaRepository<Detail, String>, JpaSpecificationExecutor<Detail> {

}