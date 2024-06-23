package ru.gustaff.teacher_register.service.converters;

import ru.gustaff.teacher_register.dto.SchoolSubjectDto;
import ru.gustaff.teacher_register.entity.SchoolSubject;

public class SchoolSubjectDtoConverter extends AbstractDtoJsonConverter<SchoolSubjectDto> {

    public static final SchoolSubjectDtoConverter SCHOOL_SUBJECT_DTO_JSON_CONVERTER = new SchoolSubjectDtoConverter();

    protected SchoolSubjectDtoConverter() {
        super(SchoolSubjectDto.class);
    }

    public SchoolSubjectDto createDto(SchoolSubject schoolSubject) {
        return new SchoolSubjectDto(schoolSubject.getId(), schoolSubject.getName());
    }

    public SchoolSubject createDao(SchoolSubjectDto schoolSubjectDto, int hoursPerWeek) {
        return new SchoolSubject(schoolSubjectDto.getId(), schoolSubjectDto.getName(), hoursPerWeek);
    }
}
