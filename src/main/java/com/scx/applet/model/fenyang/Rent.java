package com.scx.applet.model.fenyang;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 出租
 */
@Entity
@Table(name = "rent")
public class Rent extends BaseEntity implements Serializable {

    @Column(name="address",length = 50)
    private String address;//地址

    @Column(name = "rental",length = 10)
    private String rental;//租金

    @Column(name = "content", length = 1000)
    private String content;//内容

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRental() {
        return rental;
    }

    public void setRental(String rental) {
        this.rental = rental;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "出租Rent{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", rental='" + rental + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
