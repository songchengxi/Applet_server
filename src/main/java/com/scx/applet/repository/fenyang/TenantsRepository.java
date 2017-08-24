package com.scx.applet.repository.fenyang;

import com.scx.applet.model.fenyang.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TenantsRepository extends JpaRepository<Tenants, String>, JpaSpecificationExecutor<Tenants> {
}
