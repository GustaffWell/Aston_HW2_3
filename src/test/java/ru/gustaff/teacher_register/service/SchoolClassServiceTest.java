package ru.gustaff.teacher_register.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.gustaff.teacher_register.BaseTest;
import ru.gustaff.teacher_register.dto.SchoolClassDto;

import java.util.List;

import static ru.gustaff.teacher_register.service.test_data.SchoolClassServiceTestData.*;

public class SchoolClassServiceTest extends BaseTest {

    private final SchoolClassService schoolClassService = new SchoolClassService();

    @Test
    public void get() {
        Assertions.assertEquals(SCHOOL_CLASS_1_DTO, schoolClassService .get(100_012));
    }

    @Test
    public void getAll() {
        List<SchoolClassDto> schoolClassDtoList = ALL_CLASSES_DTO;
        Assertions.assertEquals(schoolClassDtoList, schoolClassService.getAll());
    }

    @Test
    public void save() {
        SchoolClassDto newSchoolClassDto = NEW_SCHOOL_CLASS_DTO;
        newSchoolClassDto.setId(null);
        Assertions.assertEquals(true, schoolClassService.save(SCHOOL_CLASS_1_DTO, 1950));
        Assertions.assertEquals(true, schoolClassService.save(newSchoolClassDto, 1950));
    }

    @Test
    public void delete() {
        Assertions.assertEquals(true, schoolClassService.delete(100_012));
    }
}