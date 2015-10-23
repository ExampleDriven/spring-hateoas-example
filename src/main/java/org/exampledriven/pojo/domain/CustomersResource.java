package org.exampledriven.pojo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.exampledriven.resource.domain.Customer;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@JsonPropertyOrder({"_links", "customerList"})
public class CustomersResource extends ResourceSupport {

    @JsonProperty("customerList")
    List<CustomerResource> customerResourceList;

    public List<CustomerResource> getCustomerResourceList() {
        return customerResourceList;
    }

    public void setCustomerResourceList(List<CustomerResource> customerResourceList) {
        this.customerResourceList = customerResourceList;
    }
}
