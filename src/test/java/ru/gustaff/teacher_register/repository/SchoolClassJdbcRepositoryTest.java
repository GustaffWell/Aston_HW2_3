package ru.gustaff.teacher_register.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.gustaff.teacher_register.BaseTest;
import ru.gustaff.teacher_register.model.SchoolClass;

import static ru.gustaff.teacher_register.repository.SchoolClassJdbcRepository.SCHOOL_CLASS_JDBC_REPOSITORY;
import static ru.gustaff.teacher_register.repository.test_data.SchoolClassRepositoryTestData.*;

public class SchoolClassJdbcRepositoryTest extends BaseTest {

    @Test
    public void getById() {
        SchoolClass schoolClass = SCHOOL_CLASS_JDBC_REPOSITORY.get(100_012);
        Assertions.assertEquals(SCHOOL_CLASS_1, schoolClass);
    }

    @Test
    public void getNotExistId() {
        Assertions.assertEquals(null, SCHOOL_CLASS_JDBC_REPOSITORY.get(1));
    }

    @Test
    public void getAll() {
        Assertions.assertEquals(ALL_SCHOOL_CLASSES, SCHOOL_CLASS_JDBC_REPOSITORY.getAll());
    }

    @Test
    public void add() {
        SchoolClass schoolClass = new SchoolClass(null, "new", 10);
        Assertions.assertEquals(NEW_SCHOOL_CLASS, SCHOOL_CLASS_JDBC_REPOSITORY.save(schoolClass));
    }

    @Test
    public void update() {
        SchoolClass schoolClass = new SchoolClass(100_012,"updated", 3);
        Assertions.assertEquals(UPDATED_SCHOOL_CLASS, SCHOOL_CLASS_JDBC_REPOSITORY.save(schoolClass));
    }

    @Test
    public void delete() {
        Assertions.assertEquals(true, SCHOOL_CLASS_JDBC_REPOSITORY.delete(100_012));
    }

    @Test
    public void addOrRemoveTeacher() {
        Assertions.assertEquals(true, SCHOOL_CLASS_JDBC_REPOSITORY.addOrRemoveTeacher(100_012, 100_000));
    }
}