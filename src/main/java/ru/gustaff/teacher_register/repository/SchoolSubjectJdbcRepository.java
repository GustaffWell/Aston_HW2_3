package ru.gustaff.teacher_register.repository;

import ru.gustaff.teacher_register.model.SchoolSubject;

import java.sql.*;
import java.util.List;

import static ru.gustaff.teacher_register.repository.util.SchoolSubjectRepositoryUtils.*;

public class SchoolSubjectJdbcRepository extends AbstractJdbcRepository<SchoolSubject> {

    public static final SchoolSubjectJdbcRepository SCHOOL_SUBJECT_JDBC_REPOSITORY = new SchoolSubjectJdbcRepository();

    private SchoolSubjectJdbcRepository() {
    }

    @Override
    public SchoolSubject save(SchoolSubject schoolSubject) {
        return defaultSave(schoolSubject, GET_SUBJECT_ID_BY_NAME);
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (Connection connection = DbConnection.getConnection()) {
            connection.setAutoCommit(false);
            defaultDelete(id, DELETE_FROM_TEACHER_SUBJECT_BY_SUBJECT_ID, connection);
            result = defaultDelete(id, DELETE_SUBJECT, connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public SchoolSubject get(int id) {
        return defaultGet(id, SELECT_SUBJECT);
    }

    @Override
    public List<SchoolSubject> getAll() {
        return defaultGetAll(SELECT_ALL_SUBJECTS);
    }

    @Override
    protected SchoolSubject getNewObject(ResultSet resultSet) throws SQLException {
        return new SchoolSubject(resultSet.getInt(1), resultSet.getString(2),
                resultSet.getInt(3));
    }

    @Override
    protected void create(SchoolSubject schoolSubject, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SCHOOL_SUBJECT)) {
            preparedStatement.setString(1, schoolSubject.getName());
            preparedStatement.setInt(2, schoolSubject.getHoursPerWeek());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int update(SchoolSubject schoolSubject, Connection connection) {
        int result = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SCHOOL_SUBJECT)) {
            preparedStatement.setString(1, schoolSubject.getName());
            preparedStatement.setInt(2, schoolSubject.getHoursPerWeek());
            preparedStatement.setInt(3, schoolSubject.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
