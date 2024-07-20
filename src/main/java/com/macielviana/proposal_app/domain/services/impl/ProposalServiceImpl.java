package com.macielviana.proposal_app.domain.services.impl;

import com.macielviana.proposal_app.domain.dtos.ProposalDTO;
import com.macielviana.proposal_app.domain.dtos.ProposalRequestDTO;
import com.macielviana.proposal_app.domain.entities.Proposal;
import com.macielviana.proposal_app.domain.mapper.ProposalMapper;
import com.macielviana.proposal_app.domain.repositories.ProposalRepository;
import com.macielviana.proposal_app.domain.services.NotificationRabbitmqService;
import com.macielviana.proposal_app.domain.services.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository proposalRepository;
    private final NotificationRabbitmqService notificationRabbitmqService;

    @Value("${rabbitmq.proposal-pending.exchange}")
    private String exchange;

    @Transactional
    @Override
    public ProposalDTO create(ProposalRequestDTO proposalRequestDTO) {
        Proposal proposal = ProposalMapper.INSTANCE.toEntity(proposalRequestDTO);

        proposalRepository.save(proposal);

        notification(proposal);

        return ProposalMapper.INSTANCE.toDTO(proposal);
    }

    @Override
    public Page<ProposalDTO> getAll(Pageable pageable) {
        Page<Proposal> proposals = proposalRepository.findAll(pageable);
        return ProposalMapper.INSTANCE.toDTOPage(proposals);
    }

    @Override
    public void notification(Proposal proposal) {
        try {
            notificationRabbitmqService.notificationRabbitmq(proposal, exchange);
        } catch (RuntimeException exception) {
            proposal.setIntegrated(false);
            proposalRepository.save(proposal);

        }
    }
}
