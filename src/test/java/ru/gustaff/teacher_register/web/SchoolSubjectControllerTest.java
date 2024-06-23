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
import ru.gustaff.teacher_register.dto.SchoolSubjectDto;
import ru.gustaff.teacher_register.service.SchoolSubjectService;
import ru.gustaff.teacher_register.service.converters.SchoolSubjectDtoConverter;
import ru.gustaff.teacher_register.service.test_data.SchoolSubjectServiceTestData;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gustaff.teacher_register.service.test_data.SchoolSubjectServiceTestData.*;

@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_CLASS,scripts="classpath:db/initDB.sql")
class SchoolSubjectControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/api/subject";

    @Autowired
    private SchoolSubjectService schoolSubjectService;

    @Test
    void getSubject() throws Exception {
        perform(get(REST_URL + "/100004"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(SchoolSubjectDtoConverter.SCHOOL_SUBJECT_DTO_JSON_CONVERTER.toJson(SUBJECT_2_DTO)));
    }

    @Test
    void getAll() throws Exception {
        MvcResult mvcResult = getMockMvc().perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONCompare.compareJSON(SchoolSubjectServiceTestData.getAllSubjectsJson(),
                mvcResult.getResponse().getContentAsString(), JSONCompareMode.LENIENT);
    }

    @Test
    void createWithLocation() throws Exception {
        SchoolSubjectDto newSchoolSubjectDto = NEW_SUBJECT_DTO;
        newSchoolSubjectDto.setId(null);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(SchoolSubjectDtoConverter.SCHOOL_SUBJECT_DTO_JSON_CONVERTER.toJson(newSchoolSubjectDto)))
                .andExpect(status().isCreated());
        SchoolSubjectDto created = SchoolSubjectDtoConverter.SCHOOL_SUBJECT_DTO_JSON_CONVERTER.fromJson(action.andReturn()
                .getResponse()
                .getContentAsString());
        int newId = created.getId();
        newSchoolSubjectDto.setId(newId);
        Assertions.assertEquals(newSchoolSubjectDto, created);
        Assertions.assertEquals(newSchoolSubjectDto, schoolSubjectService.get(newId));
    }

    @Test
    void update() throws Exception {
        SchoolSubjectDto updatedSchoolSubjectDto = UPDATED_SUBJECT_DTO;
        perform(MockMvcRequestBuilders.put(REST_URL + "/100003/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(SchoolSubjectDtoConverter.SCHOOL_SUBJECT_DTO_JSON_CONVERTER.toJson(updatedSchoolSubjectDto)))
                .andExpect(status().isNoContent());

        Assertions.assertEquals(updatedSchoolSubjectDto, schoolSubjectService.get(UPDATED_SUBJECT_DTO.getId()));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL +"/100005"))
                .andDo(print())
                .andExpect(status().isNoContent());
        Assertions.assertEquals(null, schoolSubjectService.get(100005));
    }
}
