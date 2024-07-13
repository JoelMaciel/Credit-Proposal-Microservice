package com.macielviana.proposal_app.domain.services.impl;

import com.macielviana.proposal_app.domain.dtos.ProposalDTO;
import com.macielviana.proposal_app.domain.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void notification(ProposalDTO proposalDTO, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposalDTO);

    }
}
