package org.exampledriven.hateoas.resource;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.exampledriven.hateoas.domain.Customer;
import org.springframework.hateoas.ResourceSupport;

public class CustomerResource extends ResourceSupport {

    @JsonUnwrapped
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
