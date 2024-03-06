package com.urise.webapp.sql;

import com.urise.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public static void executeSql(ConnectionFactory connectionFactory, String statement, SqlProcess sqlProcess) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(statement)) {
            sqlProcess.execute(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public static <T> T executeSqlWithResult(ConnectionFactory connectionFactory, String statement, SqlProcessWithResult<T> sqlProcessWithResult) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(statement)) {
            return sqlProcessWithResult.executeWithResult(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public interface SqlProcess{
        void execute(PreparedStatement ps) throws StorageException, SQLException;
    }

    public interface SqlProcessWithResult<T>{
        T executeWithResult(PreparedStatement ps) throws StorageException, SQLException;
    }
}
