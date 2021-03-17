package ru.croc.javaschool.task3.model;

import ru.croc.javaschool.task3.classShells.*;

/**
 * Самолет. (id 400 - 499)
 */
public class Plane extends Transport implements RentManager {

    private  StatusRent statusRent; // Статус аренды

    private StatusRepair statusRepair; // Статус ремонта

    private Engine typeEngine; //Тип двигателя

    /**
     * Установить название, имя, id, статус аренды транспорта.
     *
     * @param title - название.
     * @param name - имя.
     * @param id - номер.
     * @param statusRent - статус аренды.
     * @param statusRepair - статус ремонта.
     * @param typeEngine - тип двигателя
     */
    public Plane(String title, String name, int id, StatusRent statusRent, StatusRepair statusRepair, Engine typeEngine) {
        super(title, name, id);
        this.statusRent = statusRent;
        this.statusRepair = statusRepair;
        this.typeEngine = typeEngine;
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
     * Сдать в аренду самолёт.
     */
    @Override
    public void rentOut() {
        if (statusRent == StatusRent.REMOVERENT) {
            System.out.println("Невозможно арендовать - самолет нуждается в ремонте!");
        } else if (statusRent == StatusRent.ONSTOCK) {
            statusRent = StatusRent.RENT;
            System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() + ") " + this.statusRent.getTitle());
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
        System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() + ") " + this.statusRent.getTitle());
        statusRepair = StatusRepair.BAD;
        System.out.println(this.statusRepair.getTitle());
    }

    /**
     * Починить самолёт
     */
    @Override
    public void fix() {
        if (statusRent == StatusRent.REMOVERENT) {
            System.out.println("Проводится полная диагностика самолета " + this.getName() + "(" + this.getId() + ")..." + "\nДвигатели отремонтированы, самолет заправлен! Транспор готов к аренде!");
            statusRepair = StatusRepair.GOOD;
            statusRent = StatusRent.ONSTOCK;
            System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() + ") " + statusRepair.getTitle());

        } else if (statusRent == StatusRent.RENT) {
            System.out.println("Этот самолёт находится в аренде!");
        } else {
            System.out.println("Этот самолёт починен и стоит на парковке.");
        }
    }

    /**
     * Проверка состояния транспорта.
     */
    @Override
    public void checkRepair() {
        System.out.println("Состояние самолёта: " + statusRepair);
    }

    /**
     * Информация об аренде транспорта
     */
    @Override
    public void checkRent() {
        System.out.println("Информация об аренде самолёта: " + statusRent);
    }

    /**
     * Переопределнный дефолтный метод, т.к. самолет летает, а не ездит.
     */
    @Override
    public void drive() {
        System.out.println("Арнедатор улетел на самолёте в своем направлении...");
    }
}
