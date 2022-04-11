package com.iuc.cs2i4.test.gestion.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class BdManager {
    private String driverName;
    private String url;
    private String userName;
    private String userPassword;

    private Connection conn;
    private Statement statement;
    private ResultSet resultset;

    public BdManager(String url, String userName, String userPassword) {
        super();
        driverName = "com.mysql.cj.jdbc.Driver";
        this.url = url;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public BdManager() {
        super();
    }

    public void loadDriver() {
        try {
            Class.forName(driverName);
            System.out.println("==== Load Driver OK ====");
        }
        catch (ClassNotFoundException exc) {
            System.out.println("==== Load Driver Error ====");
            exc.printStackTrace();
        }
    }

    public void connectToBd() {
        try {
            conn = DriverManager.getConnection(url, userName, userPassword);
            System.out.println("==== Connection OK ====");
        }
        catch (SQLException exc) {
            System.out.println("==== Connection Error ====");
            exc.printStackTrace();
        }
    }

    public int setDataInBd(String query) {
        int result = 0;
        if (conn != null) {
            try {
                statement = conn.createStatement();
            }
            catch (SQLException exc) {
                exc.printStackTrace();
            }
        }
        try {
            result = statement.executeUpdate(query);
        }
        catch (SQLException exc) {
            exc.printStackTrace();
        }

        return result;
    }

    public void close() {
        if (resultset != null) {
            try {
                resultset.close();
            }
            catch (SQLException exc) {
                exc.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            }
            catch (SQLException exc) {
                exc.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            }
            catch (SQLException exc) {
                exc.printStackTrace();
            }
        }
    }
}
