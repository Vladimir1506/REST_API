package com.vladimir1506.rest.implementation;

import com.vladimir1506.rest.model.MyFile;
import com.vladimir1506.rest.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MyFileImpl {
    public List<MyFile> getAll() {
        List<MyFile> files = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            files = session.createQuery("from MyFile").list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return files;
    }

    public MyFile getById(Long id) {
        Transaction transaction = null;
        MyFile file = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            file = session.get(MyFile.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return file;
    }

    public MyFile save(MyFile file) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(file);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return file;
    }

    public void delete(Long id) {
        Transaction transaction = null;
        MyFile file;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            file = session.get(MyFile.class, id);
            session.delete(file);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
