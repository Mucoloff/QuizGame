package dev.sweety.quizgame.utils.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseManager {

    private final HikariDataSource dataSource;

    @SneakyThrows
    public DatabaseManager(String url, String username, String password) {
        Class.forName("com.mysql.cj.jdbc.Driver");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        dataSource = new HikariDataSource(config);
    }

    public DatabaseManager(String host, int port, String database, String username, String password) {
        this(String.format("jdbc:mysql://%s:%s/%s", host, port, database), username, password);
    }


    @SneakyThrows
    public void connect() {
        dataSource.getConnection();
    }

    @SneakyThrows
    public void createUsersTable() {
        Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "player_uuid VARCHAR(36) NOT NULL," +
                    "score INT)";
            statement.executeUpdate(query);

    }


    @SneakyThrows
    public Connection getConnection() {
        return dataSource.getConnection();
    }

    public void close() {
        dataSource.close();
    }

}
