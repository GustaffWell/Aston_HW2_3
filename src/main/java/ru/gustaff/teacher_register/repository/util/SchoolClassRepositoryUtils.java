package ru.gustaff.teacher_register.repository.util;

public class SchoolClassRepositoryUtils {

    private SchoolClassRepositoryUtils() {
    }

    public static final String UPDATE_SCHOOL_CLASS =
            """
                    UPDATE school_class
                    SET number_and_letter = ?, students_count = ?
                    WHERE id = ?;
                    """;

    public static final String CREATE_SCHOOL_CLASS =
            """
                    INSERT INTO school_class (number_and_letter, students_count)
                    VALUES (?, ?);
                    """;

    public static final String GET_CLASS_ID_BY_NAME =
            """
                    SELECT id FROM school_class
                    WHERE number_and_letter=?;
                    """;

    public static final String DELETE_CLASS =
            """
                    DELETE FROM school_class WHERE id=?;
                    """;

    public static final String SELECT_CLASS =
            """
                    SELECT * FROM school_class
                    WHERE id=?;
                    """;

    public static final String SELECT_ALL_CLASSES =
            """
                    SELECT * FROM school_class
                    """;

    public static final String ADD_TEACHER_TO_CLASS =
            """
                    UPDATE school_class
                    SET teacher_id = ?
                    WHERE id = ?;
                    """;

    public static final String REMOVE_TEACHER_FROM_CLASS =
            """
                    UPDATE school_class
                    SET teacher_id = null
                    WHERE id = ?;
                    """;
}
