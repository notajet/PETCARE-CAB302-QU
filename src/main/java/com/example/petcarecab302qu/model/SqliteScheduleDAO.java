package com.example.petcarecab302qu.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class SqliteScheduleDAO {
    private Connection connection;

    public SqliteScheduleDAO() {
        connection = SqliteConnection.getInstance();
        createScheduleTable();
    }

    /**
     * Creates the schedule table in the database if it does not already exist.
     * The table contains columns for the schedule ID, data, event, time and repeat.
     */
    private void createScheduleTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS schedule ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "date VARCHAR NOT NULL,"
                    + "event VARCHAR NOT NULL,"
                    + "time VARCHAR NOT NULL"
                    + "repeat VARCHAR NOT NULL"
                    + ")";

            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
