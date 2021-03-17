package ru.croc.javaschool.task3.model;

import ru.croc.javaschool.task3.classShells.*;

/**
 * Автомобиль. (id 200 - 299)
 */
public class Auto extends Transport implements RentManager {

    private  StatusRent statusRent; // Статус аренды транспорта.

    private StatusRepair statusRepair; // Статус ремонта транспорта.

    private Engine typeEngine; // Тип двигателя транспорта.

    /**
     * Установить название, имя, id, тип двигателя, статус аренды транспорта.
     *
     * @param title - название.
     * @param name - имя.
     * @param id - номер.
     * @param typeEngine - тип двигателя.
     * @param statusRent - статус аренды.
     */
    public Auto(String title, String name, int id, Engine typeEngine, StatusRent statusRent) {
        super(title, name, id);
        this.typeEngine = typeEngine;
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
                "(" + this.getId() + ") " + statusRent + " " + typeEngine);
    }
    private String toString(String s) {
        return s;
    }

    /**
     * Сдать в аренду машину.
     */
    @Override
    public void rentOut() {
        if (statusRent == StatusRent.REMOVERENT) {
            System.out.println("Невозможно арендовать - машина нуждается в ремонте!");
        } else if (statusRent == StatusRent.ONSTOCK) {
            statusRent = StatusRent.RENT;
            System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() +
                    ") " + this.statusRent.getTitle());
        } else {
            System.out.println("Транспорт уже арендован!");
        }
    }

    /**
     * Снять с аренды машину.
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
     * Починить машину.
     */
    @Override
    public void fix() {
        if (typeEngine == typeEngine.ELECTRIC) {
            System.out.println("Заряжаем двигатель...");
        } else if (typeEngine == typeEngine.ISE) {
            System.out.println("Заправляем машину бензином...");
        }
        statusRepair = StatusRepair.GOOD;
        statusRent = StatusRent.ONSTOCK;
        System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() +
                ") " + statusRepair.getTitle());
    }
}