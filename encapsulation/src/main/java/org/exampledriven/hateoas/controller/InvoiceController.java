package org.exampledriven.hateoas.controller;

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
@RequestMapping(value = "/api", produces = "application/hal+json")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public InvoiceListResource getInvoiceByCustomerId(@RequestParam("customerId") int customerId) {

        return invoiceToResource(invoiceService.getInvoiceByCustomerId(customerId), customerId);

    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    public InvoiceResource getInvoiceById(@PathVariable int id) {

        Invoice invoice = invoiceService.getInvoiceById(id);
        return invoiceToResource(invoice);

    }

    private InvoiceListResource invoiceToResource(List<Invoice> invoiceResources, int customerId) {

        InvoiceListResource invoiceListResource = new InvoiceListResource();
        invoiceListResource.add(linkTo(methodOn(InvoiceController.class).getInvoiceByCustomerId(customerId)).withSelfRel());

        List<InvoiceResource> customerResources = invoiceResources.stream().map(InvoiceController::invoiceToResource).collect(Collectors.toList());

        invoiceListResource.setInvoiceResourceList(customerResources);

        return invoiceListResource;
    }

    private static InvoiceResource invoiceToResource(Invoice invoice) {
        Link selfLink = linkTo(methodOn(InvoiceController.class).getInvoiceById(invoice.getId())).withSelfRel();

        InvoiceResource invoiceResource = new InvoiceResource();
        invoiceResource.setInvoice(invoice);
        invoiceResource.add(selfLink);

        return invoiceResource;
    }
}
