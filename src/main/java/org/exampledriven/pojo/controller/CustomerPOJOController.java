package org.exampledriven.pojo.controller;

import org.exampledriven.pojo.domain.CustomerResource;
import org.exampledriven.pojo.domain.CustomersResource;
import org.exampledriven.resource.domain.Customer;
import org.exampledriven.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/pojo", produces = "application/hal+json")
class CustomerPOJOController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public CustomersResource getCustomers() {

        return customerToResource(customerService.getCustomers());

    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public CustomerResource getCustomer(@PathVariable int id) {

        return customerToResource(customerService.getCustomer(id));

    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable int id) {

        customerService.deleteCustomer(id);

    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public CustomerResource createCustomer(@RequestBody Customer customer) {

        Customer customerCreated = customerService.createCustomer(customer);

        return customerToResource(customerCreated);

    }

    private CustomersResource customerToResource(List<Customer> customers) {

        CustomersResource customersResource = new CustomersResource();
        customersResource.add(linkTo(methodOn(CustomerPOJOController.class).getCustomers()).withSelfRel());

        List<CustomerResource> customerResources = new ArrayList<>(customers.size());

        for (Customer customer : customers) {
            customerResources.add(customerToResource(customer));
        }

        customersResource.setCustomerResourceList(customerResources);

        return customersResource;
    }

    private CustomerResource customerToResource(Customer customer) {
        Link selfLink   = linkTo(methodOn(CustomerPOJOController.class).getCustomer(customer.getId())).withSelfRel();
        Link createLink = linkTo(methodOn(CustomerPOJOController.class).createCustomer(customer)).withRel("create");
        try {
            Link deleteLink = linkTo(CustomerPOJOController.class, CustomerPOJOController.class.getMethod("deleteCustomer", int.class), customer.getId()).withRel("delete");

            CustomerResource customerResource = new CustomerResource();
            customerResource.setCustomer(customer);

            customerResource.add(selfLink);
            customerResource.add(createLink);
            customerResource.add(deleteLink);

            return customerResource;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
