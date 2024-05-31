package ru.gustaff.teacher_register.repository;

import ru.gustaff.teacher_register.model.AbstractBaseEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJdbcRepository<T extends AbstractBaseEntity> implements BaseRepository<T> {

    protected T defaultGet(int id, String sql, Connection connection) {
        T object = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                object = getNewObject(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    protected List<T> defaultGetAll(String sql) {
        List<T> objectsList = new ArrayList<>();
        try (Connection connection = DbConnection.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                objectsList.add(getNewObject(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectsList.isEmpty() ? null : objectsList;
    }

    protected abstract T getNewObject(ResultSet rs) throws SQLException ;

    protected T defaultSave(T object, String sqlSave, String sqlGet) {
        try (Connection connection = DbConnection.getConnection()){
            connection.setAutoCommit(false);
            if (object.getId() == null) {
                create(object, connection);
                int newId = getIdByName(object.getName(), connection, sqlSave);
                object.setId(newId);
            } else {
                int updatedRows = update(object, connection);
                if (updatedRows == 0) {
                    return null;
                }
            }
            object = defaultGet(object.getId(), sqlGet, connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    protected boolean defaultDelete(int id, String sql, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int deletedRows = preparedStatement.executeUpdate();
            if (deletedRows == 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    protected boolean isObjectExistInDb(int id, Connection connection, String sql) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected int getIdByName(String name, Connection connection, String sql) {
        int result = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery() ;
            resultSet.next();
            result = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected abstract void create(T object, Connection connection);

    protected abstract int update(T object, Connection connection);
}
