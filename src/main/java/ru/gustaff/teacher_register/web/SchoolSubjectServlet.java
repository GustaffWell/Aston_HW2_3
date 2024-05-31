package ru.gustaff.teacher_register.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.gustaff.teacher_register.dto.SchoolClassDto;
import ru.gustaff.teacher_register.dto.SchoolSubjectDto;
import ru.gustaff.teacher_register.service.SchoolClassService;
import ru.gustaff.teacher_register.service.SchoolSubjectService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static ru.gustaff.teacher_register.service.converters.SchoolClassDtoJsonConverter.SCHOOL_CLASS_DTO_JSON_CONVERTER;
import static ru.gustaff.teacher_register.service.converters.SchoolSubjectDtoJsonConverter.SCHOOL_SUBJECT_DTO_JSON_CONVERTER;

@WebServlet("/subject")
public class SchoolSubjectServlet extends HttpServlet {

    private final SchoolSubjectService schoolSubjectService= new SchoolSubjectService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (request.getParameter("subject-id") == null) {
            List<SchoolSubjectDto> schoolSubjectDtoList = schoolSubjectService.getAll();
            for (SchoolSubjectDto schoolSubjectDto : schoolSubjectDtoList) {
                out.print(SCHOOL_SUBJECT_DTO_JSON_CONVERTER.toJson(schoolSubjectDto));
            }
        } else {
            int id = Integer.parseInt(request.getParameter("subject-id"));
            SchoolSubjectDto schoolSubjectDto = schoolSubjectService.get(id);
            String schoolSubjectDtoJson = SCHOOL_SUBJECT_DTO_JSON_CONVERTER.toJson(schoolSubjectDto);
            out.print(schoolSubjectDtoJson);
        }
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder schoolSubjectDtoString = new StringBuilder();
        BufferedReader reader = req.getReader();
        while (reader.ready()) {
            schoolSubjectDtoString.append(reader.readLine());
        }
        SchoolSubjectDto schoolSubjectDto = SCHOOL_SUBJECT_DTO_JSON_CONVERTER.fromJson(schoolSubjectDtoString.toString());
        int hoursPerWeek = Integer.parseInt(req.getParameter("hours-per-week"));
        schoolSubjectService.save(schoolSubjectDto, hoursPerWeek);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("subject-id"));
        schoolSubjectService.delete(id);
    }
}
