package io.ashimjk.liquibase.controller;

import io.ashimjk.liquibase.entity.BeneficiaryEntity;
import io.ashimjk.liquibase.model.Beneficiary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    BeneficiaryEntity toEntity(Beneficiary beneficiary);

}
