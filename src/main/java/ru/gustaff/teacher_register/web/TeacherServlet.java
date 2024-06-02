package ru.gustaff.teacher_register.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.gustaff.teacher_register.dto.TeacherDto;
import ru.gustaff.teacher_register.service.TeacherService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static ru.gustaff.teacher_register.service.converters.TeacherDtoJsonConverter.TEACHER_DTO_JSON_CONVERTER;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {

    private final TeacherService teacherService = new TeacherService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (request.getParameter("teacher-id") == null) {
            List<TeacherDto> teacherDtoList = teacherService.getAll();
            for (TeacherDto teacherDto : teacherDtoList) {
                out.print(TEACHER_DTO_JSON_CONVERTER.toJson(teacherDto));
            }
        } else {
            int id = Integer.parseInt(request.getParameter("teacher-id"));
            TeacherDto teacherDto = teacherService.get(id);
            String teacherDtoJson = TEACHER_DTO_JSON_CONVERTER.toJson(teacherDto);
            out.print(teacherDtoJson);
        }
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder teacherDtoString = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            teacherDtoString.append(line);
        }
        TeacherDto teacherDto = TEACHER_DTO_JSON_CONVERTER.fromJson(teacherDtoString.toString());
        int yearOfBirth = Integer.parseInt(req.getParameter("year-of-birth"));
        teacherService.save(teacherDto, yearOfBirth);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("teacher-id"));
        teacherService.delete(id);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int teacherId = Integer.parseInt(req.getParameter("teacher-id"));
        int subjectId = Integer.parseInt(req.getParameter("subject-id"));
        String action = req.getParameter("action");
        if (action.equals("add-subject")) {
            teacherService.addSubject(teacherId, subjectId);
        } else if (action.equals("delete-subject")) {
            teacherService.deleteSubject(teacherId, subjectId);
        }
    }
}
