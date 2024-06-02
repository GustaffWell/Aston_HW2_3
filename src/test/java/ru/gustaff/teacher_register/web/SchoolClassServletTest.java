package ru.gustaff.teacher_register.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import ru.gustaff.teacher_register.BaseTest;
import ru.gustaff.teacher_register.dto.SchoolClassDto;
import ru.gustaff.teacher_register.service.SchoolClassService;

import java.io.*;

import static ru.gustaff.teacher_register.service.converters.SchoolClassDtoJsonConverter.SCHOOL_CLASS_DTO_JSON_CONVERTER;
import static ru.gustaff.teacher_register.service.test_data.SchoolClassServiceTestData.*;

public class SchoolClassServletTest extends BaseTest {

    private final SchoolClassServlet servlet = new SchoolClassServlet();
    private final SchoolClassService service = new SchoolClassService();

    @Test
    public void testDoGet() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Mockito.when(request.getParameter("class-id")).thenReturn("100012");
        StringWriter actualResult = new StringWriter();
        Mockito.when(response.getWriter()).thenReturn(new PrintWriter(actualResult));

        servlet.doGet(request, response);
        Assertions.assertEquals(SCHOOL_CLASS_DTO_JSON_CONVERTER.toJson(SCHOOL_CLASS_1_DTO),
                actualResult.toString());
    }

    @Test
    public void testDoGetAll() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Mockito.when(request.getParameter("class-id")).thenReturn(null);
        StringWriter actualResult = new StringWriter();
        Mockito.when(response.getWriter()).thenReturn(new PrintWriter(actualResult));
        servlet.doGet(request, response);
        StringBuilder expectedResult = new StringBuilder();
        for (SchoolClassDto schoolClassDto : ALL_CLASSES_DTO) {
            expectedResult.append(SCHOOL_CLASS_DTO_JSON_CONVERTER.toJson(schoolClassDto));
        }
        Assertions.assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    public void testDoPostNewClass() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        String json = "{\"numberAndLetter\":\"new class dto\"}";
        BufferedReader reader = new BufferedReader(new StringReader(json));
        Mockito.when(request.getParameter("students-count")).thenReturn("30");
        Mockito.when(request.getReader()).thenReturn(reader);
        servlet.doPost(request, response);
        SchoolClassDto schoolClassDto = service.get(100_016);
        Assertions.assertEquals(NEW_SCHOOL_CLASS_DTO, schoolClassDto);
    }

    @Test
    public void testDoPostUpdateClass() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        String json = "{\"id\":100012,\"numberAndLetter\":\"updated class dto\"}";
        BufferedReader reader = new BufferedReader(new StringReader(json));
        Mockito.when(request.getParameter("students-count")).thenReturn("30");
        Mockito.when(request.getReader()).thenReturn(reader);
        servlet.doPost(request, response);
        SchoolClassDto schoolClassDto = service.get(100_012);
        Assertions.assertEquals(UPDATED_SCHOOL_CLASS_DTO, schoolClassDto);
    }

    @Test
    public void testDoDelete() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.when(request.getParameter("class-id")).thenReturn("100012");
        servlet.doDelete(request, response);
        Assertions.assertNull(service.get(100_012));
    }
}
