package dev.sweety.quizgame.utils.db;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseManager {

    private final String USERNAME;
    private final String PASSWORD;
    private final String URL;

    public DatabaseManager(String HOST, int PORT, String DATABASE, String URL, String USERNAME, String PASSWORD) {
        this("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + URL, USERNAME, PASSWORD);
    }

    public DatabaseManager(String URL, String USERNAME, String PASSWORD) {
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    private Connection connection;


    @SneakyThrows
    public Connection getConnection() {
        if (connection == null) {
            this.connection = DriverManager.getConnection(URL, this.USERNAME, this.PASSWORD);
        }
        return connection;
    }

    @SneakyThrows
    public DatabaseManager createUsersTable() {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "player_uuid VARCHAR(36) NOT NULL," +
                "score DOUBLE)";
        statement.executeUpdate(query);
        return this;
    }


    @SneakyThrows
    public void close() {
        if (this.connection != null) {
            this.connection.close();
        }
    }

}
