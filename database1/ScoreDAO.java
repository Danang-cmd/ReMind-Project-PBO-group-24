package database;

import model.Score;
import java.sql.*;
import java.util.ArrayList;

public class ScoreDAO {

    public static void saveScore(String username, int score) {
        String sql = "INSERT INTO scores(username, score) VALUES(?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setInt(2, score);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Save score error: " + e.getMessage());
        }
    }

    public static ArrayList<Score> getLeaderboard() {
        ArrayList<Score> list = new ArrayList<>();

        String sql = "SELECT username, score FROM scores ORDER BY score DESC LIMIT 10";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Score(
                        rs.getString("username"),
                        rs.getInt("score")
                ));
            }
        } catch (Exception e) {
            System.out.println("Leaderboard error: " + e.getMessage());
        }

        return list;
    }
}
