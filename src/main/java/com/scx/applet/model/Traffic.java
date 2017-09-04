package com.scx.applet.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "traffic")
public class Traffic implements Serializable{

    @Id
    @Column(name = "id", length = 36)
    public String id;

    @Column(name = "city",length = 2)
    public String city;//城市

    @Column(name = "name", length = 100)
    public String name;

    @Column(name = "type", length = 50)
    public String type;//1：事故；2：拥堵；3：管制、施工；4：封闭；5：缓慢

    @Column(name = "time", length = 20)
    public String time;

    @Column(name = "info", length = 1000)
    public String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Traffic{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", time='" + time + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
