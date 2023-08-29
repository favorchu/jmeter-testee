package com.example.testee.controller;

public class Order {
    private int id;
    private String name;

    private String payload;

    public Order() {
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Order(int id, String name, String payload) {
        this.id = id;
        this.name = name;
        this.payload = payload;
    }

    public long getId() {
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
}
