package ru.gustaff.teacher_register.service.test_data;

import ru.gustaff.teacher_register.dto.SchoolClassDto;

import java.io.IOException;
import java.util.List;

import static ru.gustaff.teacher_register.repository.test_data.SchoolClassRepositoryTestData.*;
import static ru.gustaff.teacher_register.service.converters.SchoolClassDtoConverter.SCHOOL_CLASS_DTO_JSON_CONVERTER;

public class SchoolClassServiceTestData {
    public static final SchoolClassDto SCHOOL_CLASS_1_DTO = SCHOOL_CLASS_DTO_JSON_CONVERTER.createDto(SCHOOL_CLASS_1);
    public static final SchoolClassDto SCHOOL_CLASS_2_DTO = SCHOOL_CLASS_DTO_JSON_CONVERTER.createDto(SCHOOL_CLASS_2);
    public static final SchoolClassDto SCHOOL_CLASS_3_DTO = SCHOOL_CLASS_DTO_JSON_CONVERTER.createDto(SCHOOL_CLASS_3);
    public static final SchoolClassDto SCHOOL_CLASS_4_DTO = SCHOOL_CLASS_DTO_JSON_CONVERTER.createDto(SCHOOL_CLASS_4);
    public static final SchoolClassDto NEW_SCHOOL_CLASS_DTO = new SchoolClassDto(100_016, "new class dto");
    public static final SchoolClassDto UPDATED_SCHOOL_CLASS_DTO = new SchoolClassDto(100_012, "updated class dto");

    public static List<SchoolClassDto> ALL_CLASSES_DTO = List.of(SCHOOL_CLASS_1_DTO, SCHOOL_CLASS_2_DTO,
            SCHOOL_CLASS_3_DTO, SCHOOL_CLASS_4_DTO);

    public static String getAllClassesJson() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        ALL_CLASSES_DTO.forEach(classDto ->
        {
            try {
                stringBuilder.append(SCHOOL_CLASS_DTO_JSON_CONVERTER.toJson(classDto));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return stringBuilder.toString();
    }
}
