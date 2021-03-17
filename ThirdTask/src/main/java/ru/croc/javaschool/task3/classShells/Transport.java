package ru.croc.javaschool.task3.classShells;

/**
 * Родительский класс транспорт.
 */
public abstract class Transport {

    private String name; // Имя транспорта.

    private int id; // Номер транспорта.

    private String title; // Название транспорта.

    /**
     * Установить название, имя, номер транспорта.
     *
     * @param title - название.
     * @param name - имя.
     * @param id - номер.
     */
    public Transport(String title, String name, int id) {
        this.title = title;
        this.name = name;
        this.id = id;
    }

    /**
     * Получить информацию о транспорте.
     *
     * @return строку
     */
    public abstract String getFullName();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
}


