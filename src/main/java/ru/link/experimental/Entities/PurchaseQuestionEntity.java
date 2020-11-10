package ru.link.experimental.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "purchase_question", schema = "experimental_app", catalog = "experimental")
public class PurchaseQuestionEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "purchase_id")
    private UUID purchaseId;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;
}
