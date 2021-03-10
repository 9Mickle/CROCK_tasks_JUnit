package Zoo.ComponentsZoo;

import Zoo.ComponentsZoo.Animal;
import Zoo.ComponentsZoo.Employee;
import Zoo.ComponentsZoo.Enclosure;

import java.util.Arrays;

/**
 * Зоопарк.
 */
public class Zoo {

    Animal[] animals = new Animal[] {}; //Животные
    Employee[] employees = new Employee[] {}; //Сотрудники
    Enclosure[] enclosures = new Enclosure[] {}; //Вольеры


    /**
     * Добавление животного.
     *
     * @param animal - животные.
     */
    public void addAnimal(Animal animal) {
        animals = Arrays.copyOf(animals, animals.length + 1);
        animals[animals.length - 1] = animal;
    }


    /**
     * Добавление сотрудников и вольеров.
     *
     * @param employee - сотрудники.
     * @param enclosure - вольеры.
     */
    public void addStaff(Employee employee, Enclosure enclosure) {
        employees = Arrays.copyOf(employees, employees.length + 1);
        employees[employees.length - 1] = employee;

        enclosures = Arrays.copyOf(enclosures, enclosures.length + 1);
        enclosures[enclosures.length - 1] = enclosure;
    }


    /**
     * Удаление животного.
     *
     * @param animal - животное.
     */
    private void remove(Animal animal) {
        final Animal[] newAnimals = new Animal[animals.length - 1];
        int index = 0;

        for (Animal currentAnimal : animals) {
            if (currentAnimal != animal) {
                newAnimals[index++] = currentAnimal;
            }
        }
        animals = newAnimals;
    }


    /**
     * Чистка вольера.
     *
     * @param index - индекс вольера.
     */
    public void clean(int index) {
        if (enclosures[index].isCleanStatus()) {
            System.out.println("\nВольер " + enclosures[index] + " - грязный");
            System.out.println("Сотрудник " + employees[index] + " почистил вольер " + enclosures[index]);
            enclosures[index].setCleanStatus(false);
        } else System.out.println("\nВольер " + enclosures[index] + " - чистый");
    }


    /**
     * Кормление животного.
     *
     * @param index - индекс животного.
     */
    public void feed(int index) {
        if (animals[index].isFeedStatus()) {
            System.out.println("\nЖивотное " + animals[index] + " - голодное");
            System.out.println("Сотрудник " + employees[index] + " покормил животное " + animals[index]);
            animals[index].setFeedStatus(false);
        } else System.out.println("\nЖивотное " + animals[index] + " - покормлено");
    }


    /**
     * Болезнь животного.
     *
     * @param index - индекс животного.
     */
    public void disease(int index) {
        if (animals[index].isDiseaseStatus()) {
            System.out.println("\nЖивотное " + animals[index] + " заболело и умерло");
        }
    }


    /**
     * Метод ставит свободного сотрудника в конец массива, в конце метода вызвается метод удаления записи о животном.
     *
     * @param animalIndex - индекса животного в массиве, которое нужно убрать.
     */
    public void removeAnimal(int animalIndex) {
        Employee[] newEmployee = new Employee[] {}; // Новый массив для обмена сотрудников
        Enclosure[] newEnclosure = new Enclosure[] {}; // Новый массив для обмена вольеров
        newEmployee =  Arrays.copyOf(employees, employees.length);
        newEnclosure = Arrays.copyOf(enclosures, enclosures.length);

            //Процесс обмена сотрудников
            newEmployee[employees.length - 1] = employees[animalIndex];
            employees[animalIndex] = newEmployee[employees.length - 1];
            newEmployee[animalIndex] = employees[employees.length - 1];
            employees = newEmployee;

            //Процесс обмена вольеров
            newEnclosure[enclosures.length - 1] = enclosures[animalIndex];
            enclosures[animalIndex] = newEnclosure[enclosures.length - 1];
            newEnclosure[animalIndex] = enclosures[enclosures.length - 1];
            enclosures = newEnclosure;

            remove(animals[animalIndex]); // Удаление животного
    }


    /**
     * Информация о зоопарке.
     */
    public void info() {
        String an;
        for (int i = 0; i < employees.length ; i++) {
        if (animals.length > i){
            an = animals[i].getName();
            System.out.println("Сотрудник " + employees[i] + " отвечает за животное " + an +
                    ",которому дан вольер " + enclosures[i]);
            }
        }
    }
}
