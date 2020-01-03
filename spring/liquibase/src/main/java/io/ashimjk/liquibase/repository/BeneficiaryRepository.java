package io.ashimjk.liquibase.repository;

import io.ashimjk.liquibase.entity.BeneficiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<BeneficiaryEntity, Long> {
}
