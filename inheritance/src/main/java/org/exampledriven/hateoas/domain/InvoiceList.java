package org.exampledriven.hateoas.domain;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class InvoiceList extends ResourceSupport {

    private List<Invoice> invoiceList;

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }
}
