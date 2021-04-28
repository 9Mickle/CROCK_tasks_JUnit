package ru.croc.java.school.finalTask;

import org.junit.jupiter.api.BeforeEach;
import ru.croc.java.school.finalTask.db.dbprovider.DataSourceProvider;
import ru.croc.java.school.finalTask.db.repository.CityObjectRepository;
import ru.croc.java.school.finalTask.db.service.CityObjectService;
import ru.croc.java.school.finalTask.jaxb.parser.JaxbConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class TestScenario {

    JaxbConverter converter = new JaxbConverter();
    DataSourceProvider dataSourceProvider;
    CityObjectRepository cityObjectRepository;
    CityObjectService cityObjectService;

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

    public void testScenario() {
        System.out.println("Входные данные: ");
        Map<String, String> inputData;
        inputData = converter.recordFileToString();
        System.out.println(inputData);
    }
}
