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
     * Название столбца с уникальным идентификатором объекта.
     */
    private static final String ID = "id";

    /**
     * Название столбца с типом объекта.
     */
    private static final String TYPE = "type";

    /**
     * Название столбца с названием объекта.
     */
    private static final String TITLE = "title";

    /**
     * Название столбца с описанием объекта.
     */
    private static final String DESCRIPTION = "description";

    /**
     * Название столбца со временем начала работы объекта.
     */
    private static final String STARTWORK = "startWork";

    /**
     * Название столбца со временем конца рабочего дня объекта.
     */
    private static final String ENDWORK = "endWork";

    /**
     * Подключение.
     */
    private final EmbeddedDataSource dataSource;

    /**
     * Конструктор репозитория.
     *
     * @param dataSource данные для инициализации таблицы.
     */
    public CityObjectRepository(EmbeddedDataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * Метод инициализации БД.
     */
    private void initTable() {
        System.out.println(String.format("Начать инициализацию %s таблицы", TABLE_NAME));
        try (Connection connection =
                     dataSource.getConnection();
             Statement statement =
                     connection.createStatement()) {

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
                                + ID + " INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                                + TYPE + " VARCHAR (255),"
                                + TITLE + " VARCHAR (255),"
                                + DESCRIPTION + " VARCHAR (255),"
                                + STARTWORK + " TIME,"
                                + ENDWORK + " TIME"
                                + ")");
                System.out.println("Таблица была успешно инициализирована");
                resultSet.close();
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
     * @return Список всех объектов.
     */
    public List<CityObject> findAll() {
        try (Connection connection =
                     dataSource.getConnection();
             Statement statement =
                     connection.createStatement()) {

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
            resultSet.close();
            return cityObjects;
        } catch (SQLException e) {
            System.err.println("Невозможно обратится к таблице");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Метод создания записи о новом объекте в БД. (Create)
     *
     * @param cityObject городской Объект.
     */
    public void createNew(CityObject cityObject) throws SQLException {
        String sqlQuery =
                "INSERT INTO " + TABLE_NAME + "(type, title, description, startWork, endWork)" + "Values(?, ?, ?, ?, ?)";
        try (Connection connection =
                     dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sqlQuery)) {

            statement.setString(1, cityObject.getType());
            statement.setString(2, cityObject.getTitle());
            statement.setString(3, cityObject.getDescription());
            statement.setString(4, cityObject.getStartWork().toString());
            statement.setString(5, cityObject.getEndWork().toString());
            statement.execute();
        } catch (SQLException e) {
            System.err.println("Невозможно добавить объект в таблицу");
            e.printStackTrace();
        }
    }

    /**
     * Метод удаления записи об объекте из БД. (Delete)
     *
     * @param id Идентификатор объекта.
     */
    public void deleteRecord(Integer id) throws SQLException {
        String sqlQuery = "DELETE FROM " + TABLE_NAME + " WHERE id=" + id;
        try (Connection connection =
                     dataSource.getConnection();
             Statement statement =
                     connection.createStatement()) {

            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            System.err.println("Невозможно обратится к таблице");
            e.printStackTrace();
        }
    }

    /**
     * Метод обновления записи об объекте в БД, после изменения какого-либо столбца таблицы. (Update)
     *
     * @param columnTitle Название столбца в таблице.
     * @param result Значение, на которое будет изменена строка объекта в таблице.
     * @param id Идентификатор объекта.
     * @return Лист с изменненым объектом.
     */
    public List<CityObject> updateTable(String columnTitle, String result, Boolean chek, Integer id) throws SQLException {
        String sqlUpdateQuery;

        // Если получили true, то формируем SQL запрос под число.
        if (chek) {
            int number = Integer.parseInt(result);
            sqlUpdateQuery =
                    "UPDATE " + TABLE_NAME + " SET " + columnTitle + "=" + number + " WHERE id=" + id;
            // Если получили false, то форммируем SQL запрос под строку.
        } else {
            sqlUpdateQuery =
                    "UPDATE " + TABLE_NAME + " SET " + columnTitle + "=" + "'" + result + "'" + " WHERE id=" + id;
        }
        try (Connection connection =
                     dataSource.getConnection();
             Statement statement =
                     connection.createStatement()) {

            statement.executeUpdate(sqlUpdateQuery);
        } catch (SQLException e) {
            System.err.println("Невозможно обновить таблицу");
            e.printStackTrace();
        }
        return selectObject(id);
    }

    /**
     * Метод получения объекта по id.
     *
     * @param id Идентификатор объекта.
     * @return Лист с выбранным объектом.
     */
    public List<CityObject> selectObject(Integer id) {
        try(Connection connection =
                    dataSource.getConnection();
            Statement statement =
                    connection.createStatement()) {

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
            resultSet.close();
            return list;
        } catch (SQLException e) {
            System.err.println("Невозможно обратится к таблице");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Метод удаления таблицы в БД.
     */
    public void dropTable() {
        try(Connection connection =
                    dataSource.getConnection();
        Statement statement =
                connection.createStatement()) {

         statement.execute("DROP TABLE " + TABLE_NAME);
        } catch (SQLException e) {
            System.err.println("Не возмножно обратится к таблице");
            e.printStackTrace();
        }
    }
}