package ru.croc.java.school.finalTask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.java.school.finalTask.db.dbprovider.DataSourceProvider;
import ru.croc.java.school.finalTask.db.entity.CityObject;
import ru.croc.java.school.finalTask.db.repository.CityObjectRepository;
import ru.croc.java.school.finalTask.db.service.CityObjectService;
import ru.croc.java.school.finalTask.jaxb.parser.JaxbConverter;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Map;

/**
 * Тестовый сценарий.
 */
public class TestScenario {

    JaxbConverter converter = new JaxbConverter();
    DataSourceProvider dataSourceProvider;
    CityObjectRepository cityObjectRepository;
    CityObjectService cityObjectService;
    LocalTime sW = LocalTime.of(1, 0);
    LocalTime eW = LocalTime.of(23, 0);

    @BeforeEach
    public void setUp() {
        try {
            dataSourceProvider = new DataSourceProvider();
            cityObjectRepository = new CityObjectRepository(dataSourceProvider.getDataSource());
            cityObjectService = new CityObjectService(cityObjectRepository);
            cityObjectService.drop();
            cityObjectRepository = new CityObjectRepository(dataSourceProvider.getDataSource());
            cityObjectService = new CityObjectService(cityObjectRepository);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            System.err.println("Невозможно обратится к таблице");
            ex.printStackTrace();
        }
    }

    /**
     * Тестовый сценарий.
     */
    @Test
    public void testScenario() {

        //1) Вывод входных данных.
        Map<String, String> inputData;
        inputData = converter.recordFileToString();
        System.out.println("\n\nВходные данные: ");
        System.out.println("\nДосуговые объекты: ");
        System.out.println("=========================");
        System.out.println(inputData.get("leisure"));
        System.out.println("\nМуниципальные объекты: ");
        System.out.println("=========================");
        System.out.println(inputData.get("municipal"));

        //2) Запись полученных данных  в таблицу БД.
        try {
            cityObjectService.addAllObject();
        } catch (IOException ex) {
            System.err.println("Не удалось найти файл");
            ex.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Невозможно обратится к таблице");
            e.printStackTrace();
        }
        //Вывод информации об объектах в БД.
        System.out.println("\nТаблица");
        System.out.println("=========================");
        System.out.println(cityObjectService.getAll());

        //3) Изменение время открытия объекта Hospital.
        try {
            cityObjectService.update("startWork", "06:00:00", 1);
        } catch (SQLException e) {
            System.err.println("Невозможно обратится с таблице");
            e.printStackTrace();
        }
        //Вывод изменненого объекта.
        System.out.println("\nИзмененный объект");
        System.out.println("=========================");
        System.out.println(cityObjectService.select(1));

        //4) Удаление объекта Bowling.
        try {
            cityObjectService.delete(4);
        } catch (SQLException e) {
            System.err.println("Невозможно обратится к таблице.");
            e.printStackTrace();
        }
        //Вывод всех объектов таблицы без объекта Bowling
        System.out.println("\nТаблица без объекта Bowling");
        System.out.println("=========================");
        System.out.println(cityObjectService.getAll());

        //5) Добавление нового объекта в таблицу.
        try {
            cityObjectService.create(
                    new CityObject(
                            5, "Municipal", "Jail", "You can go to jail here", Time.valueOf(sW), Time.valueOf(eW)));
        } catch (SQLException e) {
            System.err.println("Невозможно добавить объект в таблицу");
            e.printStackTrace();
        }
        //Вывод всех объектов таблицы + объект Jail
        System.out.println("\nТаблица с добавленным объектом Jail");
        System.out.println("=========================");
        System.out.println(cityObjectService.getAll());

        Assertions.assertTrue(new File("city_db").exists());
    }
}
