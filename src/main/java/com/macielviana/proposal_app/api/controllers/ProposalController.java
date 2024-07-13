package com.macielviana.proposal_app.api.controllers;

import com.macielviana.proposal_app.domain.dtos.ProposalDTO;
import com.macielviana.proposal_app.domain.dtos.ProposalRequestDTO;
import com.macielviana.proposal_app.domain.services.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/proposals")
public class ProposalController {

    private final ProposalService proposalService;

    @GetMapping
    public Page<ProposalDTO> getAll(
            @PageableDefault(page = 0, size = 10, sort = "proposalId", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        return proposalService.getAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProposalDTO> create(@RequestBody ProposalRequestDTO proposalRequestDTO) {
        ProposalDTO proposalDTO = proposalService.create(proposalRequestDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{proposalId}")
                .buildAndExpand(proposalDTO.getProposalId())
                .toUri()).body(proposalDTO);
    }
}
