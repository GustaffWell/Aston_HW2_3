DELETE FROM teacher_subject;
DELETE FROM school_subject;
DELETE FROM school_class;
DELETE FROM teacher;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO teacher (name, year_of_birth)
VALUES ('Прокудина Марина Ивановна', 1965),         --100000
       ('Даммер Манана Дмитриевна', 1975),          --100001
       ('Сабенина Лариса Анатольевна', 1978);       --100002

INSERT INTO school_subject (name, hours_per_week)
VALUES ('Русский язык', 5),                            --100003
       ('Литература', 3),                              --100004
       ('Физика', 2),                                  --100005
       ('Математика', 5);                              --100006

INSERT INTO teacher_subject (teacher_id, subject_id)
VALUES (100000, 100003),                            --100007
       (100000, 100004),                            --100008
       (100001, 100005),                            --100009
       (100001, 100006),                            --100010
       (100002, 100006);                            --100011

INSERT INTO school_class (teacher_id, number_and_letter, students_count)
VALUES (100000, '11а', 28),                         --100012
       (100001, '11б', 25),                         --100013
       (100001, '10а', 23),                         --100014
       (100002, '11б', 20);                         --100015