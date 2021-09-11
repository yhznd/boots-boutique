package com.yunus.haznedar.bootsboutique.config;



import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig
{
    public static final String BOUTIQUE_QUEUE_ADD = "boutique_queue_add";
    public static final String BOUTIQUE_QUEUE_DELETE = "boutique_queue_delete";
    public static final String BOUTIQUE_TOPIC_EXCHANGE = "boutique_topic_exchange";
    public static final String BOUTIQUE_ADD_RK = "boutique.boot.added";
    public static final String BOUTIQUE_DELETE_RK = "boutique.boot.deleted";

    @Bean
    public Queue queueAdd()
    {
        return new Queue(BOUTIQUE_QUEUE_ADD);
    }

    @Bean
    public Queue queueDelete()
    {
        return new Queue(BOUTIQUE_QUEUE_DELETE);
    }

    @Bean 
    public TopicExchange topicExchange()
    {
        return new TopicExchange(BOUTIQUE_TOPIC_EXCHANGE);
    }
    
    @Bean
    public Binding bindingAddQueue(Queue queueAdd, TopicExchange topicExchange)
    {
        return BindingBuilder.bind(queueAdd).to(topicExchange).with(BOUTIQUE_ADD_RK);
    }

    @Bean
    public Binding bindingDeleteQueue(Queue queueDelete, TopicExchange topicExchange)
    {
        return BindingBuilder.bind(queueDelete).to(topicExchange).with(BOUTIQUE_DELETE_RK);
    }

    public MessageConverter converter()
    {
        //We need converter because we're gonna deal with objects not data types.
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory)
    {
        final RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }





}
