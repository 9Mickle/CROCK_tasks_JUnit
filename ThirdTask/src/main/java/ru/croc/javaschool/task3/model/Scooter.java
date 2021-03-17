package ru.croc.javaschool.task3.model;

import ru.croc.javaschool.task3.classShells.*;

/**
 * Самокат.(id 50 - 99)
 */
public class Scooter extends Transport implements RentManager {

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
    public Scooter(String title, String name, int id, StatusRent statusRent) {
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
     * Сдать в аренду самокат.
     */
    @Override
    public void rentOut() {
        if (statusRent == StatusRent.REMOVERENT) {
            System.out.println("Невозможно арендовать - самокат нуждается в ремонте!");
        } else if (statusRent == StatusRent.ONSTOCK) {
            statusRent = StatusRent.RENT;
            System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() +
                    ") " + this.statusRent.getTitle());
        } else {
            System.out.println("Транспорт уже арендован!");
        }
    }

    /**
     * Снять с аренды самокат.
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
     * Починить самокат.
     */
    @Override
    public void fix() {
        System.out.println("Смазываем подшипники самоката " + this.getName() +
                "("+ this.getId() + ")..." + "\nТранспор готов к аренде!");
        statusRepair = StatusRepair.GOOD;
        statusRent = StatusRent.ONSTOCK;
        System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() +
                ") " + statusRepair.getTitle());
    }
}
