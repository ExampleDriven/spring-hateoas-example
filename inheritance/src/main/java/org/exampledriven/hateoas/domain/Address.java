package org.exampledriven.hateoas.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class Address extends ResourceSupport {
    @JsonProperty("id")
    private int addressId;
    private String street;
    private int customerId;

    public Address(int addressId, String street, int customerId) {
        this.addressId = addressId;
        this.street = street;
        this.customerId = customerId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


}
