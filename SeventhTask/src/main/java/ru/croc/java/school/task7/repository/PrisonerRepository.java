package ru.croc.java.school.task7.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.java.school.task7.entity.Prisoner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO.
 * Репозиторий для доступа к таблице с данными о заключенных.
 * CRUD-операции (Create, Read, Update, Delete).
 */
public class PrisonerRepository {

    /**
     * Название таблицы.
     */
    private static final String TABLE_NAME = "prisoner";

    private final EmbeddedDataSource dataSource;

    public PrisonerRepository(EmbeddedDataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * Метод инициализации БД.
     */
    private void initTable() {
        System.out.println(String.format("Начать инициализацию %s таблицы", TABLE_NAME));
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            DatabaseMetaData databaseMetadata = connection.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(
                    null,
                    null,
                    TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"}); // Поиск осущ. в верхнем регистре.
            if (resultSet.next()) {
                System.out.println("Таблица уже инициализирована");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + TABLE_NAME
                                + " ("
                                + "id INTEGER PRIMARY KEY,"
                                + "name VARCHAR (255),"
                                + "age INTEGER,"
                                + "inJail boolean,"
                                + "startDate VARCHAR (255),"
                                + "endDate VARCHAR (255),"
                                + "verdict INTEGER"
                                + ")");
                System.out.println("Таблица была успешно инициализирована");
            }
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при инициализации таблицы: " + e.getMessage());
        } finally {
            System.out.println("=========================");
        }
    }

    /**
     * Метод поиска всех заключенных в БД. (Read)
     *
     * @return список всех магазинов.
     */
    public List<Prisoner> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            List<Prisoner> prisoners = new ArrayList<>();
            while (resultSet.next()) {
                prisoners.add(
                        new Prisoner(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getBoolean("inJail"),
                                resultSet.getString("startDate"),
                                resultSet.getString("endDate"),
                                resultSet.getInt("verdict")));
            }
            return prisoners;
        } catch (SQLException e) {
            e.getMessage();
        }
        return new ArrayList<>();
    }

    /**
     * Метод создания записи о новом заключенном в БД. (Create)
     *
     * @param prisoner заключенный.
     */
    public void createNew(Prisoner prisoner) throws SQLException {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, prisoner.getId().toString());
            statement.setString(2, prisoner.getName());
            statement.setString(3, prisoner.getAge().toString());
            statement.setString(4, prisoner.getInJail().toString());
            statement.setString(5, prisoner.getStartDate());
            statement.setString(6, prisoner.getEndDate());
            statement.setString(7, prisoner.getVerdict().toString());
            statement.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Удалить запись о заключенном из БД. (Delete)
     *
     * @param id идентификатор объекта.
     */
    public void deleteRecord(Integer id) throws SQLException {
        String sqlQuery = "DELETE FROM " + TABLE_NAME + " WHERE id=" + id;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Обновить БД, после изменения какой-либо записи о заключенном в БД. (Update)
     *
     * @param columnTitle имя столбца в БД.
     * @param result параметр, на который будет изменён столбец.
     * @param id идентификатор объекта.
     */
    public void updateTable(String columnTitle, String result, Boolean chek, Integer id) throws SQLException {
        String sqlUpdateQuery;
        // Если вернули true, то формируем SQL запрос под число.
        // Если вернули false, то форммируем SQL запрос под строку.
        if (chek) {
            int number = Integer.parseInt(result);
            sqlUpdateQuery = "UPDATE " + TABLE_NAME + " SET " + columnTitle + "=" + number + " WHERE id=" + id;
        } else {
            sqlUpdateQuery = "UPDATE " + TABLE_NAME + " SET " + columnTitle + "=" + "'" + result + "'" + " WHERE id=" + id;
        }
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlUpdateQuery);
        } catch (SQLException e) {
            throw e;
        }
    }

    public void deleteAll () throws SQLException {
        String sqlQuery = "DELETE FROM " + TABLE_NAME;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Метод для получения конкретного заключенного из БД.
     *
     * @param id идентификатор.
     * @return список из 1 элемента (заключенного).
     */
    public List<Prisoner> getPrisoner(Integer id) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            List<Prisoner> prisoners = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id=" + id);

            while (resultSet.next()) {
                prisoners.add(
                        new Prisoner(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getBoolean("inJail"),
                                resultSet.getString("startDate"),
                                resultSet.getString("endDate"),
                                resultSet.getInt("verdict")));
            }
            return prisoners;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}

