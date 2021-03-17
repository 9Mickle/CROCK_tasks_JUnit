package ru.croc.javaschool.task3.model;

import ru.croc.javaschool.task3.classShells.*;

/**
 * Самокат.(id 50 - 99)
 */
public class Scooter extends Transport implements RentManager {

    private  StatusRent statusRent; // Статус аренды

    private StatusRepair statusRepair; // Статус ремонта

    private Engine typeEngine; // Тип двигателя

    /**
     * Установить название, имя, id, статус аренды транспорта.
     *
     * @param title - название.
     * @param name - имя.
     * @param id - номер.
     * @param statusRent - статус аренды.
     */
    public Scooter(String title, String name, int id, StatusRent statusRent, StatusRepair statusRepair) {
        super(title, name, id);
        this.statusRent = statusRent;
        this.statusRepair = statusRepair;
    }

    /**
     * Установить значение + двигатель
     *
     * @param title - название.
     * @param name - имя.
     * @param id - номер.
     * @param statusRent - статус аренды.
     * @param statusRepair - статус ремонта.
     * @param typeEngine - двигатель.
     */
    public Scooter(String title, String name, int id, StatusRent statusRent, StatusRepair statusRepair, Engine typeEngine) {
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
        if (statusRent == StatusRent.REMOVERENT) {
            if (typeEngine == typeEngine.ELECTRIC) {
                System.out.println("Заряжаем двигатель...");
            } else {
                System.out.println("Смазываем подшипники самоката " + this.getName() + "(" + this.getId() + ")..." + "\nТранспор готов к аренде!");
            }
            statusRepair = StatusRepair.GOOD;
            statusRent = StatusRent.ONSTOCK;
            System.out.println(this.getTitle() + " " + this.getName() + "(" + this.getId() + ") " + statusRepair.getTitle());

        } else if (statusRent == StatusRent.RENT){
            System.out.println("Этот самокат находится в аренде!");
        } else {
            System.out.println("Этот самокат починен и стоит на парковке.");
        }
    }

    /**
     * Проверка состояния транспорта.
     */
    @Override
    public void checkRepair() {
        System.out.println("Состояние самоката: " + statusRepair);
    }

    /**
     * Информация об аренде транспорта
     */
    @Override
    public void checkRent() {
        System.out.println("Информация об аренде самоката: " + statusRent);
    }
}
