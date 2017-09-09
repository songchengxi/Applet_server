package com.scx.applet.model.history;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "history_detail")
public class Detail implements Serializable {

    @Id
    @Column(name = "id")
    public String id;

    @Column(name = "profile")
    public String profile;//简介

    @Column(name = "territory")
    public String territory;//疆域

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    @Override
    public String toString() {
        return "HistoryDetail{" +
                "id='" + id + '\'' +
                ", profile='" + profile + '\'' +
                ", territory='" + territory + '\'' +
                '}';
    }
}
