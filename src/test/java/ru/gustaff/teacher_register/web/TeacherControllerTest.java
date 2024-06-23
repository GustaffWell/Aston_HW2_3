package ru.gustaff.teacher_register.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.gustaff.teacher_register.dto.TeacherDto;
import ru.gustaff.teacher_register.service.TeacherService;
import ru.gustaff.teacher_register.service.converters.TeacherDtoConverter;
import ru.gustaff.teacher_register.service.test_data.TeacherServiceTestData;

import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gustaff.teacher_register.service.test_data.TeacherServiceTestData.TEACHER_2_DTO;
import static ru.gustaff.teacher_register.service.test_data.TeacherServiceTestData.UPDATED_TEACHER_DTO;

@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_CLASS,scripts="classpath:db/initDB.sql")
class TeacherControllerTest extends AbstractControllerTest{

    private static final String REST_URL = "/api/teacher";

    @Autowired
    private TeacherService teacherService;

    @Test
    void getTeacher() throws Exception {
        perform(get(REST_URL + "/100001"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(TeacherDtoConverter.TEACHER_DTO_JSON_CONVERTER.toJson(TEACHER_2_DTO)));
    }

    @Test
    void getAll() throws Exception {
        MvcResult mvcResult = getMockMvc().perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONCompare.compareJSON(TeacherServiceTestData.getAllTeachersJson(),
                mvcResult.getResponse().getContentAsString(), JSONCompareMode.LENIENT);
    }

    @Test
    void createWithLocation() throws Exception {
        TeacherDto newTeacher = new TeacherDto(null, "New Teacher",
                new HashSet<>(), new HashSet<>());
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "/1995")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TeacherDtoConverter.TEACHER_DTO_JSON_CONVERTER.toJson(newTeacher)))
                .andExpect(status().isCreated());

        TeacherDto created = TeacherDtoConverter.TEACHER_DTO_JSON_CONVERTER.fromJson(action.andReturn()
                .getResponse()
                .getContentAsString());
        int newId = created.getId();
        newTeacher.setId(newId);
        Assertions.assertEquals(newTeacher, created);
        Assertions.assertEquals(newTeacher, teacherService.get(newId));
    }

    @Test
    void update() throws Exception {
        TeacherDto updatedTeacher = UPDATED_TEACHER_DTO;
        perform(MockMvcRequestBuilders.put(REST_URL + "/100000/1995")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TeacherDtoConverter.TEACHER_DTO_JSON_CONVERTER.toJson(updatedTeacher)))
                .andExpect(status().isNoContent());

        Assertions.assertEquals(updatedTeacher, teacherService.get(UPDATED_TEACHER_DTO.getId()));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL +"/100003"))
                .andDo(print())
                .andExpect(status().isNoContent());
        Assertions.assertEquals(null, teacherService.get(100003));
    }
}