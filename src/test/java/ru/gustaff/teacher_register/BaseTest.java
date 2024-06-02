package ru.gustaff.teacher_register;

import org.junit.After;
import org.junit.Before;
import ru.gustaff.teacher_register.repository.util.DbConnection;

public abstract class BaseTest {

    @Before
    public void beforeTest() throws Exception {
        DbConnection.isTestProfile = true;
    }

    @After
    public void afterTest() throws Exception {
        DbConnection.getConnection().prepareStatement(populateBd).executeUpdate();
        DbConnection.isTestProfile = false;
    }

    private static final String populateBd =
            """
                    DROP SCHEMA IF EXISTS school CASCADE;
                    CREATE SCHEMA school;
                 
                    CREATE SEQUENCE school.global_seq START WITH 100000;
                                        
                    CREATE TABLE school.teacher
                    (
                        id               INTEGER PRIMARY KEY DEFAULT nextval('school.global_seq'),
                        name             VARCHAR                           NOT NULL,
                        year_of_birth    INTEGER                           NOT NULL
                    );
                                        
                    CREATE TABLE school.school_subject
                    (
                        id              INTEGER PRIMARY KEY DEFAULT nextval('school.global_seq'),
                        name            VARCHAR NOT NULL,
                        hours_per_week  INTEGER NOT NULL
                    );
                                        
                    CREATE TABLE school.teacher_subject
                    (
                        id               INTEGER PRIMARY KEY DEFAULT nextval('school.global_seq'),
                        teacher_id       INTEGER NOT NULL REFERENCES school.teacher,
                        subject_id       INTEGER NOT NULL REFERENCES school.school_subject,
                        UNIQUE (teacher_id, subject_id)
                    );
                                        
                    CREATE TABLE school.school_class
                    (
                        id                  INTEGER PRIMARY KEY DEFAULT nextval('school.global_seq'),
                        teacher_id          INTEGER,
                        number_and_letter   VARCHAR             NOT NULL,
                        students_count      INT                 NOT NULL,
                        FOREIGN KEY (teacher_id) REFERENCES school.teacher (id)
                    );
                                        
                    ALTER SEQUENCE school.global_seq RESTART WITH 100000;
                                        
                    INSERT INTO school.teacher (name, year_of_birth)
                    VALUES ('Прокудина Марина Ивановна', 1965),         --100000
                           ('Даммер Манана Дмитриевна', 1975),          --100001
                           ('Сабенина Лариса Анатольевна', 1978);       --100002
                                        
                    INSERT INTO school.school_subject (name, hours_per_week)
                    VALUES ('Русский язык', 5),                            --100003
                           ('Литература', 3),                              --100004
                           ('Физика', 2),                                  --100005
                           ('Математика', 5);                              --100006
                                        
                    INSERT INTO school.teacher_subject (teacher_id, subject_id)
                    VALUES (100000, 100003),                            --100007
                           (100000, 100004),                            --100008
                           (100001, 100005),                            --100009
                           (100001, 100006),                            --100010
                           (100002, 100006);                            --100011
                                        
                    INSERT INTO school.school_class (teacher_id, number_and_letter, students_count)
                    VALUES (100000, '11а', 28),                         --100012
                           (100001, '11б', 25),                         --100013
                           (100001, '10а', 23),                         --100014
                           (100002, '10б', 20);                         --100015
                    """;
}
