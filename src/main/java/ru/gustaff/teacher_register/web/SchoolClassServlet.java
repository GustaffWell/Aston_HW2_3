package ru.gustaff.teacher_register.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.gustaff.teacher_register.dto.SchoolClassDto;
import ru.gustaff.teacher_register.service.SchoolClassService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static ru.gustaff.teacher_register.service.converters.SchoolClassDtoJsonConverter.SCHOOL_CLASS_DTO_JSON_CONVERTER;

@WebServlet("/class")
public class SchoolClassServlet extends HttpServlet {

    private final SchoolClassService schoolClassService= new SchoolClassService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (request.getParameter("class-id") == null) {
            List<SchoolClassDto> schoolClassDtoList = schoolClassService.getAll();
            for (SchoolClassDto schoolClassDto : schoolClassDtoList) {
                out.print(SCHOOL_CLASS_DTO_JSON_CONVERTER.toJson(schoolClassDto));
            }
        } else {
            int id = Integer.parseInt(request.getParameter("class-id"));
            SchoolClassDto schoolClassDto = schoolClassService.get(id);
            String schoolClassDtoJson = SCHOOL_CLASS_DTO_JSON_CONVERTER.toJson(schoolClassDto);
            out.print(schoolClassDtoJson);
        }
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder schoolClassDtoString = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            schoolClassDtoString.append(line);
        }
        SchoolClassDto schoolClassDto = SCHOOL_CLASS_DTO_JSON_CONVERTER.fromJson(schoolClassDtoString.toString());
        int studentsCount = Integer.parseInt(req.getParameter("students-count"));
        schoolClassService.save(schoolClassDto, studentsCount);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("class-id"));
        schoolClassService.delete(id);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int classId = Integer.parseInt(req.getParameter("class-id"));
        String teacherIdString = req.getParameter("teacher-id");
        Integer subjectId = teacherIdString == null ? null : Integer.parseInt(teacherIdString);
        schoolClassService.addOrRemoveTeacher(classId, subjectId);
    }
}
