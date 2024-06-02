package ru.gustaff.teacher_register.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import ru.gustaff.teacher_register.dto.TeacherDto;
import ru.gustaff.teacher_register.BaseTest;
import ru.gustaff.teacher_register.service.TeacherService;

import java.io.*;

import static ru.gustaff.teacher_register.service.converters.TeacherDtoJsonConverter.TEACHER_DTO_JSON_CONVERTER;
import static ru.gustaff.teacher_register.service.test_data.TeacherServiceTestData.*;


public class TeacherServletTest extends BaseTest {

    private final TeacherServlet servlet = new TeacherServlet();
    private final TeacherService service = new TeacherService();

    @Test
    public void testDoGet() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Mockito.when(request.getParameter("teacher-id")).thenReturn("100000");
        StringWriter actualResult = new StringWriter();
        Mockito.when(response.getWriter()).thenReturn(new PrintWriter(actualResult));

        servlet.doGet(request, response);
        Assertions.assertEquals(TEACHER_DTO_JSON_CONVERTER.toJson(TEACHER_1_DTO),
                actualResult.toString());
    }

    @Test
    public void testDoGetAll() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Mockito.when(request.getParameter("teacher-id")).thenReturn(null);
        StringWriter actualResult = new StringWriter();
        Mockito.when(response.getWriter()).thenReturn(new PrintWriter(actualResult));
        servlet.doGet(request, response);
        StringBuilder expectedResult = new StringBuilder();
        for (TeacherDto teacherDto : TEACHERS_DTO) {
            expectedResult.append(TEACHER_DTO_JSON_CONVERTER.toJson(teacherDto));
        }
        Assertions.assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    public void testDoPostNewTeacher() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        String json = "{\"name\":\"New Teacher\"}";
        BufferedReader reader = new BufferedReader(new StringReader(json));
        Mockito.when(request.getParameter("year-of-birth")).thenReturn("1990");
        Mockito.when(request.getReader()).thenReturn(reader);
        servlet.doPost(request, response);
        TeacherDto teacherDto = service.get(100_016);
        Assertions.assertEquals(NEW_TEACHER_DTO, teacherDto);
    }

    @Test
    public void testDoPostUpdateTeacher() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        String json = "{\"id\":100000,\"name\":\"Updated Teacher\"}";
        BufferedReader reader = new BufferedReader(new StringReader(json));
        Mockito.when(request.getParameter("year-of-birth")).thenReturn("1990");
        Mockito.when(request.getReader()).thenReturn(reader);
        servlet.doPost(request, response);
        TeacherDto teacherDto = service.get(100_000);
        Assertions.assertEquals(UPDATED_TEACHER_DTO, teacherDto);
    }

    @Test
    public void testDoDelete() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.when(request.getParameter("teacher-id")).thenReturn("100001");
        servlet.doDelete(request, response);
        Assertions.assertNull(service.get(100_001));
    }
}