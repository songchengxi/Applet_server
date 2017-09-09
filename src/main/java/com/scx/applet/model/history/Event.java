package com.scx.applet.model.history;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "history_event")
public class Event implements Serializable{

    @Id
    @Column(name = "id")
    public String id = UUID.randomUUID().toString().replaceAll("-","");

    @Column(name = "dynasty")
    public String dynasty;

    @Column(name = "sort")
    public String sort;

    @Column(name = "title")
    public String title;

    @Column(name = "content")
    public String content;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HistoryEvent{" +
                "id='" + id + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", sort='" + sort + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
