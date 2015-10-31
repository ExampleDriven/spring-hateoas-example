package org.exampledriven.hateoas.controller;

import org.exampledriven.hateoas.domain.Invoice;
import org.exampledriven.hateoas.domain.InvoiceList;
import org.exampledriven.hateoas.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public InvoiceList getInvoiceByCustomerId(@RequestParam("customerId") int customerId) {

        return addLinks(invoiceService.getInvoiceByCustomerId(customerId), customerId);

    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    public Invoice getInvoiceById(@PathVariable int id) {

        Invoice invoice = invoiceService.getInvoiceById(id);
        return addLinks(invoice);

    }

    private InvoiceList addLinks(List<Invoice> invoices, int customerId) {

        InvoiceList invoiceList = new InvoiceList();
        invoiceList.add(linkTo(methodOn(InvoiceController.class).getInvoiceByCustomerId(customerId)).withSelfRel());

        invoices.forEach(InvoiceController::addLinks);

        invoiceList.setInvoiceList(invoices);


        return invoiceList;
    }

    private static Invoice addLinks(Invoice invoice) {
        Link selfLink = linkTo(methodOn(InvoiceController.class).getInvoiceById(invoice.getInvoiceId())).withSelfRel();

        invoice.add(selfLink);

        return invoice;
    }
}
