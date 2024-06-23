package ru.gustaff.teacher_register.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.gustaff.teacher_register.dto.TeacherDto;

import java.util.List;

import static ru.gustaff.teacher_register.service.test_data.TeacherServiceTestData.*;

@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:db/initDB.sql")
class TeacherServiceTest extends AbstractServiceTest {

    @Autowired
    private TeacherService teacherService;

    @Test
    void get() {
        TeacherDto teacherDto = TEACHER_1_DTO;
        Assertions.assertEquals(teacherDto, teacherService.get(100_000));
    }

    @Test
    void getAll() {
        List<TeacherDto> teacherDtoSet = TEACHERS_DTO_LIST;
        Assertions.assertEquals(teacherDtoSet, teacherService.getAll());
    }

    @Test
    void save() {
        TeacherDto teacherDto = TEACHER_1_DTO;
        TeacherDto newTeacher = NEW_TEACHER_DTO;
        newTeacher.setId(null);
        Assertions.assertEquals(teacherDto, teacherService.save(teacherDto, 1950));
        newTeacher.setId(100016);
        Assertions.assertEquals(newTeacher, teacherService.save(newTeacher, 1950));
    }

    @Test
    void delete() {
        Assertions.assertEquals(true, teacherService.delete(100_002));
    }
}