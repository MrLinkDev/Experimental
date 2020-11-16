package ru.link.experimental.Entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "supply", schema = "experimental_app", catalog = "experimental")
@RequiredArgsConstructor
@NoArgsConstructor
public class SupplyEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "purchase_id")
    @NonNull
    private UUID purchaseId;

    @Column(name = "cost")
    @NonNull
    private long cost;

    @Column(name = "t_stack")
    @NonNull
    private String tStack;

    @Column(name = "structure")
    @NonNull
    private String structure;

    @Column(name = "comment")
    @NonNull
    private String comment;

}
