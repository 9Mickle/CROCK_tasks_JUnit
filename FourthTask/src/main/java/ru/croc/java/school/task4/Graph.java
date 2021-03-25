package ru.croc.java.school.task4;

import java.util.*;

/**
 * Граф.
 *
 * @param <T> неизвестный тип данных.
 */
public class Graph<T> {

    /**
     * Список смежности графа.
     */
    HashMap<Vertex<T>, ArrayList<Vertex<T>>> graph = new HashMap<>();

    /**
     * Список компонент смежности графа.
     */
    ArrayList<ArrayList<Vertex<T>>> adjacencyComponents = new ArrayList<>();

    public Graph(HashMap<Vertex<T>, ArrayList<Vertex<T>>> graph) {
        this.graph = graph;
        this.adjacencyComponents = new ArrayList<>();
    }

    /**
     * Добавление вершины...
     *
     * @param vertex вершина.
     */
    public void addVertex(Vertex<T> vertex) {
        graph.put(vertex, new ArrayList<>());
        adjacencyComponents.add(new ArrayList<>(List.of(vertex)));
    }

    /**
     * Удаление вершины...
     *
     * @param vertex - вершина
     */
    public void deleteVertex(Vertex<T> vertex) {
        graph.get(vertex).remove(vertex); //?
    }

    /**
     * Добавление ребра...
     *
     * @param first  первая вершина.
     * @param second вторая вершина.
     */
    public void addEdge(Vertex<T> first, Vertex<T> second) {
        //Добавление двух смежных вершин.
        graph.get(first).add(second);
        graph.get(second).add(first);

    }

    /**
     * Удаление ребра...
     *
     * @param first  первая вершина.
     * @param second вторая вершина.
     */
    public void deleteEdge(Vertex<T> first, Vertex<T> second) {
        //Удаление двух вершин, соеденённых ребром.
        graph.get(first).remove(second);
        graph.get(second).remove(first);
    }

    /**
     * Получить количество компонент связности...
     */
    public void listAdjacencyComponents() {

    }

}

