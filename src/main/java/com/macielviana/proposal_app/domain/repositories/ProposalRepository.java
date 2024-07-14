package com.macielviana.proposal_app.domain.repositories;

import com.macielviana.proposal_app.domain.entities.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, UUID> {

    List<Proposal> findAllByIntegratedFalse();

}
