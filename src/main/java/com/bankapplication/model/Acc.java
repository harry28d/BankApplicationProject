package com.bankapplication.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "accounts")
public class Acc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private double balance;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Acc() {}

    public Acc(String number, double balance, Customer customer) {
        this.number = number;
        this.balance = balance;
        this.customer = customer;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public List<Transaction> getTransactions() { return transactions; }

    public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }
}
