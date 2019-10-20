package io.ashimjk.entityrelationship.controller;

import io.ashimjk.entityrelationship.domain.Address;
import io.ashimjk.entityrelationship.domain.Beneficiary;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class BeneficiaryControllerTest {

    @LocalServerPort
    private int port;

    @Test
    void givenBeneficiary_whenCreate_ThenSaveBeneficiary() {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setAddresses(Collections.singletonList(buildAddress()));


        String response = RestAssured
                .given()
                .port(port)
                .contentType("application/json")
                .when()
                .body(beneficiary)
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .jsonPath()
                .prettify();


        assertThat(response)
                .isNotNull()
                .isNotEmpty();
    }

    private Address buildAddress() {
        return Address.builder()
                .addressLine1("ktm")
                .city("ktm")
                .country("nepal")
                .build();
    }

}