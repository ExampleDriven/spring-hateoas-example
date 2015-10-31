package org.exampledriven.hateoas.domain;

import java.util.List;

public class Invoice {
    private int id;
    private int price;
    private String title;
    private int customerId;

    public Invoice(int id, int price, String title, int customerId) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.customerId = customerId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
