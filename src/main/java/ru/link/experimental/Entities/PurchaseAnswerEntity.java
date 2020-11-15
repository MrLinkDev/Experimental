package ru.link.experimental.Entities;

import com.fasterxml.jackson.annotation.*;
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

    @OneToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @JsonIgnore
    private PurchaseQuestionEntity questionId;

    @Column(name = "content")
    @NonNull
    private String content;

    @Column(name = "publicity")
    @NonNull
    private boolean publicity;
}
