package dev.sweety.quizgame.utils.db;

import dev.sweety.quizgame.utils.models.PlayerStats;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class StatsManager {

    private final DatabaseManager databaseManager;

    public StatsManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @SneakyThrows
    public void createStats(UUID playerUUID, Double score) {
        Connection connection = databaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO users (player_uuid, score) VALUES (?,?)");
        statement.setString(1, playerUUID.toString());
        statement.setDouble(2, score);
        statement.executeUpdate();
    }


    public void createStats(PlayerStats stats) {
        createStats(stats.getUuid(), stats.getScore());
    }

    @SneakyThrows
    public void updatePlayerStats(PlayerStats stats) {
        Connection connection = databaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE users SET score = ? WHERE player_uuid = ?");
        statement.setDouble(1, stats.getScore());
        statement.setString(2, stats.getUuid().toString());
        statement.executeUpdate();
    }

    @SneakyThrows
    @Nullable
    private PlayerStats findStatsByUUID(UUID uuid) {
        Connection connection = databaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE player_uuid = ?");
        statement.setString(1, uuid.toString());
        ResultSet results = statement.executeQuery();
        if (results.next()) {
            Double score = results.getDouble("score");
            return new PlayerStats(uuid, score);
        }
        return null;
    }

    public PlayerStats getStatsByUUID(UUID uuid) {
        PlayerStats stats = findStatsByUUID(uuid);
        if (stats == null) {
            stats = new PlayerStats(uuid, 0d);
            createStats(stats);
        }
        updatePlayerStats(stats);
        return stats;
    }

    public void addScore(UUID uuid, Double points){
        updatePlayerStats(getStatsByUUID(uuid).increment(points));
    }

}
