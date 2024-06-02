package ru.gustaff.teacher_register.service;

import ru.gustaff.teacher_register.dto.SchoolSubjectDto;
import ru.gustaff.teacher_register.model.SchoolSubject;
import ru.gustaff.teacher_register.repository.SchoolSubjectJdbcRepository;

import java.util.List;

import static ru.gustaff.teacher_register.service.converters.SchoolSubjectDtoJsonConverter.SCHOOL_SUBJECT_DTO_JSON_CONVERTER;

public class SchoolSubjectService {

    private final SchoolSubjectJdbcRepository schoolSubjectJdbcRepository;

    public SchoolSubjectService() {
        schoolSubjectJdbcRepository = SchoolSubjectJdbcRepository.SCHOOL_SUBJECT_JDBC_REPOSITORY;
    }

    public boolean save(SchoolSubjectDto schoolSubjectDto, int hoursPerWeek) {
        SchoolSubject schoolSubject = schoolSubjectJdbcRepository.save(SCHOOL_SUBJECT_DTO_JSON_CONVERTER.createDao(schoolSubjectDto, hoursPerWeek));
        return schoolSubject != null;
    }

    public boolean delete(int id) {
        return schoolSubjectJdbcRepository.delete(id);
    }

    public SchoolSubjectDto get(int id) {
        SchoolSubject schoolSubject = schoolSubjectJdbcRepository.get(id);
        if (schoolSubject == null) {
            return null;
        }
        return SCHOOL_SUBJECT_DTO_JSON_CONVERTER.createDto(schoolSubject);
    }

    public List<SchoolSubjectDto> getAll() {
        return schoolSubjectJdbcRepository.getAll().stream()
                .map(SCHOOL_SUBJECT_DTO_JSON_CONVERTER::createDto)
                .toList();
    }
}
