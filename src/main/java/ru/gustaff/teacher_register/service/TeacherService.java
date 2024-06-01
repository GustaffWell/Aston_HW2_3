package ru.gustaff.teacher_register.service;

import ru.gustaff.teacher_register.dto.TeacherDto;
import ru.gustaff.teacher_register.model.Teacher;
import ru.gustaff.teacher_register.repository.TeacherJdbcRepository;

import java.util.List;

import static ru.gustaff.teacher_register.service.converters.TeacherDtoJsonConverter.TEACHER_DTO_JSON_CONVERTER;

public class TeacherService {

    private final TeacherJdbcRepository teacherJdbcRepository;

    public TeacherService() {
        teacherJdbcRepository = TeacherJdbcRepository.TEACHER_JDBC_REPOSITORY;
    }

    public TeacherDto get(int id) {
        return TEACHER_DTO_JSON_CONVERTER.createDto(teacherJdbcRepository.get(id));
    }

    public List<TeacherDto> getAll() {
        return teacherJdbcRepository.getAll().stream()
                .map(TEACHER_DTO_JSON_CONVERTER::createDto)
                .toList();
    }

    public boolean save(TeacherDto teacherDto, int yearOfBirth) {
        Teacher teacher = TEACHER_DTO_JSON_CONVERTER.createDao(teacherDto, yearOfBirth);
        Teacher savedTeacher = teacherJdbcRepository.save(teacher);
        return savedTeacher != null;
    }

    public boolean delete(int id) {
        return teacherJdbcRepository.delete(id);
    }

    public boolean addSubject (int teacherId, int subjectId) {
        return teacherJdbcRepository.addSubject(teacherId, subjectId);
    }

    public boolean deleteSubject (int teacherId, int subjectId) {
        return teacherJdbcRepository.deleteSubject(teacherId, subjectId);
    }
}
