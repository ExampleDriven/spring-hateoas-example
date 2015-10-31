package org.exampledriven.hateoas.controller;

import org.exampledriven.hateoas.resource.AddressResource;
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
    public AddressResource getAddressByCustomerId(@RequestParam("customerId") int customerId) {

        Address address = addressService.getAddressByCustomerId(customerId);
        return addressToResource(address, address.getId());

    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
    public AddressResource getAddressById(@PathVariable int id) {

        return addressToResource(addressService.getAddressById(id), id);

    }

    private AddressResource addressToResource(Address address, int id) {
        Link selfLink = linkTo(methodOn(AddressController.class).getAddressById(id)).withSelfRel();

        AddressResource addressResource = new AddressResource();
        addressResource.setAddress(address);
        addressResource.add(selfLink);

        return addressResource;
    }
}
