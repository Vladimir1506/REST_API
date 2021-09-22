package com.vladimir1506.rest.repository.implementation;

import com.vladimir1506.rest.model.User;
import com.vladimir1506.rest.repository.UserRepository;
import com.vladimir1506.rest.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserImpl implements UserRepository {
    @Override
    public List<User> getAll() {
        List<User> users = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            users = session.createQuery("from User").list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public User getById(Long id) {
        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }

    @Override
    public User save(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        User user;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
