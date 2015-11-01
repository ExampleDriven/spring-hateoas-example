package org.exampledriven.hateoas.controller.assembler;

import org.exampledriven.hateoas.controller.InvoiceController;
import org.exampledriven.hateoas.domain.Invoice;
import org.exampledriven.hateoas.resource.InvoiceResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class InvoiceResourceAssembler extends ResourceAssemblerSupport<Invoice, InvoiceResource> {

    public InvoiceResourceAssembler() {
        super(InvoiceController.class, InvoiceResource.class);
    }

    @Override
    public InvoiceResource toResource(Invoice invoice) {
        InvoiceResource invoiceResource = createResourceWithId(invoice.getId(), invoice);
        invoiceResource.setInvoice(invoice);

        return invoiceResource;
    }
}
