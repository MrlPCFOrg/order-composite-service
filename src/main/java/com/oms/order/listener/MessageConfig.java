package com.oms.order.listener;



import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Value("${message.orderStatusQueue}")
    private String orderStatusQueue;

    @Value("${message.orderStatusExchange}")
    private  String  orderStatusExchange;

    @Value("${message.orderStatusRoutingKey}")
    private  String  orderStatusRoutingKey;


    @Autowired
    private ConnectionFactory connectionFactory;

    /*@Autowired
    private RabbitTemplate rabbitTemplate;*/

    @Bean
    Queue queue() {
        return new Queue(orderStatusQueue, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(orderStatusExchange);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(orderStatusRoutingKey);
    }

    /*@Bean
    public RabbitTemplate getRabbitTemplate(ConnectionFactory connectionFactory) {
        rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }*/

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(orderStatusQueue);//Queue name reads from CONFIG Repository
        container.setMessageListener(listenerAdapter);

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(MessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "onMessage");
    }
}
