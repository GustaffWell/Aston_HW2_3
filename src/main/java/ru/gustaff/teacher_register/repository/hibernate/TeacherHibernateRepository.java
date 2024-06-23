package ru.gustaff.teacher_register.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.gustaff.teacher_register.entity.Teacher;
import ru.gustaff.teacher_register.repository.TeacherRepository;

import java.util.List;

@Repository
public class TeacherHibernateRepository implements TeacherRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public TeacherHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Teacher save(Teacher teacher) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            if (teacher.isNew()) {
                session.persist(teacher);
            } else {
                session.merge(teacher);
            }
            transaction.commit();
            return teacher;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Teacher reference = session.getReference(Teacher.class, id);
            session.remove(reference);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Teacher get(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Teacher.class, id);
        }
    }

    @Override
    public List<Teacher> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Teacher", Teacher.class).list();
        }
    }
}


