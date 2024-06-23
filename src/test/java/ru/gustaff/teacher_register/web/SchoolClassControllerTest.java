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
import ru.gustaff.teacher_register.dto.SchoolClassDto;
import ru.gustaff.teacher_register.service.SchoolClassService;
import ru.gustaff.teacher_register.service.test_data.SchoolClassServiceTestData;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gustaff.teacher_register.service.converters.SchoolClassDtoConverter.SCHOOL_CLASS_DTO_JSON_CONVERTER;
import static ru.gustaff.teacher_register.service.test_data.SchoolClassServiceTestData.*;

@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_CLASS,scripts="classpath:db/initDB.sql")
class SchoolClassControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/api/class";

    @Autowired
    private SchoolClassService schoolClassService;

    @Test
    void getSubject() throws Exception {
        perform(get(REST_URL + "/100013"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(SCHOOL_CLASS_DTO_JSON_CONVERTER.toJson(SCHOOL_CLASS_2_DTO)));
    }

    @Test
    void getAll() throws Exception {
        MvcResult mvcResult = getMockMvc().perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONCompare.compareJSON(SchoolClassServiceTestData.getAllClassesJson(),
                mvcResult.getResponse().getContentAsString(), JSONCompareMode.LENIENT);
    }

    @Test
    void createWithLocation() throws Exception {
        SchoolClassDto newSchoolClassDto = NEW_SCHOOL_CLASS_DTO;
        newSchoolClassDto.setId(null);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "/30")
                .contentType(MediaType.APPLICATION_JSON)
                .content(SCHOOL_CLASS_DTO_JSON_CONVERTER.toJson(newSchoolClassDto)))
                .andExpect(status().isCreated());
        SchoolClassDto created = SCHOOL_CLASS_DTO_JSON_CONVERTER.fromJson(action.andReturn()
                .getResponse()
                .getContentAsString());
        int newId = created.getId();
        newSchoolClassDto.setId(newId);
        Assertions.assertEquals(newSchoolClassDto, created);
        Assertions.assertEquals(newSchoolClassDto, schoolClassService.get(newId));
    }

    @Test
    void update() throws Exception {
        SchoolClassDto updatedSchoolClassDto = UPDATED_SCHOOL_CLASS_DTO;
        perform(MockMvcRequestBuilders.put(REST_URL + "/100012/25")
                .contentType(MediaType.APPLICATION_JSON)
                .content(SCHOOL_CLASS_DTO_JSON_CONVERTER.toJson(updatedSchoolClassDto)))
                .andExpect(status().isNoContent());

        Assertions.assertEquals(updatedSchoolClassDto, schoolClassService.get(UPDATED_SCHOOL_CLASS_DTO.getId()));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL +"/100014"))
                .andDo(print())
                .andExpect(status().isNoContent());
        Assertions.assertEquals(null, schoolClassService.get(100014));
    }
}
