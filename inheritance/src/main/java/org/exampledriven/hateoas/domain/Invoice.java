package org.exampledriven.hateoas.domain;

import org.springframework.hateoas.ResourceSupport;

public class Invoice extends ResourceSupport {
    private int invoiceId;
    private int price;
    private String title;
    private int customerId;

    public Invoice(int invoiceId, int price, String title, int customerId) {
        this.invoiceId = invoiceId;
        this.price = price;
        this.title = title;
        this.customerId = customerId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
}
