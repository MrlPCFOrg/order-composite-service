package com.oms.order.model.request;

import com.oms.order.model.domain.*;

import java.util.List;

public class OrderUpdate {

    private String orderStatus;
    private String totalCost;

    private List<Product> products;
    private List<Payment> paymentList;
    private List<Shipping> shippingList;
    private List<Tracking> trackingList;
    private List<Invoice> invoiceList;




}
