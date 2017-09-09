package com.scx.applet.repository.history;

import com.scx.applet.model.history.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, String>, JpaSpecificationExecutor<Event> {

    List<Event> findByDynastyOrderBySortAsc(String id);
}
