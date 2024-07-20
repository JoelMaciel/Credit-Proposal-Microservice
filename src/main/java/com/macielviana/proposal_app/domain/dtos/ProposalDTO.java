package com.macielviana.proposal_app.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProposalDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID proposalId;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String cpf;
    private Double income;
    private Double amountRequested;
    private int paymentDeadline;
    private boolean integrated;
    private Boolean approved;
    private String observation;

}
