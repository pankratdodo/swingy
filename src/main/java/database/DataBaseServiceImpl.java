package database;

import controller.DataBaseService;
import models.Hero;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseServiceImpl implements DataBaseService {
    private static Statement statement;
    private static final String connectionString = "jdbc:sqlite:".concat(System.getProperty("user.dir")).concat("/saved_heroes.db");

    public void establishConnection() {
        try {
            Connection connection = DriverManager.getConnection(connectionString);
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS " +
                    "'heroes' ('name' text, 'race' text, 'level' INT, 'exp' INT," +
                    "'attack' INT, 'defense' INT, 'hp' INT, 'maxHp' INT, 'artifactType' text, 'artifactValue' INT);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getHeroesNames() {
        List<String> names = new ArrayList<>();
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery("SELECT * FROM heroes");
            while (resultSet.next()) {
                names.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }

    @Override
    public void addNewHero(Hero heroToAdd) {
//        String artifactType = heroToAdd.getArtifact() == null ? ArtifactType.NO_ARTIFACT.toString() : heroToAdd.getArtifact().getArtifactType().toString();
//        int artifactValue = artifactType.equals("NO_ARTIFACT") ? 0 : heroToAdd.getArtifact().getValue();

        String requestAdd = " VALUES ('" +
                heroToAdd.getName() + "', '" +
                heroToAdd.getClas() + "', " +
                0 + "," +
                0 + "," +
                0 + "," +
                0 + "," +
                0 + "," +
                0 + ",'" +
                "aa" + "'," +
                0 + ");";
        try {
            statement.execute("INSERT INTO 'heroes' ('name', 'race', 'level', 'exp', 'attack', 'defense', 'hp', 'maxHP', 'artifactType', 'artifactValue')" + requestAdd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
