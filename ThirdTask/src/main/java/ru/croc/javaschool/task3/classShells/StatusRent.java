package ru.croc.javaschool.task3.classShells;

/**
 * Список - статус аренды.
 */
public enum StatusRent {

    RENT ("арендован."),
    REMOVERENT ("снят с аренды."),
    ONSTOCK ("на складе.");

    private String title;

    StatusRent(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
я