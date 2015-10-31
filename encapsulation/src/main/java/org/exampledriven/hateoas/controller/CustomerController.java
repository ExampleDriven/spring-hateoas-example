package org.exampledriven.hateoas.controller;

import org.exampledriven.hateoas.resource.CustomerListResource;
import org.exampledriven.hateoas.resource.CustomerResource;
import org.exampledriven.hateoas.domain.Customer;
import org.exampledriven.hateoas.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public CustomerListResource getCustomers() {

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

    private CustomerListResource customerToResource(List<Customer> customers) {

        CustomerListResource customerListResource = new CustomerListResource();
        customerListResource.add(linkTo(methodOn(CustomerController.class).getCustomers()).withSelfRel());

        List<CustomerResource> customerResources = customers.stream().map(CustomerController::customerToResource).collect(Collectors.toList());

        customerListResource.setCustomerResourceList(customerResources);

        return customerListResource;
    }

    private static CustomerResource customerToResource(Customer customer) {
        Link selfLink   = linkTo(methodOn(CustomerController.class).getCustomer(customer.getId())).withSelfRel();
        Link addressLink = ControllerLinkBuilder.linkTo(methodOn(AddressController.class).getAddressByCustomerId(customer.getId())).withRel("address");
        Link invoiceLink = linkTo(methodOn(InvoiceController.class).getInvoiceByCustomerId(customer.getId())).withRel("invoice");

        CustomerResource customerResource = new CustomerResource();
        customerResource.setCustomer(customer);
        customerResource.add(selfLink);
        customerResource.add(addressLink);
        customerResource.add(invoiceLink);

        return customerResource;

    }
}
