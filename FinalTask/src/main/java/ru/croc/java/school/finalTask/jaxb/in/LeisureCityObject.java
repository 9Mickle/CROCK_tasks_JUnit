package ru.croc.java.school.finalTask.jaxb.in;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Досуговый городской объект.*/
@XmlRootElement
public class LeisureCityObject {

    /**
     * Лист с досуговыми объектами.
     */
    @XmlElement(name = "Object")
    private List<LeisureObject> leisureObjects = new ArrayList<>();

    /**
     * Конструктор городского досугового объекта.
     */
    public LeisureCityObject() {
    }

    public LeisureCityObject(List<LeisureObject> leisureObjects) {
        this.leisureObjects = leisureObjects;
    }

    public List<LeisureObject> getLeisureObjects() {
        return leisureObjects;
    }

    public void setLeisureObjects(List<LeisureObject> leisureObjects) {
        this.leisureObjects = leisureObjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeisureCityObject that = (LeisureCityObject) o;
        return Objects.equals(leisureObjects, that.leisureObjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leisureObjects);
    }
}
