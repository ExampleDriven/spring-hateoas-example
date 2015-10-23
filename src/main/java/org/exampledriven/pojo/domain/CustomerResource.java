package org.exampledriven.pojo.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.exampledriven.resource.domain.Customer;
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
