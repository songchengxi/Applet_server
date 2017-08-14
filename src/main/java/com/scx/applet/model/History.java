package com.scx.applet.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 历史朝代
 */
@Entity
@Table(name = "history")
public class History implements Serializable {

    @Id
    @Column(name = "id", length = 3)
    public String id;

    @Column(name = "name", length = 20)
    public String name;

    @Column(name = "time", length = 20)
    public String time;

    @Column(name = "capital", length = 20)
    public String capital;//都城

    @Column(name = "capital_now", length = 20)
    public String capitalNow;//都城现址

    @Column(name = "founder", length = 20)
    public String founder;//建立者

    @Column(name = "introduction", length = 300)
    public String introduction;//简介

    @Column(name = "territory", length = 50)
    public String territory;//疆域

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCapitalNow() {
        return capitalNow;
    }

    public void setCapitalNow(String capitalNow) {
        this.capitalNow = capitalNow;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    @Override
    public String toString() {
        return "History{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", capital='" + capital + '\'' +
                ", capitalNow='" + capitalNow + '\'' +
                ", founder='" + founder + '\'' +
                ", introduction='" + introduction + '\'' +
                ", territory='" + territory + '\'' +
                '}';
    }
}
