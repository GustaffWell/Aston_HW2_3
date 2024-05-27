package ru.gustaff.teacher_rerister.service;

import ru.gustaff.teacher_rerister.dto.TeacherDto;
import ru.gustaff.teacher_rerister.model.Teacher;
import ru.gustaff.teacher_rerister.repository.SchoolClassJdbcRepository;
import ru.gustaff.teacher_rerister.repository.TeacherJdbcRepository;

import java.io.IOException;
import java.util.List;

import static ru.gustaff.teacher_rerister.service.converters.TeacherDtoJsonConverter.TEACHER_DTO_JSON_CONVERTER;

public class TeacherService {

    private final TeacherJdbcRepository teacherJdbcRepository;
    private final SchoolClassJdbcRepository schoolClassJdbcRepository;

    public TeacherService() {
        this.teacherJdbcRepository = new TeacherJdbcRepository();
        this.schoolClassJdbcRepository = new SchoolClassJdbcRepository();
    }

    public TeacherDto getTeacher(int id) {
        return TEACHER_DTO_JSON_CONVERTER.createDto(teacherJdbcRepository.get(id));
    }

    public List<TeacherDto> getAllTeachers() {
        return teacherJdbcRepository.getAll().stream()
                .map(TEACHER_DTO_JSON_CONVERTER::createDto)
                .toList();
    }

    public void save(TeacherDto teacherDto, int yearOfBirth) {
        Teacher teacher = TEACHER_DTO_JSON_CONVERTER.createDao(teacherDto, yearOfBirth);
        Teacher newTeacher = teacherJdbcRepository.save(teacher);
        System.out.println(newTeacher);
    }

    public void delete(int id) {
        boolean result = teacherJdbcRepository.delete(id);
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        TeacherService service = new TeacherService();

            service.schoolClassJdbcRepository.addOrRemoveTeacher(100012, 100000);
    }
}
