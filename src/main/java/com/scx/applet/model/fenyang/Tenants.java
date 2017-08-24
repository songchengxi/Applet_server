package com.scx.applet.model.fenyang;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 租房
 */
@Entity
@Table(name = "tenants")
public class Tenants extends BaseEntity implements Serializable {

    @Column(name = "content", length = 1000)
    private String content;//内容

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "租房Tenants{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", phone='" + phone + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
