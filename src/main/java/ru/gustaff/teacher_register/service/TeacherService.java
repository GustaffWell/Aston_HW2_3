package ru.gustaff.teacher_register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gustaff.teacher_register.dto.TeacherDto;
import ru.gustaff.teacher_register.entity.Teacher;
import ru.gustaff.teacher_register.repository.TeacherRepository;

import java.util.List;

import static ru.gustaff.teacher_register.service.converters.TeacherDtoConverter.TEACHER_DTO_JSON_CONVERTER;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherJdbcRepository) {
        this.teacherRepository = teacherJdbcRepository;
    }

    public TeacherDto get(int id) {
        Teacher teacher = teacherRepository.get(id);
        if (teacher == null) {
            return null;
        }
        return TEACHER_DTO_JSON_CONVERTER.createDto(teacher);
    }

    public List<TeacherDto> getAll() {
        return teacherRepository.getAll().stream()
                .map(TEACHER_DTO_JSON_CONVERTER::createDto)
                .toList();
    }

    public TeacherDto save(TeacherDto teacherDto, int yearOfBirth) {
        Teacher teacher = TEACHER_DTO_JSON_CONVERTER.createDao(teacherDto, yearOfBirth);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return TEACHER_DTO_JSON_CONVERTER.createDto(savedTeacher);
    }

    public boolean delete(int id) {
        return teacherRepository.delete(id);
    }

}
