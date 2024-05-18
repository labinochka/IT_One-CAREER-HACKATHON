package ru.itis.financeimpl.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String email;

    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "account")
    private List<Subscription> subscription;

    @OneToMany(mappedBy = "account")
    private List<GoalAndBudgetLimit> goalsAndLimits;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
