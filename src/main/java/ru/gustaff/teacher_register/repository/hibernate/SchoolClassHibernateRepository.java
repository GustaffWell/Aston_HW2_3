package ru.gustaff.teacher_register.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.gustaff.teacher_register.entity.SchoolClass;
import ru.gustaff.teacher_register.repository.SchoolClassRepository;

import java.util.List;

@Repository
public class SchoolClassHibernateRepository implements SchoolClassRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public SchoolClassHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SchoolClass save(SchoolClass schoolClass) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            if (schoolClass.isNew()) {
                session.persist(schoolClass);
            } else {
                session.merge(schoolClass);
            }
            transaction.commit();
            return schoolClass;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            SchoolClass reference = session.getReference(SchoolClass.class, id);
            session.remove(reference);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public SchoolClass get(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(SchoolClass.class, id);
        }
    }

    @Override
    public List<SchoolClass> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from SchoolClass", SchoolClass.class).list();
        }
    }
}
