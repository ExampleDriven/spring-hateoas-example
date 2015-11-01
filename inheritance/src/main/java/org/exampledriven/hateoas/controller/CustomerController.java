package org.exampledriven.hateoas.controller;

import org.exampledriven.hateoas.domain.Customer;
import org.exampledriven.hateoas.domain.CustomerList;
import org.exampledriven.hateoas.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/customer", produces = "application/hal+json")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public CustomerList getCustomers() {

        return addLinks(customerService.getCustomers());

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable int id) {

        return addLinks(customerService.getCustomer(id));

    }

    private CustomerList addLinks(List<Customer> customers) {

        CustomerList customerList = new CustomerList();
        customerList.add(linkTo(methodOn(CustomerController.class).getCustomers()).withSelfRel());

        customers.forEach(CustomerController::addLinks);

        customerList.setCustomerList(customers);

        return customerList;
    }

    private static Customer addLinks(Customer customer) {
        Link selfLink   = linkTo(methodOn(CustomerController.class).getCustomer(customer.getCustomerId())).withSelfRel();
        Link invoiceLink = linkTo(methodOn(InvoiceController.class).getInvoiceByCustomerId(customer.getCustomerId())).withRel("invoice");

        customer.add(selfLink);
        customer.add(invoiceLink);

        return customer;

    }
}
