DROP TABLE IF EXISTS teacher_subject;
DROP TABLE IF EXISTS school_subject;
DROP TABLE IF EXISTS school_class;
DROP TABLE IF EXISTS teacher;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE teacher
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                           NOT NULL,
    year_of_birth    INTEGER                           NOT NULL
);

CREATE TABLE school_subject
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name            VARCHAR NOT NULL,
    hours_per_week  INTEGER NOT NULL
);

CREATE TABLE teacher_subject
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    teacher_id       INTEGER NOT NULL REFERENCES teacher,
    subject_id       INTEGER NOT NULL REFERENCES school_subject,
    UNIQUE (teacher_id, subject_id)
);

CREATE TABLE school_class
(
    id                  INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    teacher_id          INTEGER,
    number_and_letter   VARCHAR             NOT NULL,
    students_count      INT                 NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES teacher (id)
);
