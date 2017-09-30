package com.oscarmyomin.wordpressreader.model;

/**
 * Created by philippe on 9/30/17.
 */

public class Category {
    int id, count;
    String name;

    public Category() {
    }

    public Category(int id, int count, String name) {
        this.id = id;
        this.count = count;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
