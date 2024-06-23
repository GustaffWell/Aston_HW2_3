package ru.gustaff.teacher_register.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.gustaff.teacher_register.entity.SchoolClass;

import static ru.gustaff.teacher_register.repository.test_data.SchoolClassRepositoryTestData.*;

@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:db/initDB.sql")
class SchoolClassJdbcRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Test
    void getById() {
        SchoolClass schoolClass = schoolClassRepository.get(100_012);
        Assertions.assertEquals(SCHOOL_CLASS_1, schoolClass);
    }

    @Test
    void getNotExistId() {
        Assertions.assertEquals(null, schoolClassRepository.get(1));
    }

    @Test
    void getAll() {
        Assertions.assertEquals(ALL_SCHOOL_CLASSES, schoolClassRepository.getAll());
    }

    @Test
    void add() {
        SchoolClass schoolClass = new SchoolClass(null, "new", 10);
        Assertions.assertEquals(NEW_SCHOOL_CLASS, schoolClassRepository.save(schoolClass));
    }

    @Test
    void update() {
        SchoolClass schoolClass = new SchoolClass(100_012,"updated", 3);
        Assertions.assertEquals(UPDATED_SCHOOL_CLASS, schoolClassRepository.save(schoolClass));
    }

    @Test
    void delete() {
        Assertions.assertEquals(true, schoolClassRepository.delete(100_012));
    }
}