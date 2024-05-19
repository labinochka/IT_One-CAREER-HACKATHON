package ru.itis.bank.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transaction_in_bank")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String mail;

    private Instant date;

    private int amount;

    private String category;

    @Column(name = "transactional_type")
    private String transactionalType;
}
