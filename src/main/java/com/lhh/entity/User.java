package com.lhh.entity;

import javax.persistence.*;

/**
 * @author lhh
 * @date 2019/5/26 11:41
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
