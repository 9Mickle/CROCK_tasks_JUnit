package ru.croc.java.school.task4;

/**
 * Вершина графа.
 *
 * @param <T> неизвестный тип 'значение' данных вершины.
 */
public class Vertex<T> {

    /**
     * Неизвестный тип 'значение' данных вершины.
     */
    private T data;

    /**
     * Номер вершины.
     */
    private int number;

    public Vertex(int number, T data) {
        this.data = data;
        this.number = number;
    }
}
