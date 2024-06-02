package ru.gustaff.teacher_register.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.gustaff.teacher_register.BaseTest;
import ru.gustaff.teacher_register.dto.SchoolSubjectDto;

import java.util.List;

import static ru.gustaff.teacher_register.service.test_data.SchoolSubjectServiceTestData.*;

public class SchoolSubjectServiceTest extends BaseTest {

    private final SchoolSubjectService schoolSubjectService = new SchoolSubjectService();

    @Test
    public void get() {
        Assertions.assertEquals(SUBJECT_1_DTO, schoolSubjectService.get(100_003));
    }

    @Test
    public void getAll() {
        List<SchoolSubjectDto> schoolSubjectDtoList = ALL_SUBJECTS_DTO;
        Assertions.assertEquals(schoolSubjectDtoList, schoolSubjectService.getAll());
    }

    @Test
    public void save() {
        SchoolSubjectDto newSchoolSubjectDto = NEW_SUBJECT_DTO;
        newSchoolSubjectDto.setId(null);
        Assertions.assertEquals(true, schoolSubjectService.save(SUBJECT_1_DTO, 1950));
        Assertions.assertEquals(true, schoolSubjectService.save(newSchoolSubjectDto, 1950));
    }

    @Test
    public void delete() {
        Assertions.assertEquals(true, schoolSubjectService.delete(100_003));
    }
}