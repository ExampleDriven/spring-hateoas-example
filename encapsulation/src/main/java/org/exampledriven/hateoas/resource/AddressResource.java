package org.exampledriven.hateoas.resource;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.exampledriven.hateoas.domain.Address;
import org.springframework.hateoas.ResourceSupport;

public class AddressResource extends ResourceSupport {

    @JsonUnwrapped
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
