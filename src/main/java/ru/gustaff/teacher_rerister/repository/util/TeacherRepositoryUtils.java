package ru.gustaff.teacher_rerister.repository.util;

public class TeacherRepositoryUtils {

    private TeacherRepositoryUtils() {
    }

    public static final String GET_TEACHER =
            """
                    SELECT t.id, t.name, t.year_of_birth, s.id, s.name, s.hours_per_week, sc.id, sc.number_and_letter, sc.students_count
                    FROM teacher t
                    LEFT JOIN teacher_subject ts ON ts.teacher_id = t.id
                    LEFT JOIN school_subject s ON s.id = ts.subject_id
                    LEFT JOIN school_class sc ON t.id = sc.teacher_id
                    WHERE t.id = ?;
                    """;

    public static final String GET_ALL_TEACHERS =
            """
                    SELECT t.id, t.name, t.year_of_birth, s.id, s.name, s.hours_per_week, sc.id, sc.number_and_letter, sc.students_count
                    FROM teacher t
                    LEFT JOIN teacher_subject ts ON ts.teacher_id = t.id
                    LEFT JOIN school_subject s ON s.id = ts.subject_id
                    LEFT JOIN school_class sc ON t.id = sc.teacher_id;
                    """;

    public static final String CREATE_TEACHER =
            """
                    INSERT INTO teacher (name, year_of_birth)
                    VALUES (?, ?);
                    """;

    public static final String UPDATE_TEACHER =
            """
                    UPDATE teacher
                    SET name=?, year_of_birth=?
                    WHERE id=?;
                    """;

    public static final String GET_TEACHER_ID_BY_NAME =
            """
                    SELECT id FROM teacher WHERE name=?;
                    """;

    public static final String DELETE_TEACHER =
            """
                    DELETE FROM teacher WHERE id=?;
                    """;

    public static final String DELETE_FROM_TEACHER_SUBJECT_BY_TEACHER_ID =
            """
                    DELETE FROM teacher_subject
                    WHERE teacher_id=?;
                    """;

    public static final String GET_TEACHER_WITHOUT_CLASSES_AND_SUBJECTS =
            """
                    SELECT * FROM teacher
                    WHERE id=?;
                    """;

    public static final String ADD_SUBJECT =
            """
                    INSERT INTO teacher_subject (teacher_id, subject_id)
                    VALUES (?, ?);
                    """;
}
