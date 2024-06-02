package ru.gustaff.teacher_register.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import ru.gustaff.teacher_register.dto.SchoolSubjectDto;
import ru.gustaff.teacher_register.BaseTest;
import ru.gustaff.teacher_register.service.SchoolSubjectService;

import java.io.*;

import static ru.gustaff.teacher_register.service.converters.SchoolSubjectDtoJsonConverter.SCHOOL_SUBJECT_DTO_JSON_CONVERTER;
import static ru.gustaff.teacher_register.service.test_data.SchoolSubjectServiceTestData.*;

public class SchoolSubjectServletTest extends BaseTest {

    private final SchoolSubjectServlet servlet = new SchoolSubjectServlet();
    private final SchoolSubjectService service = new SchoolSubjectService();

    @Test
    public void testDoGet() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Mockito.when(request.getParameter("subject-id")).thenReturn("100003");
        StringWriter actualResult = new StringWriter();
        Mockito.when(response.getWriter()).thenReturn(new PrintWriter(actualResult));

        servlet.doGet(request, response);
        Assertions.assertEquals(SCHOOL_SUBJECT_DTO_JSON_CONVERTER.toJson(SUBJECT_1_DTO),
                actualResult.toString());
    }

    @Test
    public void testDoGetAll() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Mockito.when(request.getParameter("subject-id")).thenReturn(null);
        StringWriter actualResult = new StringWriter();
        Mockito.when(response.getWriter()).thenReturn(new PrintWriter(actualResult));
        servlet.doGet(request, response);
        StringBuilder expectedResult = new StringBuilder();
        for (SchoolSubjectDto schoolSubjectDto : ALL_SUBJECTS_DTO) {
            expectedResult.append(SCHOOL_SUBJECT_DTO_JSON_CONVERTER.toJson(schoolSubjectDto));
        }
        Assertions.assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    public void testDoPostNewTeacher() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        String json = "{\"name\":\"new subject dto\"}";
        BufferedReader reader = new BufferedReader(new StringReader(json));
        Mockito.when(request.getParameter("hours-per-week")).thenReturn("1");
        Mockito.when(request.getReader()).thenReturn(reader);
        servlet.doPost(request, response);
        SchoolSubjectDto schoolSubjectDto = service.get(100_016);
        Assertions.assertEquals(NEW_SUBJECT_DTO, schoolSubjectDto);
    }

    @Test
    public void testDoPostUpdateTeacher() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        String json = "{\"id\":100003,\"name\":\"updated subject dto\"}";
        BufferedReader reader = new BufferedReader(new StringReader(json));
        Mockito.when(request.getParameter("hours-per-week")).thenReturn("1");
        Mockito.when(request.getReader()).thenReturn(reader);
        servlet.doPost(request, response);
        SchoolSubjectDto schoolSubjectDto = service.get(100_003);
        Assertions.assertEquals(UPDATED_SUBJECT_DTO, schoolSubjectDto);
    }

    @Test
    public void testDoDelete() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.when(request.getParameter("subject-id")).thenReturn("100004");
        servlet.doDelete(request, response);
        Assertions.assertNull(service.get(100_004));
    }
}
