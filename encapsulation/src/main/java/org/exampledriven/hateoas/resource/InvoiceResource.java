package org.exampledriven.hateoas.resource;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.exampledriven.hateoas.domain.Invoice;
import org.springframework.hateoas.ResourceSupport;

public class InvoiceResource extends ResourceSupport {

    @JsonUnwrapped
    private Invoice invoice;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
