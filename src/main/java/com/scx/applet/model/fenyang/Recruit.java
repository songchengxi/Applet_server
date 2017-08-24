package com.scx.applet.model.fenyang;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 招聘
 */
@Entity
@Table(name = "recruit")
public class Recruit extends BaseEntity implements Serializable {

    @Column(name = "company", length = 50)
    private String company;//公司

    @Column(name = "position",length = 20)
    private String position;//职位

    @Column(name = "salary",length = 20)
    private String salary;//薪资

    @Column(name = "demand", length = 1000)
    private String demand;//要求

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

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

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    @Override
    public String toString() {
        return "招聘Recruit{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", salary='" + salary + '\'' +
                ", company='" + company + '\'' +
                ", demand='" + demand + '\'' +
                '}';
    }

}
