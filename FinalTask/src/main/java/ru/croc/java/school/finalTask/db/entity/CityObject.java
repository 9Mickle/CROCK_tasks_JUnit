package ru.croc.java.school.finalTask.db.entity;

import java.sql.Time;
import java.util.Objects;

/** Городской объект.*/
public class CityObject {

    /**
     * Идентификатор объекта.
     */
    private final Integer id;

    /**
     * Тип объекта.
     */
    private String type;

    /**
     * Название объекта.
     */
    private String title;

    /**
     * Описание объекта.
     */
    private String description;

    /**
     * Время начала рабочего дня объекта.
     */
    private Time startWork;

    /**
     * Время конца рабочего дня объекта.
     */
    private Time endWork;

    /**
     * Конструктор городского объекта.
     *
     * @param id Уникальный идентификатор.
     * @param type Тип объекта.
     * @param title Название объекта.
     * @param description Описание объекта.
     * @param startWork Время открытия.
     * @param endWork Время закрытия.
     */
    public CityObject(Integer id, String type, String title, String description, Time startWork, Time endWork) {
        this.id = id;
        if (type.equals(TypeObject.LEISURE.getType())) {
            this.type = TypeObject.LEISURE.getType();
        } else if (type.equals(TypeObject.MUNICIPAL.getType())) {
            this.type = TypeObject.MUNICIPAL.getType();
        }
        this.title = title;
        this.description = description;
        this.startWork = startWork;
        this.endWork = endWork;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getStartWork() {
        return startWork;
    }

    public void setStartWork(Time startWork) {
        this.startWork = startWork;
    }

    public Time getEndWork() {
        return endWork;
    }

    public void setEndWork(Time endWork) {
        this.endWork = endWork;
    }

    @Override
    public String toString() {
        return "CityObject{" +
                " \tid=" + id +
                " \ttype='" + type + '\'' +
                " \ttitle='" + title + '\'' +
                " \tdescription='" + description + '\'' +
                " \tstartWork=" + startWork +
                " \tendWork=" + endWork +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityObject that = (CityObject) o;
        return Objects.equals(id, that.id);
    }
}