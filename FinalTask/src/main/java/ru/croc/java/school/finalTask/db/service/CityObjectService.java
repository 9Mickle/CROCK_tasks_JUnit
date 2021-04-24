package ru.croc.java.school.finalTask.db.service;

import ru.croc.java.school.finalTask.db.entity.CityObject;
import ru.croc.java.school.finalTask.db.repository.CityObjectRepository;
import ru.croc.java.school.finalTask.jaxb.in.LeisureCityObject;
import ru.croc.java.school.finalTask.jaxb.in.LeisureObject;
import ru.croc.java.school.finalTask.jaxb.in.MunicipalCityObject;
import ru.croc.java.school.finalTask.jaxb.in.MunicipalObject;
import ru.croc.java.school.finalTask.jaxb.parser.JaxbConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис городских объектов.
 */
public class CityObjectService {

    /**
     * Репозиторий для работы с городскими объектами.
     */
    private final CityObjectRepository cityObjectRepository;

    public CityObjectService(CityObjectRepository cityObjectRepository) {
        this.cityObjectRepository = cityObjectRepository;
    }

    public List<CityObject> getAll() {
        return cityObjectRepository.findAll();
    }

    public CityObject create(CityObject leisureObject) throws SQLException {
        cityObjectRepository.createNew(leisureObject);
        return leisureObject;
    }

    public void delete(Integer id) throws SQLException {
        cityObjectRepository.deleteRecord(id);
    }

    public List<CityObject> update(String columnTitle, String result, Integer id) throws SQLException {
        Boolean check = checkString(result); // Записываем результат проверки на число и отдаем в метод updateTable.
        return cityObjectRepository.updateTable(columnTitle, result, check, id);
    }

    public List<CityObject> select(Integer id) {
        return cityObjectRepository.selectObject(id);
    }

    public void drop() throws SQLException {
        cityObjectRepository.dropTable();
    }

    /**
     * Метод проверки строки, является ли она числом.
     * Необходим для корректного формирования SQL запроса в классе CityObjectRepository.
     *
     * @param someString строка.
     * @return true - если строка является числом, false - если строка не является числом типа Integer.
     */
    public Boolean checkString(String someString) {
        try {
            Integer.parseInt(someString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Запись объектов двух типов в БД.
     */
    public void addAllObject() throws IOException, SQLException {
        // Муниципальные.
        for (int i = 0; i < getMunicipalObjectFromXml().size(); i++) {
            create(getMunicipalObjectFromXml().get(i));
        }
        // Досуговые.
        for (int i = 0; i < getLeisureObjectFromXml().size(); i++) {
            create(getLeisureObjectFromXml().get(i));
        }
    }

    /**
     * Метод получения данных о досуговом объекте из xml.
     *
     * @return список досуговых объектов.
     */
    public List<CityObject> getLeisureObjectFromXml() throws IOException {
        String typeObject = "Leisure"; // Стандартный тип для этого объекта.
        JaxbConverter converter = new JaxbConverter();
        LeisureCityObject leisureCityObject;
        String inXml = converter.giveXmlLeisure();

        leisureCityObject = converter.fromXml(inXml, LeisureCityObject.class);
        List<CityObject> cityObjects = new ArrayList<>();

        // Запись полей всех объектов leisure.
        for (LeisureObject leisureObject : leisureCityObject.getLeisureObjects()) {
            cityObjects.add(
                    new CityObject(
                            null,
                            typeObject,
                            leisureObject.getTitle(),
                            leisureObject.getDescription(),
                            Time.valueOf(leisureObject.getStartTime()),
                            Time.valueOf(leisureObject.getEndTime())));
        }
        return cityObjects;
    }

    /**
     * Метод получения данных о муниципальном объекте из xml.
     *
     * @return список муниципальных объектов.
     */
    public List<CityObject> getMunicipalObjectFromXml() throws IOException {
        String typeObject = "Municipal"; // Стандартный тип для этого объекта.
        JaxbConverter converter = new JaxbConverter();
        MunicipalCityObject municipalCityObject;
        String inXml = converter.giveXmlMunicipal();

        municipalCityObject = converter.fromXml(inXml, MunicipalCityObject.class);
        List<CityObject> cityObjects = new ArrayList<>();

        // Запись полей всех объектов municipal.
        for (MunicipalObject municipalObject : municipalCityObject.getMunicipalObjects()) {
            cityObjects.add(
                    new CityObject(
                            null,
                            typeObject,
                            municipalObject.getTitle(),
                            municipalObject.getDescription(),
                            Time.valueOf(municipalObject.getStartTime()),
                            Time.valueOf(municipalObject.getEndTime())));
        }
        return cityObjects;
    }
}