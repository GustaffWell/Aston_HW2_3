package ru.gustaff.teacher_register.repository.test_data;

import ru.gustaff.teacher_register.entity.Teacher;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.gustaff.teacher_register.repository.test_data.SchoolClassRepositoryTestData.*;
import static ru.gustaff.teacher_register.repository.test_data.SchoolSubjectRepositoryTestData.*;

public class TeacherRepositoryTestData {

    public static Teacher TEACHER_1 = new Teacher(100000, "Прокудина Марина Ивановна", 1965,
            Set.of(SUBJECT_1, SUBJECT_2), Set.of(SCHOOL_CLASS_1));
    public static Teacher TEACHER_2 = new Teacher(100001, "Даммер Манана Дмитриевна", 1975,
            Set.of(SUBJECT_3, SUBJECT_4), Set.of(SCHOOL_CLASS_2, SCHOOL_CLASS_3));
    public static Teacher TEACHER_3 = new Teacher(100002, "Сабенина Лариса Анатольевна", 1978,
            Set.of(SUBJECT_4), Set.of(SCHOOL_CLASS_4));
    public static Teacher NEW_TEACHER = new Teacher(100016, "New Teacher", 1995,
            new HashSet<>(), new HashSet<>());
    public static Teacher UPDATED_TEACHER = new Teacher(100_000, "Updated Teacher", 1955,
            Set.of(SUBJECT_1, SUBJECT_2), Set.of(SCHOOL_CLASS_1));

    public static List<Teacher> TEACHERS = List.of(TEACHER_1, TEACHER_2, TEACHER_3);
}
