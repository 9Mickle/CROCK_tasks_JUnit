package ru.croc.java.school.task7.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.java.school.task7.entity.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для доступа к таблице с данными о магазинах.  CRUD-операции (Create, Read, Update, Delete).
 */
public class ShopRepository {

    /** Название таблицы.*/
    private static final String TABLE_NAME = "shop";

    private EmbeddedDataSource dataSource;

    public ShopRepository(EmbeddedDataSource dataSource) {
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
                                + "id INTEGER PRIMARY KEY, "
                                + "title VARCHAR(255)"
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
     * Метод поиска всех магазинов в БД.
     *
     * @return список всех магазинов.
     */
    public List<Shop> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            List<Shop> taskList = new ArrayList<>();
            while (resultSet.next()) {
                taskList.add(
                        new Shop(
                                resultSet.getInt("id"),
                                resultSet.getString("title")));
            }
            return taskList;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Метод создания записи о новом магазине в БД.
     *
     * @param shop магазин.
     */
    public void createNew(Shop shop) {

        String sqlQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, shop.getId().toString());
            statement.setString(2, shop.getTitle());
            statement.execute();
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

}
