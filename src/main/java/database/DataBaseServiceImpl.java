package database;

import controller.DataBaseService;
import models.hero.Hero;
import utils.ErrorCode;
import utils.exceptions.SqlRunningException;

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
                    "'heroes' ('name' text, 'race' text, 'level' INT, 'exp' INT, " +
                    "'attack' INT, 'defense' INT, 'actualHp' INT, 'maxHp' INT, 'x' INT, 'y' INT, 'artefactName' text, 'artefactAttack' INT);");
        } catch (SQLException e) {
            throw new SqlRunningException("Cant create connection to database", e, ErrorCode.SQL_RUNNING_ERROR.getCode());
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
            throw new SqlRunningException("Cant execute query 'select * from heroes' into database", e, ErrorCode.SQL_RUNNING_ERROR.getCode());
        }
        return names;
    }

    @Override
    public void addNewHero(Hero heroToAdd) {
        String requestAdd = " VALUES ('" +
                heroToAdd.getName() + "', '" +
                heroToAdd.getClas() + "', " +
                heroToAdd.getLevel() + "," +
                heroToAdd.getExp() + "," +
                heroToAdd.getAttack() + "," +
                heroToAdd.getDefence() + "," +
                heroToAdd.getActualHp() + "," +
                heroToAdd.getMaxHp() + ", " +
                heroToAdd.getX() + ", " +
                heroToAdd.getY() + ",'" +
                heroToAdd.getArtefactName() + "'," +
                heroToAdd.getArtefactAttack() + ");";
        try {
            statement.execute("INSERT INTO 'heroes' ('name', 'race', 'level', 'exp', 'attack', 'defense', 'actualHp', 'maxHP', 'x', 'y', 'artefactName', 'artefactAttack')" + requestAdd);
        } catch (SQLException e) {
            throw new SqlRunningException("Cant execute query 'insert into heroes ...' into database", e, ErrorCode.SQL_RUNNING_ERROR.getCode());
        }
    }

    @Override
    public Hero findByName(String name)
    {
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery("SELECT * FROM 'heroes' WHERE name = '" + name + "'");
            return new Hero(resultSet.getString("name"), resultSet.getString("race"), resultSet.getInt("level"),
                    resultSet.getInt("exp"), resultSet.getInt("attack"), resultSet.getInt("defense"),
                    resultSet.getInt("actualHp"), resultSet.getInt("maxHp"),
                    resultSet.getInt("x"), resultSet.getInt("y"),
                    resultSet.getString("artefactName"), resultSet.getInt("artefactAttack"));
        } catch (SQLException e) {
            throw new SqlRunningException("Cant execute query 'select * from heroes ...' into database", e, ErrorCode.SQL_RUNNING_ERROR.getCode());
        }
    }
}
