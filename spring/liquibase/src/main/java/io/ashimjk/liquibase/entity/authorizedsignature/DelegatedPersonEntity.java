package io.ashimjk.liquibase.entity.authorizedsignature;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "delegated_person")
public class DelegatedPersonEntity implements Serializable {

    private static final long serialVersionUID = -507856958292201833L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String fullName;
    private String nationalNumber;
    private String jobTitle;
    private String signature;
    private Integer limitAmount;
    private String profile;
    private String document;

    @ElementCollection
    @OrderColumn(name = "services_order_id")
    private List<String> services = new ArrayList<>();

}
