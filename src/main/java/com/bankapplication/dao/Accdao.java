package com.bankapplication.dao;

import com.bankapplication.model.Acc;
import com.bankapplication.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Accdao {

    public void saveAccount(Acc acc) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(acc);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public Acc getAccount(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Acc.class, id);
        }
    }

    public List<Acc> getAllAccounts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Acc", Acc.class).list();
        }
    }

    public void updateAccount(Acc acc) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(acc);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
