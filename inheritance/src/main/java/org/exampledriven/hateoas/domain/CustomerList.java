package org.exampledriven.hateoas.domain;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class CustomerList extends ResourceSupport {

    List<Customer> customerList;

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
