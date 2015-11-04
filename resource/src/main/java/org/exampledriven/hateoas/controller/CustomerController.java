package org.exampledriven.hateoas.controller;

import org.exampledriven.hateoas.domain.Customer;
import org.exampledriven.hateoas.domain.Invoice;
import org.exampledriven.hateoas.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/customer", produces = "application/hal+json")
@ExposesResourceFor(Customer.class)
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    EntityLinks entityLinks;

    @RequestMapping(method = RequestMethod.GET)
    public Resources<Resource<Customer>> getCustomers() {

        return customerToResource(customerService.getCustomers());

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Resource<Customer> getCustomer(@PathVariable int id) {

        return customerToResource(customerService.getCustomer(id));

    }

    private Resources<Resource<Customer>> customerToResource(List<Customer> customers) {

        Link selfLink = linkTo(methodOn(CustomerController.class).getCustomers()).withSelfRel();

        List<Resource<Customer>> customerResources = customers.stream().map(customer -> customerToResource(customer)).collect(Collectors.toList());

        return new Resources<>(customerResources, selfLink);

    }

    private Resource<Customer> customerToResource(Customer customer) {
        Link selfLink   = linkTo(methodOn(CustomerController.class).getCustomer(customer.getId())).withSelfRel();

        Link allInvoiceLink = entityLinks.linkToCollectionResource(Invoice.class).withRel("all-invoice");
        Link invoiceLink = linkTo(methodOn(InvoiceController.class).getInvoiceByCustomerId(customer.getId())).withRel("invoice");

        return new Resource<>(customer, selfLink,  invoiceLink, allInvoiceLink);

    }
}
