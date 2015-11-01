package org.exampledriven.hateoas.controller;

import org.exampledriven.hateoas.controller.assembler.InvoiceResourceAssembler;
import org.exampledriven.hateoas.domain.Invoice;
import org.exampledriven.hateoas.resource.InvoiceListResource;
import org.exampledriven.hateoas.resource.InvoiceResource;
import org.exampledriven.hateoas.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/invoice", produces = "application/hal+json")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    private InvoiceResourceAssembler invoiceResourceAssembler = new InvoiceResourceAssembler();

    @RequestMapping(method = RequestMethod.GET)
    public InvoiceListResource getInvoiceByCustomerId(@RequestParam("customerId") int customerId) {

        return invoiceToResource(invoiceService.getInvoiceByCustomerId(customerId), customerId);

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public InvoiceResource getInvoiceById(@PathVariable int id) {

        Invoice invoice = invoiceService.getInvoiceById(id);
        return invoiceToResource(invoice);

    }

    private InvoiceListResource invoiceToResource(List<Invoice> invoiceResources, int customerId) {

        InvoiceListResource invoiceListResource = new InvoiceListResource();
        invoiceListResource.add(linkTo(methodOn(InvoiceController.class).getInvoiceByCustomerId(customerId)).withSelfRel());

        List<InvoiceResource> customerResources = invoiceResourceAssembler.toResources(invoiceResources);

        invoiceListResource.setInvoiceResourceList(customerResources);

        return invoiceListResource;
    }

    private InvoiceResource invoiceToResource(Invoice invoice) {
        return invoiceResourceAssembler.toResource(invoice);
    }
}
