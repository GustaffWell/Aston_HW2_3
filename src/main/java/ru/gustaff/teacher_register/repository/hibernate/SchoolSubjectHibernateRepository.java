package ru.gustaff.teacher_register.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.gustaff.teacher_register.entity.SchoolSubject;
import ru.gustaff.teacher_register.entity.Teacher;
import ru.gustaff.teacher_register.repository.SchoolSubjectRepository;

import java.util.List;

@Repository
public class SchoolSubjectHibernateRepository implements SchoolSubjectRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public SchoolSubjectHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SchoolSubject save(SchoolSubject schoolSubject) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            if (schoolSubject.isNew()) {
                session.persist(schoolSubject);
            } else {
                session.merge(schoolSubject);
            }
            transaction.commit();
            return schoolSubject;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            SchoolSubject subject = session.get(SchoolSubject.class, id);
            List<Teacher> teachers = session.createQuery("from Teacher", Teacher.class).list();
            teachers.stream()
                            .filter(teacher -> teacher.getSubjects().contains(subject))
                            .forEach(teacher -> teacher.getSubjects().remove(subject));
            teachers.forEach(session::merge);
            session.remove(subject);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public SchoolSubject get(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(SchoolSubject.class, id);
        }
    }

    @Override
    public List<SchoolSubject> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from SchoolSubject ", SchoolSubject.class).list();
        }
    }
}
