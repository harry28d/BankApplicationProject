package com.bankapplication.service;
import com.bankapplication.dao.Accdao;
import com.bankapplication.dao.Transactiondao;
import com.bankapplication.model.Acc;
import com.bankapplication.model.Transaction;

public class Accservice {
    private Accdao accdao = new Accdao();
    private Transactiondao transactiondao = new Transactiondao();

    public void deposit(Long accountId, double amount) {
        Account acc = accdao.findById(accountId);
        if (acc != null) {
            acc.setBalance(acc.getBalance() + amount);
            accountDao.update(acc);
            transactionDao.save(new Transaction("deposit", amount, acc));
        }
    }

    public void withdraw(Long accountId, double amount) {
        Acc acc = accdao.findById(accountId);
        if (acc != null && acc.getBalance() >= amount) {
            acc.setBalance(acc.getBalance() - amount);
            Accdao.update(acc);
            transactiondao.save(new Transaction("withdraw", amount, acc));
        } else {
            throw new IllegalArgumentException("Insufficient funds or account not found");
        }
    }

    public void transfer(Long fromAccountId, Long toAccountId, double amount) {
        Account from = accountDao.findById(fromAccountId);
        Account to = accountDao.findById(toAccountId);
        if (from == null || to == null) {
            throw new IllegalArgumentException("Account not found");
        }
        if (from.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        // Perform transfer
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        accountDao.update(from);
        accountDao.update(to);
        // Record transactions
        transactionDao.save(new Transaction("transfer-out", amount, from));
        transactionDao.save(new Transaction("transfer-in", amount, to));
    }
}

