package com.scx.applet.repository.history;

import com.scx.applet.model.history.Emperor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EmperorRepository extends JpaRepository<Emperor, String>, JpaSpecificationExecutor<Emperor> {

    List<Emperor> findByDynastyOrderBySortAsc(String id);
}
