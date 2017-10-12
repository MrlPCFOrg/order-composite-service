package com.oms.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oms.order.model.domain.OrderMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class MessageDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageDelegate.class);

    @Value("${message.orderBillingExchange}")
    private  String  exchangeName;

    @Value("${message.orderBillingRoutingKey}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate ;

    public MessageDelegate( RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(OrderMessage orderMessage){
        try{
            Message message = buildMessage(orderMessage);
            rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
            LOGGER.debug("After sending rabbitmq message");
        }
        catch (Exception ex) {
            LOGGER.error("Unable to serialize the object", ex);
        }
    }

    private Message buildMessage(Object messageBody) {
        Message message = null;
        try {
            String messageBodySerialized = (new ObjectMapper()).writeValueAsString(messageBody);
            message = (Message) MessageBuilder.withBody(messageBodySerialized.getBytes(StandardCharsets.UTF_8)).setContentType("application/json").build();
        } catch (JsonProcessingException e) {
            LOGGER.error("Unable to Process the object", e);
        }
        return message;
    }
}
