package com.scx.applet.model.fenyang;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 求职
 */
@Entity
@Table(name = "job")
public class Job extends BaseEntity implements Serializable {

    @Column(name = "position", length = 20)
    private String position;//职位

    @Column(name = "salary", length = 20)
    private String salary;//薪资

    @Column(name = "content", length = 1000)
    private String content;//内容

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "求职Job{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", salary='" + salary + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
