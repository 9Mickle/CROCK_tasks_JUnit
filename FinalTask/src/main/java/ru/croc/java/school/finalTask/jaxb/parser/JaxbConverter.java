package ru.croc.java.school.finalTask.jaxb.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Конвертер.
 */
public class JaxbConverter {

    /**
     * Метод сериализации объекта в xml.
     *
     * @param data объект
     * @return xml
     */
    public String toXml(Object data) throws JsonProcessingException {
        return createXmlMapper().writeValueAsString(data);
    }

    /**
     * Метод десериализации объекта из xml.
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
     * Метод создания настроенного mapper JAXB.
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
     * Метод запсии двух файлов xml в строку.
     *
     * @return Мапу с двумя объектами в виде строки.
     */
    public Map<String, String> recordFileToString() {

        /**
         * Имя xml файла с досуговыми объектами города.
         */
        final String path1 = "leisure.xml";

        /**
         * Имя xml файла с муниципальными объектами города.
         */
        final String path2 = "municipal.xml";

        try {
            Map<String, String> listObject = new HashMap<>();
            listObject.put("leisure", new String(Files.readAllBytes(Paths.get(path1))));
            listObject.put("municipal", new String(Files.readAllBytes(Paths.get(path2))));
            return listObject;
        } catch (IOException e) {
            System.err.println("Не удалось найти файл");
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}