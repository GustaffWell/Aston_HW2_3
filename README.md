Path to init DB file src/main/resources/db/initDB.sql

# For tests in Postman

# 1) TeacherServlet
## Get teacher by id
GET http://localhost:8080/Aston_HW2_JDBC/teacher?teacher-id=100000
## Get all teachers
GET http://localhost:8080/Aston_HW2_JDBC/teacher
## Add new teacher
POST http://localhost:8080/Aston_HW2_JDBC/teacher?year-of-birth=1990
BODY {"name": "New Teacher"}
## Update teacher
POST http://localhost:8080/Aston_HW2_JDBC/teacher?year-of-birth=1990
BODY {"id": 100000, "name": "Updated Teacher"}
## Delete teacher by id
DELETE http://localhost:8080/Aston_HW2_JDBC/teacher?teacher-id=100000
## Add subject to teacher
PUT http://localhost:8080/Aston_HW2_JDBC/teacher?action=add-subject&teacher-id=100000&subject-id=100006
## Remove subject from teacher
PUT http://localhost:8080/Aston_HW2_JDBC/teacher?action=delete-subject&teacher-id=100000&subject-id=100006

# 2) SchoolSubjectServlet
## Get subject by id
GET http://localhost:8080/Aston_HW2_JDBC/subject?subject-id=100003
## Get all subjects
GET http://localhost:8080/Aston_HW2_JDBC/subject
## Add new subject
POST http://localhost:8080/Aston_HW2_JDBC/subject?hours-per-week=1
BODY {"name": "New Subject"}
## Update subject
POST http://localhost:8080/Aston_HW2_JDBC/subject?hours-per-week=1
BODY {"id": 100003, "name": "Updated Subject"}
## Delete subject
DELETE http://localhost:8080/Aston_HW2_JDBC/subject?subject-id=100003

# 2) SchoolClassServlet
## Get class by id
GET http://localhost:8080/Aston_HW2_JDBC/class?class-id=100012
## Get all classes
GET http://localhost:8080/Aston_HW2_JDBC/class
## Add new class
POST http://localhost:8080/Aston_HW2_JDBC/class?students-count=30
BODY {"numberAndLetter": "1а"}
## Update class
POST http://localhost:8080/Aston_HW2_JDBC/class?students-count=30
BODY {"id": 100012, "numberAndLetter": "11 updated"}
## Delete class
DELETE http://localhost:8080/Aston_HW2_JDBC/class?class-id=100012
## Add teacher to class
PUT http://localhost:8080/Aston_HW2_JDBC/class?class-id=100012&teacher-id=100000
## Delete teacher from class
PUT http://localhost:8080/Aston_HW2_JDBC/class?class-id=100012

