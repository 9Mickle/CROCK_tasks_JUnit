package ru.croc.javaschool.task3.model;

import ru.croc.javaschool.task3.classShells.RentManager;
import ru.croc.javaschool.task3.classShells.StatusRent;
import ru.croc.javaschool.task3.classShells.StatusRepair;
import ru.croc.javaschool.task3.classShells.Transport;

/**
 * Велосипед. (id 1 - 49)
 */
public class Bicycle extends Transport implements RentManager {

    private  StatusRent statusRent; // Статус аренды

    private StatusRepair statusRepair; // Статус ремонта

    /**
     * Установить название, имя, id, статус аренды транспорта.
     *
     * @param title - название.
     * @param name - имя.
     * @param id - номер.
     * @param statusRent - статус аренды.
     * @param statusRepair - статус ремонта.
     */
    public Bicycle(String title, String name, int id, StatusRent statusRent, StatusRepair statusRepair) {
        super(title, name, id);
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
                "\n\tСостояние транспорта: "+ statusRepair);
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
            System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() + ") " + this.statusRent.getTitle());
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
        System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() + ") " + this.statusRent.getTitle());
        statusRepair = StatusRepair.BAD;
        System.out.println(this.statusRepair.getTitle());
    }

    /**
     * Починить велосипед.
     */
    @Override
    public void fix() {
        if (statusRent == StatusRent.REMOVERENT) {
            System.out.println("Накачиваем колеса велосипеда " + this.getName() + "("+ this.getId() + ")..." + "\nТранспор готов к аренде!");
            statusRepair = StatusRepair.GOOD;
            statusRent = StatusRent.ONSTOCK;
            System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() + ") " + statusRepair.getTitle());

        } else if (statusRent == StatusRent.RENT) {
            System.out.println("Этот велосипед находится в аренде!");
        } else {
            System.out.println("Этот велосипед починен и стоит на парковке.");
        }
    }

    /**
     * Проверка состояния транспорта.
     */
    @Override
    public void checkRepair() {
        System.out.println("Состояние велосипеда: " + statusRepair);
    }

    /**
     * Информация об аренде транспорта
     */
    @Override
    public void checkRent() {
        System.out.println("Информация об аренде велосипеда: " + statusRent);
    }
}
