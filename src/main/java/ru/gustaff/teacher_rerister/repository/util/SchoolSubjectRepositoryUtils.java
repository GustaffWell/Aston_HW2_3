package ru.gustaff.teacher_rerister.repository.util;

public class SchoolSubjectRepositoryUtils {

    private SchoolSubjectRepositoryUtils() {
    }

    public static final String UPDATE_SCHOOL_SUBJECT =
            """
                    UPDATE school_subject
                    SET name = ?, hours_per_week = ?
                    WHERE id = ?;
                    """;

    public static final String CREATE_SCHOOL_SUBJECT =
            """
                    INSERT INTO school_subject (name, hours_per_week)
                    VALUES (?, ?);
                    """;

    public static final String GET_SUBJECT_ID_BY_NAME =
            """
                    SELECT id FROM school_subject WHERE name=?;
                    """;

    public static final String DELETE_SUBJECT =
            """
                    DELETE FROM school_subject WHERE id=?;
                    """;

    public static final String SELECT_SUBJECT =
            """
                    SELECT * FROM school_subject
                    WHERE id=?;
                    """;

    public static final String SELECT_ALL_SUBJECTS =
            """
                    SELECT * FROM school_subject
                    """;

    public static final String DELETE_FROM_TEACHER_SUBJECT_BY_SUBJECT_ID =
            """
                    DELETE FROM teacher_subject 
                    WHERE subject_id=?;
                    """;
}
