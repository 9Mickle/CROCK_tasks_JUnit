package ru.croc.java.school.finalTask.jaxb;

import org.junit.jupiter.api.*;
import ru.croc.java.school.finalTask.jaxb.in.LeisureCityObject;
import ru.croc.java.school.finalTask.jaxb.in.LeisureObject;
import ru.croc.java.school.finalTask.jaxb.in.MunicipalObject;
import ru.croc.java.school.finalTask.jaxb.in.MunicipalCityObject;
import ru.croc.java.school.finalTask.jaxb.parser.JaxbConverter;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Тесты класса JaxbConverter.
 */
class JaxbConverterTest {

    /**
     * Тест на получение муниципальных объектов из xml.
     */
    @Test
    public void jaxbConverterMunicipal() throws IOException {
        String xml = "<MunicipalCityObject>\n" +
                "  <Object>\n" +
                "    <title>Hospital</title>\n" +
                "    <description>Here you will be cured</description>\n" +
                "    <startTime>08:00</startTime>\n" +
                "    <endTime>23:00</endTime>\n" +
                "  </Object>\n" +
                "  <Object>\n" +
                "    <title>City hall</title>\n" +
                "    <description>Here you can discuss important issues</description>\n" +
                "    <startTime>08:00</startTime>\n" +
                "    <endTime>17:00</endTime>\n" +
                "  </Object>\n" +
                "</MunicipalCityObject>";

        final MunicipalCityObject municipalCityObjects = new MunicipalCityObject();

        List<MunicipalObject> municipalObjects = new ArrayList<>();
        municipalObjects.add(new MunicipalObject(
                "Hospital", "Here you will be cured", LocalTime.of(8, 0), LocalTime.of(23, 0)));
        municipalObjects.add(new MunicipalObject(
                "City hall", "Here you can discuss important issues", LocalTime.of(8, 0),LocalTime.of(17, 0)));
        municipalCityObjects.setMunicipalObjects(municipalObjects);

        final JaxbConverter converter = new JaxbConverter();
        System.out.println(xml);

        Assertions.assertEquals(municipalCityObjects, converter.fromXml(xml, MunicipalCityObject.class));
    }

    /**
     * Тест на получение досуговых объектов из xml.
     */
    @Test
    public void jaxbConverterLeisure() throws IOException {
        String xml = "<LeisureCityObject>\n" +
                "    <Object>\n" +
                "        <title>Cinema</title>\n" +
                "        <description>Here you can watch movies</description>\n" +
                "        <startTime>10:00</startTime>\n" +
                "        <endTime>22:00</endTime>\n" +
                "    </Object>\n" +
                "    <Object>\n" +
                "        <title>Bowling</title>\n" +
                "        <description>Here you can play bowling and have a drink</description>\n" +
                "        <startTime>12:00</startTime>\n" +
                "        <endTime>03:00</endTime>\n" +
                "    </Object>\n" +
                "</LeisureCityObject>";
        final LeisureCityObject leisureCityObjects = new LeisureCityObject();

        List<LeisureObject> leisureObjects = new ArrayList<>();
        leisureObjects.add(new LeisureObject(
                "Cinema", "Here you can watch movies", LocalTime.of(10, 0), LocalTime.of(22, 0)));
        leisureObjects.add(new LeisureObject(
                "Bowling", "Here you can play bowling and have a drink", LocalTime.of(12, 0),LocalTime.of(3, 0)));
        leisureCityObjects.setLeisureObjects(leisureObjects);

        final JaxbConverter converter = new JaxbConverter();
        System.out.println(xml);

        Assertions.assertEquals(leisureCityObjects, converter.fromXml(xml, LeisureCityObject.class));
    }
}