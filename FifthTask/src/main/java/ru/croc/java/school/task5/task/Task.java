package ru.croc.java.school.task5.task;

import java.io.Serializable;

/**
 * Задача.
 */
public class Task implements Serializable {

    /** Идентификатор. */
    private final String id;

    /** Наименование. */
    private String title;

    /** Описание. */
    private String description;

    /** Исполнитель. */
    private String executor;

    /** Статус. */
    private String statusTask;

    /**
     * Установить идентификатор, наименование, описание, исполнителя задачи.
     *
     * @param id идентификатор.
     * @param title наименование.
     * @param description описание.
     * @param executor исполнитель.
     * @param statusTask статус выполения.
     */
    public Task(String id, String title, String description, String executor, String statusTask) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.executor = executor;
        this.statusTask = statusTask;
    }


    public String getId() {
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

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(String statusTask) {
        this.statusTask = statusTask;
    }

    @Override
    public String toString() {
        return "Задача{" +
                "Код='" + id + '\'' +
                ", Наименование='" + title + '\'' +
                ", Описание='" + description + '\'' +
                ", Исполнитель='" + executor + '\'' +
                ", Статус=" + statusTask +
                '}';
    }
}
