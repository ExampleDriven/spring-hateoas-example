package org.exampledriven.hateoas.controller;

import org.exampledriven.hateoas.domain.Customer;
import org.exampledriven.hateoas.domain.CustomerList;
import org.exampledriven.hateoas.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public CustomerList getCustomers() {

        return addLinks(customerService.getCustomers());

    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable int id) {

        return addLinks(customerService.getCustomer(id));

    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable int id) {

        customerService.deleteCustomer(id);

    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public Customer createCustomer(@RequestBody Customer customer) {

        Customer customerCreated = customerService.createCustomer(customer);

        return addLinks(customerCreated);

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
        Link addressLink = ControllerLinkBuilder.linkTo(methodOn(AddressController.class).getAddressByCustomerId(customer.getCustomerId())).withRel("address");
        Link invoiceLink = linkTo(methodOn(InvoiceController.class).getInvoiceByCustomerId(customer.getCustomerId())).withRel("invoice");

        customer.add(selfLink);
        customer.add(addressLink);
        customer.add(invoiceLink);

        return customer;

    }
}
