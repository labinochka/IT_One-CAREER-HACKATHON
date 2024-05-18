package ru.itis.financeimpl.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "budget_distribution_rule")
public class BudgetDistributionRule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    @Column(name = "create_date")
    private Instant createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private Instant lastUpdateDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private int budget;

    @Column(name = "current_expenses")
    private int currentExpenses; // сколько на данный момент потрачено

    private String category; // на что выделен бюджет
}
