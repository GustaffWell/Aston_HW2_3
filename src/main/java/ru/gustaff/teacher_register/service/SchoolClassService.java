package ru.gustaff.teacher_register.service;

import ru.gustaff.teacher_register.dto.SchoolClassDto;
import ru.gustaff.teacher_register.repository.SchoolClassJdbcRepository;

import java.util.List;

import static ru.gustaff.teacher_register.repository.SchoolClassJdbcRepository.SCHOOL_CLASS_JDBC_REPOSITORY;
import static ru.gustaff.teacher_register.service.converters.SchoolClassDtoJsonConverter.SCHOOL_CLASS_DTO_JSON_CONVERTER;

public class SchoolClassService {

    private final SchoolClassJdbcRepository schoolClassJdbcRepository;

    public SchoolClassService() {
        schoolClassJdbcRepository = SCHOOL_CLASS_JDBC_REPOSITORY;
    }

    public void save(SchoolClassDto schoolClassDto, int studentsCount) {
        schoolClassJdbcRepository.save(SCHOOL_CLASS_DTO_JSON_CONVERTER.createDao(schoolClassDto, studentsCount));
    }

    public boolean delete(int id) {
        return schoolClassJdbcRepository.delete(id);
    }

    public SchoolClassDto get(int id) {
        return SCHOOL_CLASS_DTO_JSON_CONVERTER.createDto(schoolClassJdbcRepository.get(id));
    }

    public List<SchoolClassDto> getAll() {
        return schoolClassJdbcRepository.getAll().stream()
                .map(SCHOOL_CLASS_DTO_JSON_CONVERTER::createDto)
                .toList();
    }

    public boolean addOrRemoveTeacher (int classId, Integer teacherId) {
        return schoolClassJdbcRepository.addOrRemoveTeacher(classId, teacherId);
    }

}
