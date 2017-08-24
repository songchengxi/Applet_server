package com.scx.applet.repository.fenyang;

import com.scx.applet.model.fenyang.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RentRepository extends JpaRepository<Rent, String>, JpaSpecificationExecutor<Rent> {
}
