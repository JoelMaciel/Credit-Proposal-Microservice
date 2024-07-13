package com.macielviana.proposal_app.domain.services.impl;

import com.macielviana.proposal_app.domain.dtos.ProposalDTO;
import com.macielviana.proposal_app.domain.dtos.ProposalRequestDTO;
import com.macielviana.proposal_app.domain.entities.Proposal;
import com.macielviana.proposal_app.domain.mapper.ProposalMapper;
import com.macielviana.proposal_app.domain.repositories.ProposalRepository;
import com.macielviana.proposal_app.domain.services.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository proposalRepository;

    @Transactional
    @Override
    public ProposalDTO create(ProposalRequestDTO proposalRequestDTO) {
        Proposal proposal = ProposalMapper.INSTANCE.toEntity(proposalRequestDTO);
        proposalRepository.save(proposal);
        return ProposalMapper.INSTANCE.toDTO(proposal);
    }

    @Override
    public Page<ProposalDTO> getAll(Pageable pageable) {
        Page<Proposal> proposals = proposalRepository.findAll(pageable);
        return ProposalMapper.INSTANCE.toDTOPage(proposals);
    }
}
