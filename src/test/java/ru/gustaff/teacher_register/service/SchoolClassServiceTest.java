package ru.gustaff.teacher_register.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.gustaff.teacher_register.dto.SchoolClassDto;

import java.util.List;

import static ru.gustaff.teacher_register.service.test_data.SchoolClassServiceTestData.*;

@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:db/initDB.sql")
class SchoolClassServiceTest extends AbstractServiceTest{

    @Autowired
    private SchoolClassService schoolClassService;

    @Test
    void get() {
        Assertions.assertEquals(SCHOOL_CLASS_1_DTO, schoolClassService .get(100_012));
    }

    @Test
    void getAll() {
        List<SchoolClassDto> schoolClassDtoList = ALL_CLASSES_DTO;
        Assertions.assertEquals(schoolClassDtoList, schoolClassService.getAll());
    }

    @Test
    void save() {
        SchoolClassDto newSchoolClassDto = NEW_SCHOOL_CLASS_DTO;
        newSchoolClassDto.setId(null);
        Assertions.assertEquals(SCHOOL_CLASS_1_DTO, schoolClassService.save(SCHOOL_CLASS_1_DTO, 1950));
        newSchoolClassDto.setId(100016);
        Assertions.assertEquals(newSchoolClassDto, schoolClassService.save(newSchoolClassDto, 1950));
    }

    @Test
    void delete() {
        Assertions.assertEquals(true, schoolClassService.delete(100_012));
    }
}