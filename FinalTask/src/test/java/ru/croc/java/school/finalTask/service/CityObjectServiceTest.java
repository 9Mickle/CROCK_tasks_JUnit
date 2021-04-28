package ru.croc.java.school.finalTask.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.java.school.finalTask.db.dbprovider.DataSourceProvider;
import ru.croc.java.school.finalTask.db.entity.CityObject;
import ru.croc.java.school.finalTask.db.repository.CityObjectRepository;
import ru.croc.java.school.finalTask.db.service.CityObjectService;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Тесты класса CityObjectService.
 */
class CityObjectServiceTest {

    DataSourceProvider dataSourceProvider;
    CityObjectRepository cityObjectRepository;
    CityObjectService cityObjectService;

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
            cityObjectService = new CityObjectService(cityObjectRepository);
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
    public void getAll() {
        List<CityObject> cityObjects = new ArrayList<>();
        List<CityObject> expectedCityObject;

        cityObjects.add(cinema);
        cityObjects.add(cityHall);
        cityObjects.add(test);

        expectedCityObject = cityObjectService.getAll();
        Assertions.assertEquals(expectedCityObject, cityObjects);
    }

    /**
     * Тест на изменение названия заведения.
     */
    @Test
    public void update() {
        List<CityObject> cityObjects = new ArrayList<>();
        List<CityObject> expectedCityObject;
        CityObject test = new CityObject(
                3, "Leisure", "bowling", "description", Time.valueOf(sW), Time.valueOf(eW));
        cityObjects.add(test);

        try {
            expectedCityObject = cityObjectService.update("title", "bowling", 3);
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
            cityObjectService.delete(test.getId());
            Assertions.assertEquals(cityObjectService.getAll(), cityObjects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест на создание нового заведения.
     */
    @Test
    public void create() {
        CityObject hospital = new CityObject(
                4, "Municipal", "Hospital", "description4", Time.valueOf(sW), Time.valueOf(eW));
        List<CityObject> hospitalList= new ArrayList<>();
        try {
            hospitalList.add(hospital);
            cityObjectService.create(hospital);
            Assertions.assertEquals(cityObjectService.select(4), hospitalList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест на получение заведения из БД.
     */
    @Test
    public void select() {
        List<CityObject> list = new ArrayList<>();
        List<CityObject> expectedList;
        list.add(test);

        expectedList = cityObjectService.select(3);
        Assertions.assertEquals(expectedList, list);
    }

    /**
     * Тест на удаление таблицы.
     */
    @Test
    public void drop() {
        List<CityObject> list = new ArrayList<>();
        List<CityObject> expectedList;
        try {
            cityObjectService.drop();
            expectedList = cityObjectService.getAll();
            Assertions.assertEquals(expectedList, list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getLeisureObjectFromXml() {
        CityObject theatre = new CityObject(
                null, "Leisure", "Theatre", "Description1", Time.valueOf(sW), Time.valueOf(eW));
        CityObject shop = new CityObject(
                null, "Leisure", "Shopping centre", "Description2", Time.valueOf(sW), Time.valueOf(eW));

        List<CityObject> expectedList;
        List<CityObject> cityObjects = Arrays.asList(theatre, shop);
        try {
            expectedList = cityObjectService.getLeisureObjectFromXml();
            Assertions.assertEquals(expectedList, cityObjects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMunicipalObjectFromXml() {
        CityObject police = new CityObject(
                null, "Municipal", "Police", "Description1", Time.valueOf(sW), Time.valueOf(eW));
        CityObject fireStation = new CityObject(
                null, "Municipal", "FireStation", "Description2", Time.valueOf(sW), Time.valueOf(eW));

        List<CityObject> expectedList;
        List<CityObject> cityObjects = Arrays.asList(police, fireStation);
        try {
            expectedList = cityObjectService.getMunicipalObjectFromXml();
            Assertions.assertEquals(expectedList, cityObjects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}