package ru.gustaff.teacher_register.service.test_data;

import ru.gustaff.teacher_register.dto.TeacherDto;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.gustaff.teacher_register.service.converters.TeacherDtoConverter.TEACHER_DTO_JSON_CONVERTER;
import static ru.gustaff.teacher_register.service.test_data.SchoolClassServiceTestData.*;
import static ru.gustaff.teacher_register.service.test_data.SchoolSubjectServiceTestData.*;

public class TeacherServiceTestData {

    public static TeacherDto TEACHER_1_DTO = new TeacherDto(100000, "Прокудина Марина Ивановна",
            Set.of(SUBJECT_1_DTO, SUBJECT_2_DTO), Set.of(SCHOOL_CLASS_1_DTO));

    public static TeacherDto TEACHER_2_DTO = new TeacherDto(100001, "Даммер Манана Дмитриевна",
            Set.of(SUBJECT_3_DTO, SUBJECT_4_DTO), Set.of(SCHOOL_CLASS_2_DTO, SCHOOL_CLASS_3_DTO));

    public static TeacherDto TEACHER_3_DTO = new TeacherDto(100002, "Сабенина Лариса Анатольевна",
            Set.of(SUBJECT_4_DTO), Set.of(SCHOOL_CLASS_4_DTO));

    public static TeacherDto NEW_TEACHER_DTO = new TeacherDto(100016, "New Teacher",
            new HashSet<>(), new HashSet<>());

    public static TeacherDto UPDATED_TEACHER_DTO = new TeacherDto(100_000, "Updated Teacher",
            Set.of(SUBJECT_1_DTO, SUBJECT_2_DTO), Set.of(SCHOOL_CLASS_1_DTO));

    public static Set<TeacherDto> TEACHERS_DTO = Set.of(TEACHER_1_DTO, TEACHER_3_DTO, TEACHER_2_DTO);

    public static List<TeacherDto> TEACHERS_DTO_LIST = List.of(TEACHER_1_DTO, TEACHER_2_DTO, TEACHER_3_DTO);

    public static String getAllTeachersJson() {
        StringBuilder sb = new StringBuilder();
        TEACHERS_DTO.forEach(teacher -> {
            try {
                sb.append(TEACHER_DTO_JSON_CONVERTER.toJson(teacher));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return sb.toString();
    }
}
