package ru.gustaff.teacher_rerister.service.converters;

import ru.gustaff.teacher_rerister.dto.SchoolClassDto;
import ru.gustaff.teacher_rerister.model.SchoolClass;

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
