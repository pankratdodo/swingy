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
    private static final String connectionString = "jdbc:sqlite:".concat(System.getProperty("user.dir")).concat("/src/main/resources/saved_heroes.db");

    public void establishConnection() {
        try {
            Connection connection = DriverManager.getConnection(connectionString);
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS " +
                    "'heroes' ('name' text, 'race' text, 'level' INT, 'exp' INT, " +
                    "'attack' INT, 'defense' INT, 'actualHp' INT, 'maxHp' INT, 'x' INT, 'y' INT, 'beforeX' INT, 'beforeY' INT, 'artefactName' text, 'artefactAttack' INT);");
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
                heroToAdd.getDefense() + "," +
                heroToAdd.getActualHp() + "," +
                heroToAdd.getMaxHp() + ", " +
                heroToAdd.getX() + ", " +
                heroToAdd.getY() + ", " +
                heroToAdd.getBeforeX() + ", " +
                heroToAdd.getBeforeY() + ",'" +
                heroToAdd.getArtefactName() + "'," +
                heroToAdd.getArtefactAttack() + ");";
        try {
            statement.execute("INSERT INTO 'heroes' ('name', 'race', 'level', 'exp', 'attack', 'defense', 'actualHp', 'maxHP', 'x', 'y', 'beforeX', 'beforeY', 'artefactName', 'artefactAttack')" + requestAdd);
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
                    resultSet.getInt("beforeX"), resultSet.getInt("beforeY"),
                    resultSet.getString("artefactName"), resultSet.getInt("artefactAttack"));
        } catch (SQLException e) {
            throw new SqlRunningException("Cant execute query 'select * from heroes ...' into database", e, ErrorCode.SQL_RUNNING_ERROR.getCode());
        }
    }

    @Override
    public void updateHero(Hero hero) {
        try {
            String result = "level = " + hero.getLevel() +
                    ", exp = " + hero.getExp() +
                    ", attack = " + hero.getAttack() +
                    ", defense = " + hero.getDefense() +
                    ", actualHp = " + hero.getActualHp() +
                    ", maxHp = " + hero.getMaxHp() +
                    ", x = " + hero.getX() +
                    ", y = " + hero.getY() +
                    ", beforeX = " + hero.getBeforeX() +
                    ", beforeY = " + hero.getBeforeY() +
                    ", artefactName = '" + hero.getArtefactName() +
                    "' , artefactAttack = " + hero.getArtefactAttack();
            statement.executeUpdate("UPDATE 'heroes' SET " + result + "  WHERE name = '" + hero.getName() + "';");
        }
        catch (SQLException e)
        {
            throw new SqlRunningException("Cant execute query 'update heroes set ...' into database", e, ErrorCode.SQL_RUNNING_ERROR.getCode());
        }
    }

    @Override
    public void deleteHero(Hero hero) {
        try {
            statement.executeQuery("DELETE FROM 'heroes' WHERE name = '" + hero.getName() + "'");
        }
        catch (SQLException e)
        {
            throw new SqlRunningException("Cant execute query 'delete from heroes ...' into database", e, ErrorCode.SQL_RUNNING_ERROR.getCode());
        }
    }
}
