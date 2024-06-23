package ru.gustaff.teacher_register.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.gustaff.teacher_register.entity.Teacher;

import java.util.HashSet;

import static ru.gustaff.teacher_register.repository.test_data.TeacherRepositoryTestData.*;

@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:db/initDB.sql")
class TeacherRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void getById() {
        Teacher teacher = teacherRepository.get(100_000);
        Assertions.assertEquals(TEACHER_1, teacher);
    }

    @Test
    void getNotExistId() {
        Assertions.assertEquals(null, teacherRepository.get(1));
    }

    @Test
    void getAll() {
        Assertions.assertEquals(TEACHERS, teacherRepository.getAll());
    }

    @Test
    void add() {
        Teacher teacher = new Teacher(null, "New Teacher", 1995,
                new HashSet<>(), new HashSet<>());
        Assertions.assertEquals(NEW_TEACHER, teacherRepository.save(teacher));
    }

    @Test
    void update() {
        Teacher teacher = UPDATED_TEACHER;
        Assertions.assertEquals(UPDATED_TEACHER, teacherRepository.save(teacher));
    }

    @Test
    void delete() {
        Assertions.assertEquals(true, teacherRepository.delete(100_000));
    }
}