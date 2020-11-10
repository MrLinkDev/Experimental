package ru.link.experimental.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "purchase", schema = "experimental_app", catalog = "experimental")
public class PurchaseEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany
    Set<PurchaseQuestionEntity> questions;
}
