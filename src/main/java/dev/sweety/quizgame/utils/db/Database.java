package dev.sweety.quizgame.utils.db;

import com.zaxxer.hikari.HikariDataSource;

public class Database {

    private final HikariDataSource dataSource;

    public Database(String url, String user, String password) {
        dataSource = new HikariDataSource(); //?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
    }

    public Database(String host, String port, String database, String user, String password) {
        this(String.format("jdbc:mariadb://%s:%s/%s", host, port, database), user, password);
    }
}
