package ru.croc.java.school.finalTask.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.java.school.finalTask.db.dbprovider.DataSourceProvider;
import ru.croc.java.school.finalTask.db.entity.CityObject;
import ru.croc.java.school.finalTask.db.repository.CityObjectRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CityObjectRepositoryTest {

    DataSourceProvider dataSourceProvider;
    CityObjectRepository cityObjectRepository;

    LocalTime sW = LocalTime.of(10, 0);
    LocalTime eW = LocalTime.of(22, 0);
    CityObject cinema = new CityObject(
            1, "Leisure", "Cinema", "Description1", Time.valueOf(sW), Time.valueOf(eW));
    CityObject cityHall = new CityObject(
            2, "Municipal", "City hall", "Description2", Time.valueOf(sW), Time.valueOf(eW));
    CityObject test = new CityObject(
            3, "Leisure", "Test", "description", Time.valueOf(sW), Time.valueOf(eW));

    @BeforeEach
    public void setUp() {
        try {
            dataSourceProvider = new DataSourceProvider();
            cityObjectRepository = new CityObjectRepository(dataSourceProvider.getDataSource());
            cityObjectRepository.dropTable();
            cityObjectRepository = new CityObjectRepository(dataSourceProvider.getDataSource());
            cityObjectRepository.createNew(cinema);
            cityObjectRepository.createNew(cityHall);
            cityObjectRepository.createNew(test);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест на поиск всех заведений в БД.
     */
    @Test
    public void findAll() {
        List<CityObject> cityObjects = new ArrayList<>();
        List<CityObject> expectedCityObject;

        cityObjects.add(cinema);
        cityObjects.add(cityHall);
        cityObjects.add(test);

        expectedCityObject = cityObjectRepository.findAll();
        Assertions.assertEquals(expectedCityObject, cityObjects);
    }

    /**
     * Тест на изменение названия заведения.
     */
    @Test
    public void updateTable() {
        List<CityObject> cityObjects = new ArrayList<>();
        List<CityObject> expectedCityObject;
        CityObject test = new CityObject(
                3, "Leisure", "bowling", "description", Time.valueOf(sW), Time.valueOf(eW));
        cityObjects.add(test);

        try {
            expectedCityObject = cityObjectRepository.updateTable("title", "bowling", false, 3);
            Assertions.assertEquals(expectedCityObject, cityObjects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест на удаление записи о заведении.
     */
    @Test
    public void deleteRecord() {
        List<CityObject> cityObjects = Arrays.asList(cinema, cityHall);
        try {
            cityObjectRepository.deleteRecord(test.getId());
            Assertions.assertEquals(cityObjectRepository.findAll(), cityObjects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест на создание нового заведения.
     */
    @Test
    public void createNew() {
        CityObject hospital = new CityObject(
                4, "Municipal", "Hospital", "description4", Time.valueOf(sW), Time.valueOf(eW));
        List<CityObject> hospitalList= new ArrayList<>();
        try {
            hospitalList.add(hospital);
            cityObjectRepository.createNew(hospital);
            Assertions.assertEquals(cityObjectRepository.selectObject(4), hospitalList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест на получение заведения из БД.
     */
    @Test
    public void selectObject() {
        List<CityObject> list = new ArrayList<>();
        List<CityObject> expectedList;
        list.add(test);

        expectedList = cityObjectRepository.selectObject(3);
        Assertions.assertEquals(expectedList, list);
    }

    /**
     * Тест на удаление таблицы.
     */
    @Test
    public void dropTable() {
        List<CityObject> list = new ArrayList<>();
        List<CityObject> expectedList;
        try {
            cityObjectRepository.dropTable();
            expectedList = cityObjectRepository.findAll();
            Assertions.assertEquals(expectedList, list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}