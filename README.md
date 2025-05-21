package com.bankapplication.service;
 
import com.bankapplication.dao.Accdao;
import com.bankapplication.dao.Transactiondao;
import com.bankapplication.model.Acc;
import com.bankapplication.model.Transaction;
 
public class Accservice {
    private Accdao accdao = new Accdao();
    private Transactiondao transactiondao = new Transactiondao();
 
    public void deposit(Long accountId, double amount) {
        Acc acc = accdao.findById(accountId);
        if (acc != null) {
            acc.setBalance(acc.getBalance() + amount);
            accdao.update(acc);
transactiondao.save(new Transaction("deposit", amount, acc));
        } else {
            throw new IllegalArgumentException("Account not found");
        }
    }
 
    public void withdraw(Long accountId, double amount) {
        Acc acc = accdao.findById(accountId);
        if (acc != null && acc.getBalance() >= amount) {
            acc.setBalance(acc.getBalance() - amount);
            accdao.update(acc);
transactiondao.save(new Transaction("withdraw", amount, acc));
        } else {
            throw new IllegalArgumentException("Insufficient funds or account not found");
        }
    }
 
    public void transfer(Long fromAccountId, Long toAccountId, double amount) {
        Acc from = accdao.findById(fromAccountId);
        Acc to = accdao.findById(toAccountId);
        if (from == null || to == null) {
            throw new IllegalArgumentException("Account not found");
        }
        if (from.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        accdao.update(from);
        accdao.update(to);
transactiondao.save(new Transaction("transfer-out", amount, from));
transactiondao.save(new Transaction("transfer-in", amount, to));
    }
}
