package ru.croc.javaschool.task3.model;

import ru.croc.javaschool.task3.classShells.*;

/**
 * Самолет. (id 400 - 499)
 */
public class Plane extends Transport implements RentManager {

    private  StatusRent statusRent; // Статус аренды транспорта

    private StatusRepair statusRepair; // Статус ремонта транспорта

    /**
     * Установить название, имя, id, статус аренды транспорта.
     *
     * @param title - название.
     * @param name - имя.
     * @param id - номер.
     * @param statusRent - статус аренды.
     */
    public Plane(String title, String name, int id, StatusRent statusRent) {
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
     * Сдать в аренду самолёт.
     */
    @Override
    public void rentOut() {
        if (statusRent == StatusRent.REMOVERENT) {
            System.out.println("Невозможно арендовать - самолет нуждается в ремонте!");
        } else if (statusRent == StatusRent.ONSTOCK) {
            statusRent = StatusRent.RENT;
            System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() +
                    ") " + this.statusRent.getTitle());
        } else {
            System.out.println("Транспорт уже арендован!");
        }
    }

    /**
     * Снять с арнеды самолёт.
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
     * Починить самолёт
     */
    @Override
    public void fix() {
        System.out.println("Проводится полная диагностика самолета " + this.getName() +
                "("+ this.getId() + ")..." + "\nДвигатели отремонтированы, самолет заправлен! Транспор готов к аренде!");
        statusRepair = StatusRepair.GOOD;
        statusRent = StatusRent.ONSTOCK;
        System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() +
                ") " + statusRepair.getTitle());
    }

    /**
     * Отдать самолет арендатору.
     */
    @Override
    public void drive() {
        System.out.println("Арнедатор улетел на самолёте в своем направлении...");
    }
}
