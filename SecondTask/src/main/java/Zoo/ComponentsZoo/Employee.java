package Zoo.ComponentsZoo;

/**
 * Сотрудник {@link Zoo}
 */
public class Employee {
    private final String name; // Имя сотрудника

    /**
     * Создаем имя сотрудника
     *
     * @param name
     */
    public Employee(String name) {
        this.name = name;
    }

    /**
     * Кормление животного сотрудником
     *
     * @param animal
     */
    public void feed(String animal) {
        System.out.println("Сотрудник " + this.name + " покормил животного " + animal);
    }

    /**
     * Возвращает текствое предсталвние класса.
     *
     * @return текст
     */
    @Override
    public String toString() {
        return name;
    }
}
