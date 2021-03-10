package Zoo;


import Zoo.ComponentsZoo.*;

import java.util.Arrays;

/**
 * Точка входа в программу, тестовый пример
 */
public class Main {


    public static void main(String[] args) {


        Zoo zoo = new Zoo();

        Employee[] employees = new Employee[]{ //Сотрудники
                new Employee("Александр"),
                new Employee("Роман"),
                new Employee("Вадим"),
                new Employee("Алексей")
        };
        Enclosure[] enclosures = new Enclosure[]{ //Вольеры
                new Enclosure("№1"),
                new Enclosure("№2"),
                new Enclosure("№3"),
                new Enclosure("№4")
        };
        Animal[] animals = new Animal[]{ //Животные
                new Animal("Лев"),
                new Animal("Волк"),
                new Animal("Черепаха"),
                new Animal("Слон"),
        };

        for (Animal animal : animals) { //Добавление животных в зоопарк
            zoo.addAnimal(animal);
        }
        for (int i = 0; i < employees.length; i++) { //Добавление сотрудников и вольеров
            zoo.addStaff(employees[i],enclosures[i]);
        }

        zoo.info(); //Информация о зоопарке

        enclosures[0].setCleanStatus(true);
        zoo.clean(0);
        if (enclosures[0].isCleanStatus()) {
            System.out.println("\nВольер " + enclosures[0] + " грязный");
        } else {
            System.out.println("Вольер " + enclosures[0] + " чистый");
        }

        animals[1].setFeedStatus(true);
        zoo.feed(1);
        if (animals[1].isFeedStatus()) {
            System.out.println("\nЖивотное " + animals[1] + " голодное");
        } else {
            System.out.println("Животное " + animals[1] + " покормлено");
        }

        animals[2].setDiseaseStatus(true);
        zoo.disease(2);

        zoo.swapElem(2, employees[2], enclosures[2]);
        System.out.println();
        zoo.info();
    }
}
