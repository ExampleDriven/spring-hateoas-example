package org.exampledriven.hateoas.domain;

import org.springframework.hateoas.ResourceSupport;

public class Customer extends ResourceSupport {
    private int customerId;
    private String firstName;
    private String lastName;

    public Customer(int customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
