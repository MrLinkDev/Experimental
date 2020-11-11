package ru.link.experimental.Entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "purchase_answer", schema = "experimental_app", catalog = "experimental")
@RequiredArgsConstructor
@NoArgsConstructor
public class PurchaseAnswerEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "question_id")
    @NonNull
    private UUID questionId;

    @Column(name = "content")
    @NonNull
    private String content;

    @Column(name = "publicity")
    @NonNull
    private boolean publicity;
}
