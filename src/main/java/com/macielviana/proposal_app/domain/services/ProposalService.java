package com.macielviana.proposal_app.domain.services;

import com.macielviana.proposal_app.domain.dtos.ProposalDTO;
import com.macielviana.proposal_app.domain.dtos.ProposalRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProposalService {

    ProposalDTO create(ProposalRequestDTO proposalRequestDTO);

    Page<ProposalDTO> getAll(Pageable pageable);
}
