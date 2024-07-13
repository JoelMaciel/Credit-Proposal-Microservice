package com.macielviana.proposal_app.api.configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfiguration {

    @Value("${rabbitmq.proposal-pending.exchange}")
    private String exchange;

    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializerRabbitAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public FanoutExchange createFanoutExchangeProposalPending() {
        return ExchangeBuilder.fanoutExchange(exchange).build();
    }

    @Bean
    public Binding createBindingProposalPendingMsCreditAnalysis() {
        return BindingBuilder.bind(createQueueProposalPendingMsCreditAnalysis())
                .to(createFanoutExchangeProposalPending());
    }
    @Bean
    public Binding createBindingProposalPendingMsNotification() {
        return BindingBuilder.bind(createQueueProposalPendingMsNotification())
                .to(createFanoutExchangeProposalPending());
    }

    @Bean
    public Queue createQueueProposalPendingMsCreditAnalysis() {
        return QueueBuilder.durable("proposal-pending.ms-credit-analysis").build();
    }

    @Bean
    public Queue createQueueProposalPendingMsNotification() {
        return QueueBuilder.durable("proposal-pending.ms-notification").build();
    }

    @Bean
    public Queue createQueueProposalConcludedMsProposal() {
        return QueueBuilder.durable("proposal-concluded.ms-proposal").build();
    }

    @Bean
    public Queue createQueueProposalConcludedMsNotification() {
        return QueueBuilder.durable("proposal-concluded.ms-notification").build();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
