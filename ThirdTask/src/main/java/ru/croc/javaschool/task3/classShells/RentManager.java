package ru.croc.javaschool.task3.classShells;

public interface RentManager {

    /**
     * Сдать транспорт в аренду.
     */
    void  rentOut();

    /**
     * Убрать транспорт с аренды, будем считать, что после того как сняли транспорт с аренды -
     * его нужно обслужить/отремонтировать.
     */
    void removeFromRent();

    /**
     * Починить транспорт.
     */
    void fix();

    /**
     * Информация о состоянии транспорта.
     */
    void checkRepair();

    /**
     * Информация о статусе аренды транспорта.
     */
    void checkRent();

    /**
     * Отдать транспорт арендатору.
     */
    default void drive(){
        System.out.println("Арендатор уехал на арендованом транспорте в своём направлении...");
    }
}
