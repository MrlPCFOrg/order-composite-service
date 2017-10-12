package com.oms.order.model.domain;

import com.google.common.base.MoreObjects;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Invoice {
    private String invoiceNumber;

    @DateTimeFormat
    private Date invoiceDate;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public Invoice setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public Invoice setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("invoiceNumber", invoiceNumber)
                .add("invoiceDate", invoiceDate)
                .toString();
    }
}
