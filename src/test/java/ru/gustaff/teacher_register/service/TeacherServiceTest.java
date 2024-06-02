package ru.gustaff.teacher_register.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.gustaff.teacher_register.BaseTest;
import ru.gustaff.teacher_register.dto.TeacherDto;

import java.util.List;

import static ru.gustaff.teacher_register.service.test_data.TeacherServiceTestData.*;

public class TeacherServiceTest extends BaseTest {

    private final TeacherService teacherService = new TeacherService();

    @Test
    public void get() {
        TeacherDto teacherDto = TEACHER_1_DTO;
        Assertions.assertEquals(teacherDto, teacherService.get(100_000));
    }

    @Test
    public void getAll() {
        List<TeacherDto> teacherDtoList = TEACHERS_DTO;
        Assertions.assertEquals(teacherDtoList, teacherService.getAll());
    }

    @Test
    public void save() {
        TeacherDto teacherDto = TEACHER_1_DTO;
        TeacherDto newTeacher = NEW_TEACHER_DTO;
        newTeacher.setId(null);
        Assertions.assertEquals(true, teacherService.save(teacherDto, 1950));
        Assertions.assertEquals(true, teacherService.save(newTeacher, 1950));
    }

    @Test
    public void delete() {
        Assertions.assertEquals(true, teacherService.delete(100_000));
    }
}