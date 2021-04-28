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

    /**
     * Конструктор сервиса.
     *
     * @param cityObjectRepository Репозиторий.
     */
    public CityObjectService(CityObjectRepository cityObjectRepository) {
        this.cityObjectRepository = cityObjectRepository;
    }

    /**
     * Метод получения всех записей об объектах из БД.
     *
     * @return Лист с объектами.
     */
    public List<CityObject> getAll() {
        return cityObjectRepository.findAll();
    }

    /**
     * Добавить новую запись об объекте в БД.
     *
     * @param cityObject Городской объект
     * @return Добавленный объект.
     */
    public CityObject create(CityObject cityObject) throws SQLException {
        cityObjectRepository.createNew(cityObject);
        return cityObject;
    }

    /**
     * Метод удаления записи об объекте из БД.
     *
     * @param id Идентификатор объекта.
     */
    public void delete(Integer id) throws SQLException {
        cityObjectRepository.deleteRecord(id);
    }

    /**
     * Метод обновления записи об объекте, изменив какую-либо информацию о нём.
     *
     * @param columnTitle Название столба, в котором будут происходить измененния.
     * @param result Значение, которое должно быть в таблице после изменения.
     * @param id Индетификатор объекта.
     *
     * @return Список изменений.
     */
    public List<CityObject> update(String columnTitle, String result, Integer id) throws SQLException {
        Boolean check = checkString(result); // Записываем результат проверки на число и отдаем в метод updateTable.
        return cityObjectRepository.updateTable(columnTitle, result, check, id);
    }

    /**
     * Метод получения объекта по его идентификатору.
     *
     * @param id Идентификатор объекта.
     * @return Список, в котором находится выбранный объект.
     */
    public List<CityObject> select(Integer id) {
        return cityObjectRepository.selectObject(id);
    }

    /**
     * Метод записи объектов двух типов в БД.
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
     * Метод удаления таблицы.
     */
    public void drop() throws SQLException {
        cityObjectRepository.dropTable();
    }

    /**
     * Метод получения данных о досуговом объекте из xml.
     * Нужен для метода addAllObject.
     *
     * @return Список досуговых объектов.
     */
    public List<CityObject> getLeisureObjectFromXml() throws IOException {

        JaxbConverter converter = new JaxbConverter();
        String inXml = converter.recordFileToString().get("leisure"); // Достал по ключу нужный xml файл.
        LeisureCityObject leisureCityObject;
        String typeObject = "Leisure"; // Стандартный тип для этого объекта.

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
     * Нужен для метода addAllObject.
     *
     * @return Список муниципальных объектов.
     */
    public List<CityObject> getMunicipalObjectFromXml() throws IOException {

        JaxbConverter converter = new JaxbConverter();
        String inXml = converter.recordFileToString().get("municipal"); // Достал по ключу нужный xml файл.
        MunicipalCityObject municipalCityObject;
        String typeObject = "Municipal"; // Стандартный тип для этого объекта.

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

    /**
     * Метод проверки строки, является ли она числом.
     * Необходим для корректного формирования SQL запроса в классе CityObjectRepository.
     *
     * @param someString Строка.
     * @return True - если строка является числом, false - если строка не является числом типа Integer.
     */
    public Boolean checkString(String someString) {
        try {
            Integer.parseInt(someString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}