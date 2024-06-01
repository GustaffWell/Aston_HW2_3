package ru.gustaff.teacher_register.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import ru.gustaff.teacher_register.dto.SchoolClassDto;
import ru.gustaff.teacher_register.repository.BaseTest;

import java.util.List;

import static ru.gustaff.teacher_register.service.test_data.SchoolClassServiceTestData.*;

public class SchoolClassServiceTest extends BaseTest {

    private final SchoolClassService schoolClassServiceMock = Mockito.mock(SchoolClassService.class);
    private final SchoolClassService schoolClassService = new SchoolClassService();

    @Test
    public void get() {
        SchoolClassDto schoolClassDto = SCHOOL_CLASS_1_DTO;
        Mockito.when(schoolClassServiceMock.get(Mockito.anyInt()))
                .thenReturn(schoolClassDto);
        Assertions.assertEquals(schoolClassDto, schoolClassService .get(100_012));
    }

    @Test
    public void getAll() {
        List<SchoolClassDto> schoolClassDtoList = ALL_CLASSES_DTO;
        Mockito.when(schoolClassServiceMock.getAll())
                .thenReturn(schoolClassDtoList);
        Assertions.assertEquals(schoolClassDtoList, schoolClassService.getAll());
    }

    @Test
    public void save() {
        SchoolClassDto schoolClassDto = SCHOOL_CLASS_1_DTO;
        SchoolClassDto newSchoolClassDto = NEW_SCHOOL_CLASS_DTO;
        newSchoolClassDto.setId(null);
        Mockito.when(schoolClassServiceMock.save(Mockito.eq(schoolClassDto), Mockito.anyInt()))
                .thenReturn(true);
        Mockito.when(schoolClassServiceMock.save(Mockito.eq(newSchoolClassDto), Mockito.anyInt()))
                .thenReturn(true);
        Assertions.assertEquals(true, schoolClassService.save(schoolClassDto, 1950));
        Assertions.assertEquals(true, schoolClassService.save(newSchoolClassDto, 1950));
    }

    @Test
    public void delete() {
        Mockito.when(schoolClassServiceMock.delete(Mockito.anyInt())).thenReturn(true);
        Assertions.assertEquals(true, schoolClassService.delete(100_012));
    }
}