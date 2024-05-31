package ru.gustaff.teacher_register.repository;

import ru.gustaff.teacher_register.model.SchoolClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ru.gustaff.teacher_register.repository.util.SchoolClassRepositoryUtils.*;

public class SchoolClassJdbcRepository extends AbstractJdbcRepository<SchoolClass> {

    public static final SchoolClassJdbcRepository SCHOOL_CLASS_JDBC_REPOSITORY = new SchoolClassJdbcRepository();

    private SchoolClassJdbcRepository() {
    }

    @Override
    public SchoolClass save(SchoolClass schoolClass) {
        return defaultSave(schoolClass, GET_CLASS_ID_BY_NAME);
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (Connection connection = DbConnection.getConnection()) {
            result = defaultDelete(id, DELETE_CLASS, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public SchoolClass get(int id) {
        return defaultGet(id, SELECT_CLASS);
    }

    @Override
    public List<SchoolClass> getAll() {
        return defaultGetAll(SELECT_ALL_CLASSES);
    }

    public boolean addOrRemoveTeacher (int classId, Integer teacherId) {
        boolean result = false;
        try (Connection connection = DbConnection.getConnection()) {
            result = addOrRemoveTeacher(classId, teacherId, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected boolean addOrRemoveTeacher (int classId, Integer teacherId, Connection connection) {
        String sql = teacherId == null ? REMOVE_TEACHER_FROM_CLASS : ADD_TEACHER_TO_CLASS;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            if (teacherId != null) {
                preparedStatement.setInt(1, teacherId);
                preparedStatement.setInt(2, classId);
            } else {
                preparedStatement.setInt(1, classId);
            }
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows == 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected SchoolClass getNewObject(ResultSet resultSet) throws SQLException {
        return new SchoolClass(resultSet.getInt(1), resultSet.getString(3),
                resultSet.getInt(4));
    }

    @Override
    protected void create(SchoolClass schoolClass, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SCHOOL_CLASS)) {
            preparedStatement.setString(1, schoolClass.getName());
            preparedStatement.setInt(2, schoolClass.getStudentsCount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int update(SchoolClass schoolClass, Connection connection) {
        int result = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SCHOOL_CLASS)) {
            preparedStatement.setString(1, schoolClass.getName());
            preparedStatement.setInt(2, schoolClass.getStudentsCount());
            preparedStatement.setInt(3, schoolClass.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
