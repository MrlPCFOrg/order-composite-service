package com.oms.order.listener;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.oms.order.model.domain.BillingEvent;
import com.oms.order.model.domain.EventDTO;
import com.oms.order.model.domain.EventResponse;
import com.oms.order.model.domain.Invoice;
import com.oms.order.model.domain.OrderMessage;
import com.oms.order.model.domain.Payment;
import com.oms.order.model.domain.Product;
import com.oms.order.model.request.OrderUpdate;
import com.oms.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageReceiver implements MessageListener {

    @Autowired
    OrderService orderService;

    @Value("${url.eventServiceURL}")
    private String eventService_url;

    @Autowired
    RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);

    @Override
    public void onMessage(Message message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            OrderMessage orderMessage = objectMapper.readValue(message.getBody(), OrderMessage.class);
            OrderUpdate orderUpdate = new OrderUpdate();
            orderUpdate.setTotalCost(orderMessage.getTotalCost());

            List<Payment> paymentList = new ArrayList<>();
            Payment payment = new Payment();
            payment.setPaymentMode(orderMessage.getPaymentMode());
            payment.setPaymentStatus(orderMessage.getPaymentStatus());
            paymentList.add(payment);
            orderUpdate.setPaymentList(paymentList);

            List<Invoice> invoiceList = new ArrayList<>();
            Invoice invoice = new Invoice();
            invoice.setInvoiceDate(orderMessage.getInvoiceDate());
            invoice.setInvoiceNumber(orderMessage.getInvoiceNumber());
            invoiceList.add(invoice);
            orderUpdate.setInvoiceList(invoiceList);

            List<Product> productList = new ArrayList<>();
            Product product = new Product();
            product.setQuantity("1");
            product.setTitle("1");
            product.setUnitCost("1");
            productList.add(product);
            orderUpdate.setProducts(productList);


            //Call to 'event-service' (IPC)
            LOGGER.info("message=Call to event-service{}", orderMessage);
            EventResponse eventResponse = restTemplate.getForObject(eventService_url + "/searchEventsByOrderId/{id}", EventResponse.class, orderMessage.getOrderId());

            List<EventDTO> billingEventList = eventResponse.getEventDTOS();
            boolean allStatusMatch = billingEventList.stream().allMatch(billingEvent -> billingEvent.getStatus().equalsIgnoreCase("success"));
            if (allStatusMatch) {
                orderUpdate.setOrderStatus("success");
            }else{
                orderUpdate.setOrderStatus("failed");
            }
            orderService.updateOrder(orderUpdate, orderMessage.getOrderId());

        } catch (Exception e) {
            LOGGER.error("method=handleMessage message=dataOrApplicationError", e);
        }
    }
}
