package ru.croc.javaschool.task3.model;

import ru.croc.javaschool.task3.classShells.RentManager;
import ru.croc.javaschool.task3.classShells.StatusRent;
import ru.croc.javaschool.task3.classShells.StatusRepair;
import ru.croc.javaschool.task3.classShells.Transport;

/**
 * Велосипед. (id 1 - 49)
 */
public class Bicycle extends Transport implements RentManager {

    private  StatusRent statusRent; // Статус аренды транспорта.

    private StatusRepair statusRepair; // Статус ремонта транспорта.

    /**
     * Установить название, имя, id, статус аренды транспорта.
     *
     * @param title - название.
     * @param name - имя.
     * @param id - номер.
     * @param statusRent - статус аренды.
     */
    public Bicycle(String title, String name, int id, StatusRent statusRent) {
        super(title, name, id);
        this.statusRent = statusRent;
    }

    /**
     * Получить информацию о транспорте.
     *
     * @return строку.
     */
    @Override
    public String getFullName() {
        return "Информация о транспорте: " + toString(this.getTitle() + " " + this.getName() +
                "(" + this.getId() + ") " + statusRent);
    }
    private String toString(String s) {
        return s;
    }

    /**
     * Сдать в аренду велосипед.
     */
    @Override
    public void rentOut() {
        if (statusRent == StatusRent.REMOVERENT) {
            System.out.println("Невозможно арендовать - велосипед нуждается в ремонте!");
        } else if (statusRent == StatusRent.ONSTOCK) {
            statusRent = StatusRent.RENT;
            System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() +
                    ") " + this.statusRent.getTitle());
        } else {
            System.out.println("Транспорт уже арендован!");
        }
    }

    /**
     * Снять с аренды велосипед.
     */
    @Override
    public void removeFromRent() {
        statusRent = StatusRent.REMOVERENT;
        System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() +
                ") " + this.statusRent.getTitle());
        statusRepair = StatusRepair.BAD;
        System.out.println(this.statusRepair.getTitle());
    }

    /**
     * Починить велосипед.
     */
    @Override
    public void fix() {
        System.out.println("Накачиваем колеса велосипеда " + this.getName() + "("+ this.getId() +
                ")..." + "\nТранспор готов к аренде!");
        statusRepair = StatusRepair.GOOD;
        statusRent = StatusRent.ONSTOCK;
        System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() +
                ") " + statusRepair.getTitle());
    }
}
