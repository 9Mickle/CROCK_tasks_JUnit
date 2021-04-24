package ru.croc.java.school.finalTask.jaxb.in;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Просто класс со списками городских объектов, которые будут переводится в Xml.
 */
public class InListObject {

    /**
     * Метод, возвращающий список досуговых объектов.
     *
     * @return лист с объектами.
     */
    public List<LeisureObject> addListLeisureObject() {
        List<LeisureObject> leisureObjects = new ArrayList<>();
        leisureObjects.add(new LeisureObject(
                "Cinema", "Here you can watch movies", LocalTime.of(10, 0), LocalTime.of(22, 0)));
        leisureObjects.add(new LeisureObject(
                "Bowling", "Here you can play bowling and have a drink", LocalTime.of(12, 0),LocalTime.of(3, 0)));
        return leisureObjects;
    }

    /**
     * Метод, возвращающий лист муниципальных объектов.
     *
     * @return лист с объектами.
     */
    public List<MunicipalObject> addListMunicipalObject() {
        List<MunicipalObject> municipalObjects = new ArrayList<>();
        municipalObjects.add(new MunicipalObject(
                "Hospital", "Here you will be cured", LocalTime.of(8, 0), LocalTime.of(23, 0)));
        municipalObjects.add(new MunicipalObject(
                "City hall", "Here you can discuss important issues", LocalTime.of(8, 0),LocalTime.of(17, 0)));
        return municipalObjects;
    }
}
