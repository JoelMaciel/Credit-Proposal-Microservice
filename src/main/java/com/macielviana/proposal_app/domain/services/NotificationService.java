package com.macielviana.proposal_app.domain.services;

import com.macielviana.proposal_app.domain.dtos.ProposalDTO;

public interface NotificationService {

    void notification(ProposalDTO proposalDTO, String  exchange);
}
