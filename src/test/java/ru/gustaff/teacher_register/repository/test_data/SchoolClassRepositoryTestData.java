package ru.gustaff.teacher_register.repository.test_data;

import ru.gustaff.teacher_register.entity.SchoolClass;

import java.util.List;

public class SchoolClassRepositoryTestData {

    public static final SchoolClass SCHOOL_CLASS_1 = new SchoolClass(100_012,"11а", 28);
    public static final SchoolClass SCHOOL_CLASS_2 = new SchoolClass(100_013,"11б", 25);
    public static final SchoolClass SCHOOL_CLASS_3 = new SchoolClass(100_014,"10а", 23);
    public static final SchoolClass SCHOOL_CLASS_4 = new SchoolClass(100_015,"10б", 20);
    public static final SchoolClass NEW_SCHOOL_CLASS = new SchoolClass(100_016,"new", 10);
    public static final SchoolClass UPDATED_SCHOOL_CLASS = new SchoolClass(100_012,"updated", 3);

    public static List<SchoolClass> ALL_SCHOOL_CLASSES = List.of(SCHOOL_CLASS_1, SCHOOL_CLASS_2, SCHOOL_CLASS_3, SCHOOL_CLASS_4);
}
