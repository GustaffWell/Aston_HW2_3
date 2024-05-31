package ru.gustaff.teacher_register.repository.util;

public class TeacherRepositoryUtils {

    private TeacherRepositoryUtils() {
    }

    public static final String GET_TEACHER =
            """
                    SELECT t.id, t.name, t.year_of_birth, s.id, s.name, s.hours_per_week, sc.id, sc.number_and_letter, sc.students_count
                    FROM school.teacher t
                    LEFT JOIN school.teacher_subject ts ON ts.teacher_id = t.id
                    LEFT JOIN school.school_subject s ON s.id = ts.subject_id
                    LEFT JOIN school.school_class sc ON t.id = sc.teacher_id
                    WHERE t.id = ?;
                    """;

    public static final String GET_ALL_TEACHERS =
            """
                    SELECT t.id, t.name, t.year_of_birth, s.id, s.name, s.hours_per_week, sc.id, sc.number_and_letter, sc.students_count
                    FROM school.teacher t
                    LEFT JOIN school.teacher_subject ts ON ts.teacher_id = t.id
                    LEFT JOIN school.school_subject s ON s.id = ts.subject_id
                    LEFT JOIN school.school_class sc ON t.id = sc.teacher_id;
                    """;

    public static final String CREATE_TEACHER =
            """
                    INSERT INTO school.teacher (name, year_of_birth)
                    VALUES (?, ?);
                    """;

    public static final String UPDATE_TEACHER =
            """
                    UPDATE school.teacher
                    SET name=?, year_of_birth=?
                    WHERE id=?;
                    """;

    public static final String GET_TEACHER_ID_BY_NAME =
            """
                    SELECT id FROM school.teacher WHERE name=?;
                    """;

    public static final String DELETE_TEACHER =
            """
                    DELETE FROM school.teacher WHERE id=?;
                    """;

    public static final String DELETE_FROM_TEACHER_SUBJECT_BY_TEACHER_ID =
            """
                    DELETE FROM school.teacher_subject
                    WHERE teacher_id=?;
                    """;

    public static final String GET_TEACHER_WITHOUT_CLASSES_AND_SUBJECTS =
            """
                    SELECT * FROM school.teacher
                    WHERE id=?;
                    """;

    public static final String ADD_SUBJECT =
            """
                    INSERT INTO school.teacher_subject (teacher_id, subject_id)
                    VALUES (?, ?);
                    """;

    public static final String DELETE_SUBJECT_FROM_TEACHER_SUBJECT =
            """
                    DELETE FROM school.teacher_subject
                    WHERE teacher_id=? AND subject_id=?;
                    """;

    public static final String REMOVE_TEACHER_FROM_CLASSES =
            """
                    UPDATE school.school_class
                    SET teacher_id = null
                    WHERE teacher_id = ?;
                    """;
}
