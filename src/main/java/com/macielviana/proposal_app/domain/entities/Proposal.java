package com.macielviana.proposal_app.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;
}
