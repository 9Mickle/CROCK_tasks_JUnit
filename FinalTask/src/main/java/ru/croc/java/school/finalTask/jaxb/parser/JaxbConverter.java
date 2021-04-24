package ru.croc.java.school.finalTask.jaxb.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import ru.croc.java.school.finalTask.jaxb.in.*;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class JaxbConverter {

    /**
     * Сериализует объект в xml.
     *
     * @param data объект
     * @return xml
     */
    public String toXml(Object data) throws JsonProcessingException {
        return createXmlMapper().writeValueAsString(data);
    }

    /**
     * Десериализация из xml.
     *
     * @param xml xml
     * @param type тип объекта
     * @param <T> тип
     * @return объект
     */
    public <T> T fromXml(String xml, Class<T> type) throws IOException {
        return createXmlMapper().readValue(xml, type);
    }

    /**
     * Создаём настроенный mapper JAXB.
     * @return mapper
     */
    private XmlMapper createXmlMapper() {
        final XmlMapper mapper = new XmlMapper();
        SimpleDateFormat df = new SimpleDateFormat("h:mm a");
        mapper.setDateFormat(df);
        mapper.setDefaultUseWrapper(false); // Установить значение по умолчанию.
        mapper.registerModule(new JaxbAnnotationModule()); // модуль jaxb
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // форматирование вывода
        return mapper;
    }

    /**
     * Метод конвертации досуговых объектов в xml.
     *
     * @return досуговые объекты в виде строки xml.
     */
    public String giveXmlLeisure() throws JsonProcessingException {
        final LeisureCityObject leisureCityObjects = new LeisureCityObject();
        InListObject inListObject = new InListObject();

        leisureCityObjects.setLeisureObjects(inListObject.addListLeisureObject()); // Добавил список досуговых объектов.
        final JaxbConverter converter = new JaxbConverter();
        return converter.toXml(leisureCityObjects); // Возвращает строку.
    }

    /**
     * Метод конвертации муниципальных объектов в xml.
     *
     * @return муниципальные объекты в виде строки xml.
     */
    public String giveXmlMunicipal() throws JsonProcessingException {
        final MunicipalCityObject municipalCityObjects = new MunicipalCityObject();
        InListObject inListObject = new InListObject();

        municipalCityObjects.setMunicipalObjects(inListObject.addListMunicipalObject()); // Добавил список муниципальных объектов.
        final JaxbConverter converter = new JaxbConverter();
        return converter.toXml(municipalCityObjects); // Возвращает строку.
    }
}