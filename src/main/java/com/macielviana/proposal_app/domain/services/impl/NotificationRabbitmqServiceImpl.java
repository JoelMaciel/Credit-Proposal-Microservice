package com.macielviana.proposal_app.domain.services.impl;

import com.macielviana.proposal_app.domain.entities.Proposal;
import com.macielviana.proposal_app.domain.services.NotificationRabbitmqService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationRabbitmqServiceImpl implements NotificationRabbitmqService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void notificationRabbitmq(Proposal proposal, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposal);

    }
}
