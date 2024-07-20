package com.macielviana.proposal_app.domain.schedulers;

import com.macielviana.proposal_app.domain.entities.Proposal;
import com.macielviana.proposal_app.domain.repositories.ProposalRepository;
import com.macielviana.proposal_app.domain.services.NotificationRabbitmqService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class ProposalWithoutIntegration {

    private final ProposalRepository proposalRepository;
    private final NotificationRabbitmqService notificationRabbitmqService;

    @Value("${rabbitmq.proposal-pending.exchange}")
    private String exchange;

    private final Logger logger = LoggerFactory.getLogger(ProposalWithoutIntegration.class);

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void findProposalWithoutIntegration() {
        proposalRepository.findAllByIntegratedFalse().forEach(proposal -> {
            try {
                notificationRabbitmqService.notificationRabbitmq(proposal, exchange);
                updatedProposal(proposal);
            } catch (RuntimeException exception) {
                logger.error(exception.getMessage());
            }
        });
    }

    private void updatedProposal(Proposal proposal) {
        proposal.setIntegrated(true);
        proposalRepository.save(proposal);
    }
}
