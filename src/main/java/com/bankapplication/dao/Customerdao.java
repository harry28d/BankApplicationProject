package com.bankapplication.dao;

import com.bankapplication.model.Customer;
import com.bankapplication.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Customerdao {

    public void saveCustomer(Customer customer) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public Customer getCustomer(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Customer.class, id);
        }
    }

    public List<Customer> getAllCustomers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Customer", Customer.class).list();
        }
    }

    public void deleteCustomer(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Customer customer = session.get(Customer.class, id);
            if (customer != null) {
                tx = session.beginTransaction();
                session.delete(customer);
                tx.commit();
            }
        }
    }
}
