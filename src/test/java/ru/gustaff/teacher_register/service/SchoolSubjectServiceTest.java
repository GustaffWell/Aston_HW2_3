package ru.gustaff.teacher_register.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.gustaff.teacher_register.dto.SchoolSubjectDto;

import java.util.List;

import static ru.gustaff.teacher_register.service.test_data.SchoolSubjectServiceTestData.*;

@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:db/initDB.sql")
class SchoolSubjectServiceTest extends AbstractServiceTest {

    @Autowired
    private SchoolSubjectService schoolSubjectService;

    @Test
    void get() {
        Assertions.assertEquals(SUBJECT_1_DTO, schoolSubjectService.get(100_003));
    }

    @Test
    void getAll() {
        List<SchoolSubjectDto> schoolSubjectDtoList = ALL_SUBJECTS_DTO;
        Assertions.assertEquals(schoolSubjectDtoList, schoolSubjectService.getAll());
    }

    @Test
    void save() {
        SchoolSubjectDto newSchoolSubjectDto = NEW_SUBJECT_DTO;
        newSchoolSubjectDto.setId(null);
        Assertions.assertEquals(SUBJECT_1_DTO, schoolSubjectService.save(SUBJECT_1_DTO, 1950));
        newSchoolSubjectDto.setId(100016);
        Assertions.assertEquals(newSchoolSubjectDto, schoolSubjectService.save(newSchoolSubjectDto, 1950));
    }

    @Test
    void delete() {
        Assertions.assertEquals(true, schoolSubjectService.delete(100_003));
    }
}