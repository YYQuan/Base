package com.alphawizard.hdwallet.alphahdwallet.entity.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TestDBBean extends RealmObject {
    private String name;
    private int age;



    @PrimaryKey
    private String id;

    public TestDBBean() {
    }

    public TestDBBean(String id, String name, int age) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
