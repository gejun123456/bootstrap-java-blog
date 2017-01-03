package com.rest.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by bruce.ge on 2017/1/4.
 */
@Slf4j
public class DbUtils {
    public static void checkOrCreateTables(String dbUrl, String userName, String password) {
        log.info("gonna check or create table");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("com.mysql.jdbc.Driver class not fund", e);
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException("can't connect to database ", e);
        }
        try {
            ScriptRunner runner = new ScriptRunner(conn, true, true);
            runner.runScript(new BufferedReader(new InputStreamReader(DbUtils.class
                    .getClassLoader().getResourceAsStream("ddl.sql"))));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException("connection close catch exception", e);
        }
    }
//
//    public static void main(String[] args) {
//        DbUtils.checkOrCreateTables("jdbc:mysql://localhost/?serverTimezone=GMT%2b8", "root", "");
//    }
}
