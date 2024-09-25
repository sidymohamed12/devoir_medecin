package com.medecine.core;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataSource {
    void connexion();

    void initPreparedStatement(String sql) throws SQLException;

    int executeUpdate() throws SQLException;

    ResultSet executeQuery() throws SQLException;
}
