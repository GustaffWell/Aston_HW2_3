package ru.gustaff.teacher_register.repository.test_data;

import ru.gustaff.teacher_register.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryTestData {

    public static Teacher TEACHER_1 = new Teacher(100000, "Прокудина Марина Ивановна", 1965,
            new ArrayList<>(), new ArrayList<>());
    public static Teacher TEACHER_2 = new Teacher(100001, "Даммер Манана Дмитриевна", 1975,
            new ArrayList<>(), new ArrayList<>());
    public static Teacher TEACHER_3 = new Teacher(100002, "Сабенина Лариса Анатольевна", 1978,
            new ArrayList<>(), new ArrayList<>());
    public static Teacher NEW_TEACHER = new Teacher(100016, "New Teacher", 1995,
            new ArrayList<>(), new ArrayList<>());
    public static Teacher UPDATED_TEACHER = new Teacher(100_000, "Updated Teacher", 1955,
            new ArrayList<>(), new ArrayList<>());

    public static List<Teacher> TEACHERS = List.of(TEACHER_2, TEACHER_1, TEACHER_3);
}
