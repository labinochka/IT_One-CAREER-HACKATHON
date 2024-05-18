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
@Table(name = "goal_and_limit")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GoalAndBudgetLimit {
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
    private Account account; // ToDo: insert User

    @Column(name = "make_money_goal")
    private int makeMoneyGoal;

    @Column(name = "spend_money_goal")
    private int spendMoneyGoal;

    @Column(name = "budget_limit")
    private int budgetLimit;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;
}
