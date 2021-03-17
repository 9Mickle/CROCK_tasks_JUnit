package ru.croc.javaschool.task3.classShells;

/**
 * Список - статус ремонта.
 */
public enum StatusRepair {

    GOOD ("отремонтирован."),
    BAD ("Транспорт нуждается в ремонте!");

    private String title;

    StatusRepair(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
