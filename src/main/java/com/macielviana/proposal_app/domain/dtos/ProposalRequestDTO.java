package com.macielviana.proposal_app.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProposalRequestDTO {

    private String name;
    private String lastName;
    private String phoneNumber;
    private String cpf;
    private Double income;
    private Double amountRequested;
    private int paymentDeadline;
}
