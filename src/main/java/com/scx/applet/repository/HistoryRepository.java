package com.scx.applet.repository;

import com.scx.applet.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, String>, JpaSpecificationExecutor<History> {

    List<History> findAllByOrderByIdAsc();

}
