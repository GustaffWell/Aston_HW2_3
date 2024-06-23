package ru.gustaff.teacher_register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gustaff.teacher_register.dto.SchoolSubjectDto;
import ru.gustaff.teacher_register.entity.SchoolSubject;
import ru.gustaff.teacher_register.repository.SchoolSubjectRepository;

import java.util.List;

import static ru.gustaff.teacher_register.service.converters.SchoolSubjectDtoConverter.SCHOOL_SUBJECT_DTO_JSON_CONVERTER;

@Service
public class SchoolSubjectService {

    private final SchoolSubjectRepository schoolSubjectRepository;

    @Autowired
    public SchoolSubjectService(SchoolSubjectRepository schoolSubjectRepository) {
        this.schoolSubjectRepository = schoolSubjectRepository;
    }

    public SchoolSubjectDto save(SchoolSubjectDto schoolSubjectDto, int hoursPerWeek) {
        SchoolSubject schoolSubject = schoolSubjectRepository.save(SCHOOL_SUBJECT_DTO_JSON_CONVERTER.createDao(schoolSubjectDto, hoursPerWeek));
        return SCHOOL_SUBJECT_DTO_JSON_CONVERTER.createDto(schoolSubject);
    }

    public boolean delete(int id) {
        return schoolSubjectRepository.delete(id);
    }

    public SchoolSubjectDto get(int id) {
        SchoolSubject schoolSubject = schoolSubjectRepository.get(id);
        if (schoolSubject == null) {
            return null;
        }
        return SCHOOL_SUBJECT_DTO_JSON_CONVERTER.createDto(schoolSubject);
    }

    public List<SchoolSubjectDto> getAll() {
        return schoolSubjectRepository.getAll().stream()
                .map(SCHOOL_SUBJECT_DTO_JSON_CONVERTER::createDto)
                .toList();
    }
}
