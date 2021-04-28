package ru.croc.java.school.finalTask.db.entity;

/**
 * Тип городского объекта.
 */
public enum TypeObject {

    MUNICIPAL("Municipal"), // Муниципальный объект.
    LEISURE("Leisure"); // Досуговый объект.

    private final String type;

    TypeObject(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}