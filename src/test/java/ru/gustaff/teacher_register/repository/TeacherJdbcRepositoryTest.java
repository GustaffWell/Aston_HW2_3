package ru.gustaff.teacher_register.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.gustaff.teacher_register.model.Teacher;

import java.util.ArrayList;

import static ru.gustaff.teacher_register.repository.TeacherJdbcRepository.TEACHER_JDBC_REPOSITORY;
import static ru.gustaff.teacher_register.repository.test_data.TeacherTestData.*;

public class TeacherJdbcRepositoryTest extends BaseTest{

    @Test
    public void getById() {
        Teacher teacher = TEACHER_JDBC_REPOSITORY.get(100_000);
        Assertions.assertEquals(TEACHER_1, teacher);
    }

    @Test
    public void getNotExistId() {
        Assertions.assertEquals(null, TEACHER_JDBC_REPOSITORY.get(1));
    }

    @Test
    public void getAll() {
        Assertions.assertEquals(TEACHERS, TEACHER_JDBC_REPOSITORY.getAll());
    }

    @Test
    public void add() {
        Teacher teacher = new Teacher(null, "New Teacher", 1995,
                new ArrayList<>(), new ArrayList<>());
        Assertions.assertEquals(NEW_TEACHER, TEACHER_JDBC_REPOSITORY.save(teacher));
    }

    @Test
    public void update() {
        Teacher teacher = new Teacher(100_000, "Updated Teacher", 1955,
                new ArrayList<>(), new ArrayList<>());
        Assertions.assertEquals(UPDATED_TEACHER, TEACHER_JDBC_REPOSITORY.save(teacher));
    }

    @Test
    public void delete() {
        Assertions.assertEquals(true, TEACHER_JDBC_REPOSITORY.delete(100_000));
    }

    @Test
    public void addSubject() {
        Assertions.assertEquals(true, TEACHER_JDBC_REPOSITORY.addSubject(100_000, 100_006));
    }

    @Test
    public void deleteSubject() {
        Assertions.assertEquals(true, TEACHER_JDBC_REPOSITORY.deleteSubject(100_000, 100_003));
    }
}