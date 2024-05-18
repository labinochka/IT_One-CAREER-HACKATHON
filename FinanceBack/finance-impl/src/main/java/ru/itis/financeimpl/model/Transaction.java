package ru.itis.financeimpl.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = org.hibernate.id.UUIDGenerator.class)
    private UUID id;

    @CreationTimestamp
    @Column(name = "create_date")
    private Instant createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private Instant lastUpdateDate;

    @ManyToOne
    private Account account;

    @Column(name = "date")
    private Instant date;

    @Column(name = "amount")
    private int amount;

    @Column(name = "category")
    private String category;

    @Column(name = "transactional_type")
    private String transactionalType;

    @Column(name = "is_editable")
    private boolean isEditable;
}
