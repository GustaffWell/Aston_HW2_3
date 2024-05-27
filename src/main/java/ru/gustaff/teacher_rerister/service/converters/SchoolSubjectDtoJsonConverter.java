package ru.gustaff.teacher_rerister.service.converters;

import ru.gustaff.teacher_rerister.dto.SchoolSubjectDto;
import ru.gustaff.teacher_rerister.model.SchoolSubject;

public class SchoolSubjectDtoJsonConverter extends AbstractDtoJsonConverter<SchoolSubjectDto> {

    public static final SchoolSubjectDtoJsonConverter SCHOOL_SUBJECT_DTO_JSON_CONVERTER = new SchoolSubjectDtoJsonConverter();

    private SchoolSubjectDtoJsonConverter() {
        super(SchoolSubjectDto.class);
    }

    public SchoolSubjectDto createDto(SchoolSubject schoolSubject) {
        return new SchoolSubjectDto(schoolSubject.getId(), schoolSubject.getName());
    }

    public SchoolSubject createDao(SchoolSubjectDto schoolSubjectDto, int hoursPerWeek) {
        return new SchoolSubject(schoolSubjectDto.getId(), schoolSubjectDto.getName(), hoursPerWeek);
    }
}
