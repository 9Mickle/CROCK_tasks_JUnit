package ru.croc.java.school.finalTask.jaxb.in;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Муниципальный объект города.*/
@XmlRootElement()
public class MunicipalCityObject {

    @XmlElement(name = "Object")
    private List<MunicipalObject> municipalObjects = new ArrayList<>();

    public MunicipalCityObject() {
    }

    public MunicipalCityObject(List<MunicipalObject> municipalObjects) {
        this.municipalObjects = municipalObjects;
    }

    public List<MunicipalObject> getMunicipalObjects() {
        return municipalObjects;
    }

    public void setMunicipalObjects(List<MunicipalObject> municipalObjects) {
        this.municipalObjects = municipalObjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MunicipalCityObject that = (MunicipalCityObject) o;
        return Objects.equals(municipalObjects, that.municipalObjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(municipalObjects);
    }
}