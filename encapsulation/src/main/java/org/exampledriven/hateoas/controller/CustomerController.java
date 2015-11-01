package org.exampledriven.hateoas.controller;

import org.exampledriven.hateoas.controller.assembler.CustomerResourceAssembler;
import org.exampledriven.hateoas.resource.CustomerListResource;
import org.exampledriven.hateoas.resource.CustomerResource;
import org.exampledriven.hateoas.domain.Customer;
import org.exampledriven.hateoas.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/customer", produces = "application/hal+json")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private CustomerResourceAssembler customerResourceAssembler = new CustomerResourceAssembler();

    @RequestMapping(method = RequestMethod.GET)
    public CustomerListResource getCustomers() {

        return customerToResource(customerService.getCustomers());

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public CustomerResource getCustomer(@PathVariable int id) {

        return customerToResource(customerService.getCustomer(id));

    }

    private CustomerListResource customerToResource(List<Customer> customers) {

        CustomerListResource customerListResource = new CustomerListResource();
        customerListResource.add(linkTo(methodOn(CustomerController.class).getCustomers()).withSelfRel());

        List<CustomerResource> customerResources = customerResourceAssembler.toResources(customers);

        customerListResource.setCustomerResourceList(customerResources);

        return customerListResource;

    }

    private CustomerResource customerToResource(Customer customer) {

        return customerResourceAssembler.toResource(customer);
    }
}
