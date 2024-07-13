package com.macielviana.proposal_app.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProposalDTO {

    private UUID proposalId;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String cpf;
    private Double income;
    private Double amountRequested;
    private int paymentDeadline;
    private Boolean approved;
    private String observation;
    private OffsetDateTime dateProposal;

}
