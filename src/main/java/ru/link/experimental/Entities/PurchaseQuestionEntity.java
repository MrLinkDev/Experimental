package ru.link.experimental.Entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "purchase_question", schema = "experimental_app", catalog = "experimental")
@RequiredArgsConstructor
@NoArgsConstructor
public class PurchaseQuestionEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "purchase_id")
    @NonNull
    private UUID purchaseId;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "content")
    @NonNull
    private String content;
}
