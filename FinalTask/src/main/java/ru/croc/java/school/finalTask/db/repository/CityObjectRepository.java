package ru.croc.java.school.finalTask.db.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.java.school.finalTask.db.entity.CityObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий городских объектов.
 */
public class CityObjectRepository {

    /**
     * Название таблицы.
     */
    private static final String TABLE_NAME = "city";

    /**
     * Подключение.
     */
    private final EmbeddedDataSource dataSource;

    public CityObjectRepository(EmbeddedDataSource dataSource) {
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
                                + "id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                                + "type VARCHAR (255),"
                                + "title VARCHAR (255),"
                                + "description VARCHAR (255),"
                                + "startWork TIME,"
                                + "endWork TIME"
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
     * Метод поиска всех объектов в БД. (Read)
     *
     * @return список всех объектов.
     */
    public List<CityObject> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            List<CityObject> cityObjects = new ArrayList<>();
            while (resultSet.next()) {
                cityObjects.add(
                        new CityObject(
                                resultSet.getInt("id"),
                                resultSet.getString("type"),
                                resultSet.getString("title"),
                                resultSet.getString("description"),
                                resultSet.getTime("startWork"),
                                resultSet.getTime("endWork")));

            }
            return cityObjects;
        } catch (SQLException e) {
            e.getMessage();
        }
        return new ArrayList<>();
    }

    /**
     * Метод создания записи о новом объекте в БД. (Create)
     */
    public void createNew(CityObject cityObject) throws SQLException {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + "(type, title, description, startWork, endWork)" +
                "Values(?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, cityObject.getType());
            statement.setString(2, cityObject.getTitle());
            statement.setString(3, cityObject.getDescription());
            statement.setString(4, cityObject.getStartWork().toString());
            statement.setString(5, cityObject.getEndWork().toString());
            statement.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Удалить запись об объекте из БД. (Delete)
     *
     * @param id Идентификатор объекта.
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
     * Обновить БД, после изменения какой-либо записи об объекте в БД. (Update)
     *
     * @param columnTitle Имя столбца в БД.
     * @param result Параметр, на который будет изменён столбец.
     * @param id Идентификатор объекта.
     * @return Лист с изменненым объектом.
     */
    public List<CityObject> updateTable(String columnTitle, String result, Boolean chek, Integer id) throws SQLException {
        String sqlUpdateQuery;
        // Если получили true, то формируем SQL запрос под число.
        // Если получили false, то форммируем SQL запрос под строку.
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
        return selectObject(id);
    }

    /**
     * Получить объект по id.
     *
     * @param id Идентификатор объекта.
     */
    public List<CityObject> selectObject(Integer id) {
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            List<CityObject> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id=" + id);
            while (resultSet.next()) {
                list.add(
                        new CityObject(
                                resultSet.getInt("id"),
                                resultSet.getString("type"),
                                resultSet.getString("title"),
                                resultSet.getString("description"),
                                resultSet.getTime("startWork"),
                                resultSet.getTime("endWork")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Удалить таблицу БД.
     */
    public void dropTable() throws SQLException {
        try(Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()) {
         statement.execute("DROP TABLE " + TABLE_NAME);
        }
    }
}