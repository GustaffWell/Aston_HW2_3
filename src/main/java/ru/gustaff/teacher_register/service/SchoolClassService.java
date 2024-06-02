package ru.gustaff.teacher_register.service;

import ru.gustaff.teacher_register.dto.SchoolClassDto;
import ru.gustaff.teacher_register.model.SchoolClass;
import ru.gustaff.teacher_register.repository.SchoolClassJdbcRepository;

import java.util.List;

import static ru.gustaff.teacher_register.repository.SchoolClassJdbcRepository.SCHOOL_CLASS_JDBC_REPOSITORY;
import static ru.gustaff.teacher_register.service.converters.SchoolClassDtoJsonConverter.SCHOOL_CLASS_DTO_JSON_CONVERTER;

public class SchoolClassService {

    private final SchoolClassJdbcRepository schoolClassJdbcRepository;

    public SchoolClassService() {
        schoolClassJdbcRepository = SCHOOL_CLASS_JDBC_REPOSITORY;
    }

    public boolean save(SchoolClassDto schoolClassDto, int studentsCount) {
        SchoolClass schoolClass = schoolClassJdbcRepository.save(SCHOOL_CLASS_DTO_JSON_CONVERTER.createDao(schoolClassDto, studentsCount));
        return schoolClass != null;
    }

    public boolean delete(int id) {
        return schoolClassJdbcRepository.delete(id);
    }

    public SchoolClassDto get(int id) {
        SchoolClass schoolClass = schoolClassJdbcRepository.get(id);
        if (schoolClass == null) {
            return null;
        }
        return SCHOOL_CLASS_DTO_JSON_CONVERTER.createDto(schoolClass);
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
