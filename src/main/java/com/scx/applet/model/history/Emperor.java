package com.scx.applet.model.history;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "history_emperor")
public class Emperor {

    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString().replaceAll("-", "");

    @Column(name = "dynasty")
    private String dynasty;

    @Column(name = "sort")
    private String sort;

    @Column(name = "name")
    private String name;

    @Column(name = "introduce")
    private String introduce;//介绍

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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "HistoryEmperor{" +
                "id='" + id + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", sort='" + sort + '\'' +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
