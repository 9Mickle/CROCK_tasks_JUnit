package ru.croc.javaschool.task3.classShells;

public interface Drive {

    /**
     * Отдать транспорт арендатору.
     */
    default void drive() {
        System.out.println("Арендатор уехал на арендованном транспорте в своем направлении...");
    }
}
