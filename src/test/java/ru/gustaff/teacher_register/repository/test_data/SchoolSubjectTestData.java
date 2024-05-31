package ru.gustaff.teacher_register.repository.test_data;

import ru.gustaff.teacher_register.model.SchoolSubject;

import java.util.List;

public class SchoolSubjectTestData {
    public static final SchoolSubject SUBJECT_1 = new SchoolSubject(100_003, "Русский язык", 5);
    public static final SchoolSubject SUBJECT_2 = new SchoolSubject(100_004, "Литература", 3);
    public static final SchoolSubject SUBJECT_3 = new SchoolSubject(100_005, "Физика", 2);
    public static final SchoolSubject SUBJECT_4 = new SchoolSubject(100_006, "Математика", 5);
    public static final SchoolSubject NEW_SUBJECT = new SchoolSubject(100_016, "new", 1);
    public static final SchoolSubject UPDATED_SUBJECT = new SchoolSubject(100_003, "update", 1);

    public static List<SchoolSubject> ALL_SUBJECTS = List.of(SUBJECT_1, SUBJECT_2, SUBJECT_3, SUBJECT_4);
}
