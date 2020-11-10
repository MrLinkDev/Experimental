package ru.link.experimental.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "purchase_answer", schema = "experimental_app", catalog = "experimental")
public class PurchaseAnswerEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "content")
    private String content;
}
