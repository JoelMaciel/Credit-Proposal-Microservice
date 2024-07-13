package com.macielviana.proposal_app.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    private String name;
    private String lastName;
    private String cpf;
    private String phoneNumber;
    private Double income;

    @OneToMany(mappedBy = "user")
    private List<Proposal> proposals;
}
