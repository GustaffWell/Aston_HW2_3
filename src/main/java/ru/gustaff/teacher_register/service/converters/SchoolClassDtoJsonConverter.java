package ru.gustaff.teacher_register.service.converters;

import ru.gustaff.teacher_register.dto.SchoolClassDto;
import ru.gustaff.teacher_register.model.SchoolClass;

public class SchoolClassDtoJsonConverter extends AbstractDtoJsonConverter<SchoolClassDto> {

    public static final SchoolClassDtoJsonConverter SCHOOL_CLASS_DTO_JSON_CONVERTER = new SchoolClassDtoJsonConverter();

    private SchoolClassDtoJsonConverter() {
        super(SchoolClassDto.class);
    }

    public SchoolClassDto createDto(SchoolClass schoolClass) {
        return new SchoolClassDto(schoolClass.getId(), schoolClass.getName());
    }

    public SchoolClass createDao(SchoolClassDto schoolClassDto, int studentsCount) {
        return new SchoolClass(schoolClassDto.getId(), schoolClassDto.getNumberAndLetter(), studentsCount);
    }
}
