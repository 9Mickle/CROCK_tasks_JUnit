package ru.croc.javaschool.task3.model;

import ru.croc.javaschool.task3.classShells.*;

/**
 * Автомобиль. (id 200 - 299)
 */
public class Auto extends Transport implements RentManager {

    private  StatusRent statusRent; // Статус аренды

    private StatusRepair statusRepair; // Статус ремонта

    private Engine typeEngine; // Тип двигателя

    /**
     * Установить название, имя, id, тип двигателя, статус аренды транспорта.
     *
     * @param title - название.
     * @param name - имя.
     * @param id - номер.
     * @param typeEngine - тип двигателя.
     * @param statusRent - статус аренды.
     * @param statusRepair - статус ремонта.
     */
    public Auto(String title, String name, int id, Engine typeEngine, StatusRent statusRent, StatusRepair statusRepair) {
        super(title, name, id);
        this.typeEngine = typeEngine;
        this.statusRent = statusRent;
        this.statusRepair = statusRepair;
    }

    /**
     * Получить информацию о транспорте.
     *
     * @return строку.
     */
    @Override
    public String getFullName() {
        return "Информация о транспорте: " + toString("\n\tНазвание: " + this.getTitle() +
                "\n\tФирма: " + this.getName() +
                "\n\tНомер: " + this.getId() +
                "\n\tСтатус арнеды: " + statusRent +
                "\n\tСостояние транспорта: "+ statusRepair +
                "\n\tТип двигателя: " + typeEngine);
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
            System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() + ") " + this.statusRent.getTitle());
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
        System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() + ") " + this.statusRent.getTitle());
        statusRepair = StatusRepair.BAD;
        System.out.println(this.statusRepair.getTitle());
    }

    /**
     * Починить машину.
     */
    @Override
    public void fix() {
        if (statusRent == StatusRent.REMOVERENT) {
            if (typeEngine == typeEngine.ELECTRIC) {
                System.out.println("Заряжаем двигатель...");
            } else if (typeEngine == typeEngine.ISE) {
                System.out.println("Заправляем машину бензином...");
            }
            statusRepair = StatusRepair.GOOD;
            statusRent = StatusRent.ONSTOCK;
            System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() + ") " + statusRepair.getTitle());

        } else if (statusRent == StatusRent.RENT) {
            System.out.println("Эта машина находится в аренде!");
        } else {
            System.out.println("Эта машина починена и стоит на парковке.");
        }
    }

    /**
     * Проверка состояния транспорта.
     */
    @Override
    public void checkRepair() {
        System.out.println("Состояние машины: " + statusRepair);
    }

    /**
     * Информация об аренде транспорта
     */
    @Override
    public void checkRent() {
        System.out.println("Информация об аренде машины: " + statusRent);
    }
}
