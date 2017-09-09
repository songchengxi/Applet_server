package com.scx.applet.model.history;

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

    @Column(name = "dynasty", length = 20)
    public String dynasty;

    @Column(name = "time", length = 20)
    public String time;

    @Column(name = "capital", length = 20)
    public String capital;//都城

    @Column(name = "capital_now", length = 20)
    public String capitalNow;//都城现址

    @Column(name = "founder", length = 20)
    public String founder;//建立者

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
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

    @Override
    public String toString() {
        return "History{" +
                "id='" + id + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", time='" + time + '\'' +
                ", capital='" + capital + '\'' +
                ", capitalNow='" + capitalNow + '\'' +
                ", founder='" + founder + '\'' +
                '}';
    }
}
