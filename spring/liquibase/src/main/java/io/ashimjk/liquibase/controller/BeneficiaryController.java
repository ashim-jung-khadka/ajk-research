package io.ashimjk.liquibase.controller;

import io.ashimjk.liquibase.entity.BeneficiaryEntity;
import io.ashimjk.liquibase.model.Beneficiary;
import io.ashimjk.liquibase.model.ContactPerson;
import io.ashimjk.liquibase.model.CorporateBeneficiary;
import io.ashimjk.liquibase.repository.BeneficiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("beneficiary")
public class BeneficiaryController {

    private final BeneficiaryRepository beneficiaryRepository;
    private final BeneficiaryMapper mapper;

    @GetMapping
    public ResponseEntity<List<Beneficiary>> getAll() {
        List<Beneficiary> beneficiaries = beneficiaryRepository.findAll()
                .stream()
                .map(mapper::toBeneficiary)
                .collect(Collectors.toList());

        return ResponseEntity.ok(beneficiaries);
    }

    @GetMapping("insert")
    public ResponseEntity<Beneficiary> insert() {
        ContactPerson contactPerson = new ContactPerson();
        contactPerson.setCpName("pabitra");

        CorporateBeneficiary corporateBeneficiary = new CorporateBeneficiary();
        corporateBeneficiary.setContactPersons(Collections.singleton(contactPerson));

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setName("ashish");
        beneficiary.setCorporateBeneficiary(corporateBeneficiary);

        BeneficiaryEntity beneficiaryEntity = this.beneficiaryRepository.save(mapper.toBeneficiaryEntity(beneficiary));

        return ResponseEntity.ok(mapper.toBeneficiary(beneficiaryEntity));
    }

    @GetMapping("partialUpdate")
    public ResponseEntity<Beneficiary> partialUpdate() {
        Beneficiary beneficiary = mapper.toBeneficiary(this.beneficiaryRepository.getOne(1L));
        beneficiary.setName("ashim");

        this.beneficiaryRepository.save(mapper.toBeneficiaryEntity(beneficiary));

        return ResponseEntity.ok(beneficiary);
    }

    @GetMapping("fullUpdate")
    public ResponseEntity<Beneficiary> fullUpdate() {
        Beneficiary beneficiary = mapper.toBeneficiary(this.beneficiaryRepository.getOne(1L));
        beneficiary.setName("ashim");

        CorporateBeneficiary corporateBeneficiary = beneficiary.getCorporateBeneficiary();
        Set<ContactPerson> contactPersons = corporateBeneficiary.getContactPersons();
        contactPersons = contactPersons.stream().peek(contactPerson -> contactPerson.setCpName("ashim")).collect(Collectors.toSet());

        corporateBeneficiary.setContactPersons(contactPersons);

        this.beneficiaryRepository.save(mapper.toBeneficiaryEntity(beneficiary));

        return ResponseEntity.ok(beneficiary);
    }

    @GetMapping("delete")
    public ResponseEntity<Void> delete() {
        this.beneficiaryRepository.deleteById(1L);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
