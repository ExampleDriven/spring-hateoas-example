package org.exampledriven.hateoas.resource;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class InvoiceListResource extends ResourceSupport {

    @JsonUnwrapped
    private List<InvoiceResource> invoiceResourceList;

    public List<InvoiceResource> getInvoiceResourceList() {
        return invoiceResourceList;
    }

    public void setInvoiceResourceList(List<InvoiceResource> invoiceResourceList) {
        this.invoiceResourceList = invoiceResourceList;
    }
}
