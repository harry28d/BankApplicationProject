package com.bankapplication.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private double amount;

    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Acc account;

    public Transaction() {}

    public Transaction(String type, double amount, Acc account) {
        this.type = type;
        this.amount = amount;
        this.account = account;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }

    public Acc getAccount() { return account; }

    public void setAccount(Acc account) { this.account = account; }
}
