package org.exampledriven.hateoas.domain;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.List;

@Relation(value = "foo2", collectionRelation = "bar2")
public class InvoiceList extends ResourceSupport {

    private List<Invoice> invoiceList;

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }
}
