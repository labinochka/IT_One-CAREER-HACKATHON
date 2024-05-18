package ru.itis.financeimpl.model;

import jakarta.persistence.*;
import lombok.*;

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

    private String firstName;

    private String lastName;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;
}
