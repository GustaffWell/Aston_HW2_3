package ru.gustaff.teacher_register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gustaff.teacher_register.dto.SchoolClassDto;
import ru.gustaff.teacher_register.entity.SchoolClass;
import ru.gustaff.teacher_register.repository.SchoolClassRepository;

import java.util.List;

import static ru.gustaff.teacher_register.service.converters.SchoolClassDtoConverter.SCHOOL_CLASS_DTO_JSON_CONVERTER;

@Service
public class SchoolClassService {

    private final SchoolClassRepository schoolClassRepository;

    @Autowired
    public SchoolClassService(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    public SchoolClassDto save(SchoolClassDto schoolClassDto, int studentsCount) {
        SchoolClass schoolClass = schoolClassRepository.save(SCHOOL_CLASS_DTO_JSON_CONVERTER.createDao(schoolClassDto, studentsCount));
        return SCHOOL_CLASS_DTO_JSON_CONVERTER.createDto(schoolClass);
    }

    public boolean delete(int id) {
        return schoolClassRepository.delete(id);
    }

    public SchoolClassDto get(int id) {
        SchoolClass schoolClass = schoolClassRepository.get(id);
        if (schoolClass == null) {
            return null;
        }
        return SCHOOL_CLASS_DTO_JSON_CONVERTER.createDto(schoolClass);
    }

    public List<SchoolClassDto> getAll() {
        return schoolClassRepository.getAll().stream()
                .map(SCHOOL_CLASS_DTO_JSON_CONVERTER::createDto)
                .toList();
    }
}
