package org.exampledriven.hateoas.domain;

public class Address {
    private int id;
    private String street;
    private int customerId;

    public Address(int id, String street, int customerId) {
        this.id = id;
        this.street = street;
        this.customerId = customerId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


}
