package ru.gustaff.teacher_register.service.converters;

import ru.gustaff.teacher_register.dto.SchoolClassDto;
import ru.gustaff.teacher_register.entity.SchoolClass;

public class SchoolClassDtoConverter extends AbstractDtoJsonConverter<SchoolClassDto>{

    public static final SchoolClassDtoConverter SCHOOL_CLASS_DTO_JSON_CONVERTER = new SchoolClassDtoConverter();

    protected SchoolClassDtoConverter() {
        super(SchoolClassDto.class);
    }

    public SchoolClassDto createDto(SchoolClass schoolClass) {
        return new SchoolClassDto(schoolClass.getId(), schoolClass.getName());
    }

    public SchoolClass createDao(SchoolClassDto schoolClassDto, int studentsCount) {
        return new SchoolClass(schoolClassDto.getId(), schoolClassDto.getNumberAndLetter(), studentsCount);
    }
}
