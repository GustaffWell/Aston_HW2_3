package ru.gustaff.teacher_register.service.test_data;

import ru.gustaff.teacher_register.dto.TeacherDto;

import java.util.ArrayList;
import java.util.List;

import static ru.gustaff.teacher_register.service.test_data.SchoolClassServiceTestData.*;
import static ru.gustaff.teacher_register.service.test_data.SchoolSubjectServiceTestData.*;

public class TeacherServiceTestData {
    public static TeacherDto TEACHER_1_DTO = new TeacherDto(100000, "Прокудина Марина Ивановна",
            List.of(SUBJECT_1_DTO, SUBJECT_2_DTO), List.of(SCHOOL_CLASS_1_DTO));

    public static TeacherDto TEACHER_2_DTO = new TeacherDto(100001, "Даммер Манана Дмитриевна",
            List.of(SUBJECT_3_DTO, SUBJECT_4_DTO), List.of(SCHOOL_CLASS_3_DTO, SCHOOL_CLASS_2_DTO));

    public static TeacherDto TEACHER_3_DTO = new TeacherDto(100002, "Сабенина Лариса Анатольевна",
            List.of(SUBJECT_4_DTO), List.of(SCHOOL_CLASS_4_DTO));

    public static TeacherDto NEW_TEACHER_DTO = new TeacherDto(100016, "New Teacher",
            new ArrayList<>(), new ArrayList<>());

    public static TeacherDto UPDATED_TEACHER_DTO = new TeacherDto(100_000, "Updated Teacher",
            List.of(SUBJECT_1_DTO, SUBJECT_2_DTO), List.of(SCHOOL_CLASS_1_DTO));

    public static List<TeacherDto> TEACHERS_DTO = List.of(TEACHER_2_DTO, TEACHER_1_DTO, TEACHER_3_DTO);
}
