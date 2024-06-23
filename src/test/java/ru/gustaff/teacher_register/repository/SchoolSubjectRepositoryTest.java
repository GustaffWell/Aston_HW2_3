package ru.gustaff.teacher_register.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.gustaff.teacher_register.entity.SchoolSubject;

import static ru.gustaff.teacher_register.repository.test_data.SchoolSubjectRepositoryTestData.*;

@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:db/initDB.sql")
class SchoolSubjectRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private SchoolSubjectRepository schoolSubjectRepository;

    @Test
    void getById() {
        SchoolSubject schoolSubject = schoolSubjectRepository.get(100_003);
        Assertions.assertEquals(SUBJECT_1, schoolSubject);
    }

    @Test
    void getNotExistId() {
        Assertions.assertEquals(null, schoolSubjectRepository.get(1));
    }

    @Test
    void getAll() {
        Assertions.assertEquals(ALL_SUBJECTS, schoolSubjectRepository.getAll());
    }

    @Test
    void add() {
        SchoolSubject schoolSubject = new SchoolSubject(null, "new", 1);
        Assertions.assertEquals(NEW_SUBJECT, schoolSubjectRepository.save(schoolSubject));
    }

    @Test
    void update() {
        SchoolSubject schoolSubject = new SchoolSubject(100_003, "update", 1);
        Assertions.assertEquals(UPDATED_SUBJECT, schoolSubjectRepository.save(schoolSubject));
    }

    @Test
    void delete() {
        Assertions.assertEquals(true, schoolSubjectRepository.delete(100_003));
    }
}