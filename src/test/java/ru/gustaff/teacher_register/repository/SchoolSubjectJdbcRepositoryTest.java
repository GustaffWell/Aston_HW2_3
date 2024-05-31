package ru.gustaff.teacher_register.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.gustaff.teacher_register.model.SchoolSubject;

import static ru.gustaff.teacher_register.repository.SchoolSubjectJdbcRepository.SCHOOL_SUBJECT_JDBC_REPOSITORY;
import static ru.gustaff.teacher_register.repository.test_data.SchoolSubjectTestData.*;

public class SchoolSubjectJdbcRepositoryTest extends BaseTest {

    @Test
    public void getById() {
        SchoolSubject schoolSubject = SCHOOL_SUBJECT_JDBC_REPOSITORY.get(100_003);
        Assertions.assertEquals(SUBJECT_1, schoolSubject);
    }

    @Test
    public void getNotExistId() {
        Assertions.assertEquals(null, SCHOOL_SUBJECT_JDBC_REPOSITORY.get(1));
    }

    @Test
    public void getAll() {
        Assertions.assertEquals(ALL_SUBJECTS, SCHOOL_SUBJECT_JDBC_REPOSITORY.getAll());
    }

    @Test
    public void add() {
        SchoolSubject schoolSubject = new SchoolSubject(null, "new", 1);
        Assertions.assertEquals(NEW_SUBJECT, SCHOOL_SUBJECT_JDBC_REPOSITORY.save(schoolSubject));
    }

    @Test
    public void update() {
        SchoolSubject schoolSubject = new SchoolSubject(100_003, "update", 1);
        Assertions.assertEquals(UPDATED_SUBJECT, SCHOOL_SUBJECT_JDBC_REPOSITORY.save(schoolSubject));
    }

    @Test
    public void delete() {
        Assertions.assertEquals(true, SCHOOL_SUBJECT_JDBC_REPOSITORY.delete(100_003));
    }
}