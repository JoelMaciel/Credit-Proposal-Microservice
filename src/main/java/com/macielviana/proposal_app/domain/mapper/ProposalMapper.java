package com.macielviana.proposal_app.domain.mapper;

import com.macielviana.proposal_app.domain.dtos.ProposalDTO;
import com.macielviana.proposal_app.domain.dtos.ProposalRequestDTO;
import com.macielviana.proposal_app.domain.entities.Proposal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper
public interface ProposalMapper {

    ProposalMapper INSTANCE = Mappers.getMapper(ProposalMapper.class);

    @Mapping(target = "user.name", source = "name")
    @Mapping(target = "user.lastName", source = "lastName")
    @Mapping(target = "user.cpf", source = "cpf")
    @Mapping(target = "user.phoneNumber", source = "phoneNumber")
    @Mapping(target = "user.income", source = "income")
    @Mapping(target = "proposalId", ignore = true)
    @Mapping(target = "approved", ignore = true)
    @Mapping(target = "integrated", constant = "true")
    @Mapping(target = "observation", ignore = true)
    Proposal toEntity(ProposalRequestDTO proposalRequestDTO);

    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "phoneNumber", source = "user.phoneNumber")
    @Mapping(target = "cpf", source = "user.cpf")
    @Mapping(target = "income", source = "user.income")
    @Mapping(target = "integrated", constant = "true")
    ProposalDTO toDTO(Proposal proposal);

    default Page<ProposalDTO> toDTOPage(Page<Proposal> proposals) {
        List<ProposalDTO> dtoList = proposals.stream()
                .map(this::toDTO)
                .toList();
        return new PageImpl<>(dtoList, proposals.getPageable(), proposals.getTotalElements());
    }
}

