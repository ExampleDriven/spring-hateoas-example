package org.exampledriven.hateoas.resource;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class CustomerListResource extends ResourceSupport {

    @JsonUnwrapped
    private List<CustomerResource> customerResourceList;

    public List<CustomerResource> getCustomerResourceList() {
        return customerResourceList;
    }

    public void setCustomerResourceList(List<CustomerResource> customerResourceList) {
        this.customerResourceList = customerResourceList;
    }
}
