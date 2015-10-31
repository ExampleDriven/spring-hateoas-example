package org.exampledriven.hateoas.controller;

import org.exampledriven.hateoas.domain.Address;
import org.exampledriven.hateoas.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public Address getAddressByCustomerId(@RequestParam("customerId") int customerId) {

        Address address = addressService.getAddressByCustomerId(customerId);
        return addLinks(address);

    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
    public Address getAddressById(@PathVariable int id) {

        return addLinks(addressService.getAddressById(id));

    }

    private Address addLinks(Address address) {
        Link selfLink = linkTo(methodOn(AddressController.class).getAddressById(address.getAddressId())).withSelfRel();

        address.add(selfLink);

        return address;
    }
}
