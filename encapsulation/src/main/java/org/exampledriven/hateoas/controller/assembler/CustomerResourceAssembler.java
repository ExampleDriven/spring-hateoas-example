package org.exampledriven.hateoas.controller.assembler;

import org.exampledriven.hateoas.controller.CustomerController;
import org.exampledriven.hateoas.controller.InvoiceController;
import org.exampledriven.hateoas.domain.Customer;
import org.exampledriven.hateoas.resource.CustomerResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CustomerResourceAssembler extends ResourceAssemblerSupport<Customer, CustomerResource> {

    public CustomerResourceAssembler() {
        super(CustomerController.class, CustomerResource.class);
    }

    @Override
    public CustomerResource toResource(Customer customer) {
        CustomerResource customerResource = createResourceWithId(customer.getId(), customer);

        Link invoiceLink = ControllerLinkBuilder.linkTo(methodOn(InvoiceController.class).getInvoiceByCustomerId(customer.getId())).withRel("invoice");

        customerResource.setCustomer(customer);
        customerResource.add(invoiceLink);

        return customerResource;
    }

}
