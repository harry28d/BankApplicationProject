package com.bankapplication.dao;

import com.bankapplication.model.Transaction;
import com.bankapplication.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction as HibernateTx;

import java.util.List;

public class Transactiondao {

    public void saveTransaction(Transaction txn) {
        HibernateTx tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(txn);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Transaction t where t.account.id = :accountId", Transaction.class)
                          .setParameter("accountId", accountId)
                          .list();
        }
    }
}
