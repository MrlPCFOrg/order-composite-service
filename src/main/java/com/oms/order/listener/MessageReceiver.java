package com.oms.order.listener;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.oms.billing.service.BillingService;
import com.oms.order.model.domain.OrderMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiver implements MessageListener {

    @Autowired
    BillingService billingService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);

    @Override
    public void onMessage(Message message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            OrderMessage orderMessage = objectMapper.readValue(message.getBody(), OrderMessage.class);
            billingService.updateStatus(orderMessage);

        }
        catch (Exception e) {
            LOGGER.error("method=handleMessage message=dataOrApplicationError", e);
        }
    }
}
