package ru.gustaff.teacher_register.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import ru.gustaff.teacher_register.dto.SchoolSubjectDto;
import ru.gustaff.teacher_register.repository.BaseTest;

import java.util.List;

import static ru.gustaff.teacher_register.service.test_data.SchoolSubjectServiceTestData.*;

public class SchoolSubjectServiceTest extends BaseTest {

    private final SchoolSubjectService schoolSubjectServiceMock = Mockito.mock(SchoolSubjectService.class);
    private final SchoolSubjectService schoolSubjectService = new SchoolSubjectService();

    @Test
    public void get() {
        SchoolSubjectDto schoolSubjectDto = SUBJECT_1_DTO;
        Mockito.when(schoolSubjectServiceMock.get(Mockito.anyInt()))
                .thenReturn(schoolSubjectDto);
        Assertions.assertEquals(schoolSubjectDto, schoolSubjectService.get(100_003));
    }

    @Test
    public void getAll() {
        List<SchoolSubjectDto> schoolSubjectDtoList = ALL_SUBJECTS_DTO;
        Mockito.when(schoolSubjectServiceMock.getAll())
                .thenReturn(schoolSubjectDtoList);
        Assertions.assertEquals(schoolSubjectDtoList, schoolSubjectService.getAll());
    }

    @Test
    public void save() {
        SchoolSubjectDto schoolSubjectDto = SUBJECT_1_DTO;
        SchoolSubjectDto newSchoolSubjectDto = NEW_SUBJECT_DTO;
        newSchoolSubjectDto.setId(null);
        Mockito.when(schoolSubjectServiceMock.save(Mockito.eq(schoolSubjectDto), Mockito.anyInt()))
                .thenReturn(true);
        Mockito.when(schoolSubjectServiceMock.save(Mockito.eq(newSchoolSubjectDto), Mockito.anyInt()))
                .thenReturn(true);
        Assertions.assertEquals(true, schoolSubjectService.save(schoolSubjectDto, 1950));
        Assertions.assertEquals(true, schoolSubjectService.save(newSchoolSubjectDto, 1950));
    }

    @Test
    public void delete() {
        Mockito.when(schoolSubjectServiceMock.delete(Mockito.anyInt())).thenReturn(true);
        Assertions.assertEquals(true, schoolSubjectService.delete(100_003));
    }
}