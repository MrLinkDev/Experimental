package ru.link.experimental.Entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "purchase", schema = "experimental_app", catalog = "experimental")
@RequiredArgsConstructor
@NoArgsConstructor
public class PurchaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    @NonNull
    private String name;

    @OneToMany(mappedBy = "purchaseId")
    private List<PurchaseQuestionEntity> questions;

    @OneToMany(mappedBy = "purchaseId")
    private List<SupplyEntity> supplies;
}
