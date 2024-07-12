package com.macielviana.proposal_app.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID proposalId;

    private Double amountRequested;
    private int paymentDeadline;
    private Boolean approved;
    private boolean integrated;
    private String observation;

    @CreationTimestamp
    private OffsetDateTime dateProposal;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
