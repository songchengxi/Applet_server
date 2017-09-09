package com.scx.applet.repository.history;

import com.scx.applet.model.history.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, String>, JpaSpecificationExecutor<History> {

    List<History> findAllByOrderByIdAsc();

}
