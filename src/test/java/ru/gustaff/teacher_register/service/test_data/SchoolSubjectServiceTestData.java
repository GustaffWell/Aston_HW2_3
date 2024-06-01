package ru.gustaff.teacher_register.service.test_data;

import ru.gustaff.teacher_register.dto.SchoolSubjectDto;

import java.util.List;

import static ru.gustaff.teacher_register.repository.test_data.SchoolSubjectRepositoryTestData.*;
import static ru.gustaff.teacher_register.service.converters.SchoolSubjectDtoJsonConverter.SCHOOL_SUBJECT_DTO_JSON_CONVERTER;

public class SchoolSubjectServiceTestData {

    public static final SchoolSubjectDto SUBJECT_1_DTO = SCHOOL_SUBJECT_DTO_JSON_CONVERTER.createDto(SUBJECT_1);
    public static final SchoolSubjectDto SUBJECT_2_DTO = SCHOOL_SUBJECT_DTO_JSON_CONVERTER.createDto(SUBJECT_2);
    public static final SchoolSubjectDto SUBJECT_3_DTO = SCHOOL_SUBJECT_DTO_JSON_CONVERTER.createDto(SUBJECT_3);
    public static final SchoolSubjectDto SUBJECT_4_DTO = SCHOOL_SUBJECT_DTO_JSON_CONVERTER.createDto(SUBJECT_4);
    public static final SchoolSubjectDto NEW_SUBJECT_DTO = new SchoolSubjectDto(100_016, "new subject dto");
    public static final SchoolSubjectDto UPDATED_SUBJECT_DTO = new SchoolSubjectDto(100_003, "updated subject dto");

    public static List<SchoolSubjectDto> ALL_SUBJECTS_DTO = List.of(SUBJECT_1_DTO, SUBJECT_2_DTO, SUBJECT_3_DTO, SUBJECT_4_DTO);
}
