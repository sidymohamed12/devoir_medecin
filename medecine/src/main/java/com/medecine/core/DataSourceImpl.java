package com.medecine.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourceImpl implements DataSource {

    protected final String url = "jdbc:mysql://localhost:3306/gesrvmedecin";
    protected final String user = "root";
    protected final String mdp = "Mohamed2709";
    protected PreparedStatement ps;
    protected Connection conn = null;

    @Override
    public void connexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, mdp);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Échec de la connexion à la BD: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void initPreparedStatement(String sql) throws SQLException {
        if (sql.toUpperCase().trim().startsWith("INSERT")) {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } else {
            ps = conn.prepareStatement(sql);
        }
    }

    public int executeUpdate() throws SQLException {
        return ps.executeUpdate();
    }

    public ResultSet executeQuery() throws SQLException {
        return ps.executeQuery();
    }

}
