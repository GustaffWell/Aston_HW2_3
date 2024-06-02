package ru.gustaff.teacher_register.repository;

import ru.gustaff.teacher_register.model.SchoolClass;
import ru.gustaff.teacher_register.model.SchoolSubject;
import ru.gustaff.teacher_register.model.Teacher;
import ru.gustaff.teacher_register.repository.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.gustaff.teacher_register.repository.util.SchoolSubjectRepositoryUtils.SELECT_SUBJECT;
import static ru.gustaff.teacher_register.repository.util.TeacherRepositoryUtils.*;

public class TeacherJdbcRepository extends AbstractJdbcRepository<Teacher> {

    public static final TeacherJdbcRepository TEACHER_JDBC_REPOSITORY = new TeacherJdbcRepository();

    private TeacherJdbcRepository() {
    }

    @Override
    public Teacher get(int id) {
        Teacher teacher = null;
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_TEACHER)){

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (teacher == null) {
                    teacher = getNewObject(resultSet);
                }
                int subjectId  = resultSet.getInt(4);
                SchoolSubject schoolSubject = new SchoolSubject(subjectId, resultSet.getString(5),
                        resultSet.getInt(6));
                if (subjectId != 0 && !teacher.getSubjects().contains(schoolSubject)) {
                    teacher.getSubjects().add(schoolSubject);
                }
                int classsId = resultSet.getInt(7);
                SchoolClass schoolClass = new SchoolClass(classsId, resultSet.getString(8),
                        resultSet.getInt(9));
                if (classsId != 0 && !teacher.getSupervisedClasses().contains(schoolClass)) {
                    teacher.getSupervisedClasses().add(schoolClass);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    public List<Teacher> getAll() {
        Map<Integer, Teacher> teachers = new HashMap<>();
        try (Connection connection = DbConnection.getConnection();
                Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(GET_ALL_TEACHERS);
            while (resultSet.next()) {
                int teacherId = resultSet.getInt(1);
                Teacher teacher = getNewObject(resultSet);
                int subjectId = resultSet.getInt(4);
                SchoolSubject schoolSubject = new SchoolSubject(subjectId, resultSet.getString(5),
                        resultSet.getInt(6));
                int classId = resultSet.getInt(7);
                SchoolClass schoolClass = new SchoolClass(classId, resultSet.getString(8),
                        resultSet.getInt(9));
                teachers.putIfAbsent(teacherId, teacher);
                teachers.compute(teacherId, (k, v) -> {
                    if (subjectId != 0 && !v.getSubjects().contains(schoolSubject)) {
                        v.getSubjects().add(schoolSubject);
                    }
                    if (classId != 0 && !v.getSupervisedClasses().contains(schoolClass)) {
                        v.getSupervisedClasses().add(schoolClass);
                    }
                    return v;
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(teachers.values());
    }

    @Override
    public Teacher save(Teacher teacher) {
       return defaultSave(teacher, GET_TEACHER_ID_BY_NAME, GET_TEACHER_WITHOUT_CLASSES_AND_SUBJECTS);
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (Connection connection = DbConnection.getConnection()) {
            connection.setAutoCommit(false);
            defaultDelete(id, DELETE_FROM_TEACHER_SUBJECT_BY_TEACHER_ID, connection);
            removeTeacherFromClasses(id, connection);
            result = defaultDelete(id, DELETE_TEACHER, connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void removeTeacherFromClasses(int teacherId, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_TEACHER_FROM_CLASSES)) {
            preparedStatement.setInt(1, teacherId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addSubject(int teacherId, int subjectId) {
        boolean result = false;
        try (Connection connection = DbConnection.getConnection()){
            connection.setAutoCommit(false);
            if (isObjectExistInDb(teacherId, connection, GET_TEACHER_WITHOUT_CLASSES_AND_SUBJECTS) &&
                    isObjectExistInDb(subjectId, connection, SELECT_SUBJECT)) {
                insertToTeacherSubjectTable(teacherId, subjectId, connection);
                result = true;
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteSubject(int teacherId, int subjectId) {
        boolean result = false;
        try (Connection connection = DbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUBJECT_FROM_TEACHER_SUBJECT)) {

            preparedStatement.setInt(1, teacherId);
            preparedStatement.setInt(2, subjectId);
            int deletedRows = preparedStatement.executeUpdate();
            if (deletedRows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void insertToTeacherSubjectTable(int teacherId, int subjectId, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_SUBJECT);
        preparedStatement.setInt(1, teacherId);
        preparedStatement.setInt(2, subjectId);
        preparedStatement.executeUpdate();

    }

    @Override
    protected Teacher getNewObject(ResultSet resultSet) throws SQLException {
        return new Teacher(resultSet.getInt(1), resultSet.getString(2),
                resultSet.getInt(3), new ArrayList<>(), new ArrayList<>());
    }

    @Override
    protected int update(Teacher teacher, Connection connection) {
        int result = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TEACHER)) {
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setInt(2, teacher.getYearOfBirth());
            preparedStatement.setInt(3, teacher.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void create(Teacher teacher, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TEACHER)) {
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setInt(2, teacher.getYearOfBirth());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
