package ru.croc.java.school.finalTask.db.dbprovider;

import org.apache.derby.jdbc.EmbeddedDataSource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Провайдер.
 */
public class DataSourceProvider {

    /**
     * Подключение.
     */
    private EmbeddedDataSource dataSource;

    /**
     * Параметры конфигурации.
     */
    private Map<String, String> properties = new HashMap<>();

    /** Конструктор принимающий путь к файлу для загрузки свойств.*/
    public DataSourceProvider() throws IOException {
        loadProperties();
    }

    /** Загрузка свойств.*/
    private void loadProperties() throws IOException {

        Properties properties = new Properties();
        try {
            properties.load(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("city.properties"));
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                this.properties.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка при загрузке свойств");
            throw e;
        }
    }

    /**
     * Получить данные с файла о БД.(Название, логин, пароль).
     *
     * @return данные для инициализации талицы.
     */
    public EmbeddedDataSource getDataSource() {

        if (dataSource == null) {
            dataSource = new EmbeddedDataSource();
            dataSource.setDatabaseName(properties.get("database.name"));
            String username = properties.get("database.username");
            String password = properties.get("database.password");
            if (username != null && !username.isEmpty()
                    && password != null && !password.isEmpty()) {
                dataSource.setUser(username);
                dataSource.setPassword(password);
            }
            dataSource.setCreateDatabase("create");
        }
        return dataSource;
    }
}