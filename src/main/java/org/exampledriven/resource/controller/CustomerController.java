package org.exampledriven.resource.controller;

import org.exampledriven.resource.domain.Customer;
import org.exampledriven.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public Resources<Resource> getCustomers() {

        List<Link> links = new LinkedList<>();
        links.add(linkTo(methodOn(CustomerController.class).getCustomers()).withSelfRel());
        List<Resource> resources = customerToResource(customerService.getCustomers());

        return new Resources<>(resources, links);

    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public Resource getCustomer(@PathVariable int id) {

        return customerToResource(customerService.getCustomer(id));

    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable int id) {

        customerService.deleteCustomer(id);

    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public Resource createCustomer(@RequestBody Customer customer) {

        Customer customerCreated = customerService.createCustomer(customer);

        return customerToResource(customerCreated);

    }

    private List<Resource> customerToResource(List<Customer> customers) {

        List<Resource> resources = new ArrayList<>(customers.size());

        for (Customer customer : customers) {
            resources.add(customerToResource(customer));
        }

        return resources;
    }

    private Resource<Customer> customerToResource(Customer customer) {
        Link selfLink   = linkTo(methodOn(CustomerController.class).getCustomer(customer.getId())).withSelfRel();
        Link createLink = linkTo(methodOn(CustomerController.class).createCustomer(customer)).withRel("create");
        try {
            Link deleteLink = linkTo(CustomerController.class, CustomerController.class.getMethod("deleteCustomer", int.class), customer.getId()).withRel("delete");
            return new Resource<>(customer, selfLink, createLink, deleteLink);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
